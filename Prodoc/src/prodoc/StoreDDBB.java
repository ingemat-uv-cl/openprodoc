/*
 * OpenProdoc
 * 
 * See the help doc files distributed with
 * this work for additional information regarding copyright ownership.
 * Joaquin Hierro licenses this file to You under:
 * 
 * License GNU Affero GPL v3 http://www.gnu.org/licenses/agpl.html
 * 
 * you may not use this file except in compliance with the License.  
 * Unless agreed to in writing, software is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * author: Joaquin Hierro      2011
 * 
 */

package prodoc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.StringTokenizer;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author jhierrot
 */
public class StoreDDBB extends StoreGeneric
{
/**
 *
 */
private Connection con=null;
/**
 *
 */
private Statement stmt=null;
/**
 *
 */
private String Table=null;
/**
 *
 */
private String Driver=null;


//-----------------------------------------------------------------
/**
 *
 * @param pServer
 * @param pUser
 * @param pPassword
 * @param pParam
     * @param pEncrypt
     * @throws prodoc.PDExceptionFunc
 */
protected StoreDDBB(String pServer, String pUser, String pPassword, String pParam, boolean pEncrypt) throws PDExceptionFunc
{
super(pServer, pUser, pPassword, pParam, pEncrypt);
StringTokenizer st = new StringTokenizer(pParam, ";");    
setDriver(st.nextToken());
setTable(st.nextToken());
if (isEncript())
    setEncriptPass(st.nextToken());
}

//-----------------------------------------------------------------
/**
 *
 * @throws PDException
 */
protected void Create() throws PDException
{
    // PdId includes PDID+Version
String SQL="CREATE TABLE "+getTable()+"(PDId varchar(32) NOT NULL, PDVersion varchar(32) NOT NULL, PDDATE timestamp NOT NULL, PDCONT BLOB NOT NULL, PRIMARY KEY(PDId, PDVersion))";
try {
stmt. execute(SQL);
} catch (Exception ex)
    {
    PDException.GenPDException("Error_creating_StoreDDBB",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------
/**
 *
 * @throws PDException
 */
protected void Destroy() throws PDException
{
String SQL="DROP TABLE "+getTable();
try {
stmt. execute(SQL);
} catch (Exception ex)
    {
    PDException.GenPDException("Error_deleting_StoreDDBB",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------
/**
 *
 * @throws PDException
 */
protected void Connect() throws PDException
{
try {
getDriver();
Class.forName(getDriver());
} catch (ClassNotFoundException ex)
    {
    PDException.GenPDException("Driver_JDBC_not_found",getDriver()+"="+ex.getLocalizedMessage());
    }
try {
con = DriverManager.getConnection(getServer(), getUser(), getPassword());
stmt = con.createStatement();
} catch (Exception ex)
    {
    PDException.GenPDException("Error_connecting_trough_JDBC",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------
/**
 *
 * @throws PDException
 */
protected void Disconnect() throws PDException
{
try {
stmt.close();
} catch (Exception ex)
    {
    PDException.GenPDException("Error_closing_JDBC_Sentence", ex.getLocalizedMessage());
    }
try {
con.close();
} catch (Exception ex)
    {
    PDException.GenPDException("Error_closing_JDBC_connection", ex.getLocalizedMessage());
    }

}
//-----------------------------------------------------------------
/**
 * 
 * @param Id
 * @param Ver
 * @param Bytes
     * @param Rec
     * @param OPDPath
 * @return
 * @throws PDException
 */
protected int Insert(String Id, String Ver, InputStream Bytes, Record Rec, String OPDPath) throws PDException
{
VerifyId(Id);
try {
con.setAutoCommit(false);
String SQL = "INSERT INTO "+getTable()+" (PDId, PDVersion, PDDATE, PDCONT) VALUES (?, ?, ?, ?)";
PreparedStatement BlobStmt = con.prepareStatement(SQL);
BlobStmt.setString(1, Id);
BlobStmt.setString(2, Ver);
BlobStmt.setTimestamp(3,  new Timestamp(System.currentTimeMillis()));
// BlobStmt.setBinaryStream(4, Bytes);
SerialBlob Bl=null;//  new SerialBlob(Buffer);
int Tot=0;
int readed=Bytes.read(Buffer);
while (readed!=-1)
    {
    if (isEncript())
       EncriptPass(Buffer, readed);
    if (Bl==null)
        Bl=new SerialBlob(Buffer);
    else
        Bl.setBytes(Tot, Buffer);
    Tot+=readed;
    readed=Bytes.read(Buffer);
    }
Bl.truncate(Tot);
Bytes.close();
BlobStmt.setBlob(4, Bl);
BlobStmt.execute();
con.commit();
con.setAutoCommit(true);
Bytes.close();
} catch (Exception ex)
    {
    PDException.GenPDException("Error_inserting_content",ex.getLocalizedMessage());
    }
return (-1); 
}
//-----------------------------------------------------------------
/**
 *
 * @param Id
 * @param Ver
     * @param Rec
 * @throws PDException
 */
protected void Delete(String Id, String Ver, Record Rec) throws PDException
{
VerifyId(Id);
String SQL="delete from "+getTable()+" where PDId='"+Id+"' and PDVersion='"+Ver+"'";
try {
stmt. execute(SQL);
} catch (Exception ex)
    {
    PDException.GenPDException("Error_deleting_content",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------
/**
 *
 * @param Id
 * @param Ver
 * @return
 * @throws PDException
 */
protected InputStream Retrieve(String Id, String Ver, Record Rec) throws PDException
{
VerifyId(Id);
try {
String SQL = "SELECT PDCONT FROM "+getTable()+" where PDId='"+Id+"' and PDVersion='"+Ver+"'";
PreparedStatement BlobStmt = con.prepareStatement(SQL);
ResultSet resultSet = BlobStmt.executeQuery();
while (resultSet.next())
    {
    return (resultSet.getBinaryStream(1));
    }
PDException.GenPDException("Inexistent_content", Id+"/"+Ver);
} catch (Exception ex)
    {
    PDException.GenPDException("Error_retrieving_content", ex.getLocalizedMessage());
    }
return(null);
}
//-----------------------------------------------------------------
/**
 *
 * @param Id
 * @param Ver
 * @return
 * @throws PDException
 */
//protected int Retrieve(String Id, String Ver, OutputStream fo) throws PDException
//{
//VerifyId(Id);
//int Tot=0;
//try {
//String SQL = "SELECT PDCONT FROM "+getTable()+" where PDId='"+Id+"' and PDVersion='"+Ver+"'";
//PreparedStatement BlobStmt = con.prepareStatement(SQL);
//ResultSet resultSet = BlobStmt.executeQuery();
//if (resultSet.next())
//    {
//    InputStream in=resultSet.getBinaryStream(1);
//    int readed=in.read(Buffer);
//    while (readed!=-1)
//        {
//        if (isEncript())
//           DecriptPass(Buffer, readed);
//        fo.write(Buffer, 0, readed);
//        Tot+=readed;
//        readed=in.read(Buffer);
//        }
//    in.close();
//    fo.flush();
//    fo.close();
//    }
//resultSet.close();
//} catch (Exception ex)
//    {
//    PDException.GenPDException("Error_retrieving_content", ex.getLocalizedMessage());
//    }
//return(Tot);
//}
//-----------------------------------------------------------------

/**
 *
 * @return
 */
protected String getDriver()
{
return(Driver);
}
//-----------------------------------------------------------------
/**
* @return the Table
*/
protected String getTable()
{
return Table;
}
//-----------------------------------------------------------------
/**
* @param pParam the Table to set
*/
protected void setTable(String pParam)
{
// Table =pParam.substring(pParam.lastIndexOf(";")+1);
Table =pParam;
}
//-----------------------------------------------------------------
/**
* @param pParam the Driver to set
*/
protected void setDriver(String pParam)
{
// Driver =pParam.substring(0, pParam.lastIndexOf(";"));
Driver =pParam;
}
//-----------------------------------------------------------------
/**
 * 
 * @param Id1 Original Identifier
 * @param Ver1 Original Version
 * @param Id2  New Identifier
 * @param Ver2  New Version
 * @throws PDException
 */
@Override
protected void Rename(String Id1, String Ver1, String Id2, String Ver2) throws PDException
{
VerifyId(Id1);
String SQL="update "+getTable()+" set PDId='"+Id2+"', PDVersion='"+Ver2+"' where PDId='"+Id1+"' and PDVersion='"+Ver1+"'";
try {
stmt. execute(SQL);
} catch (Exception ex)
    {
    PDException.GenPDException("Error_renaming_content:",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------
}
