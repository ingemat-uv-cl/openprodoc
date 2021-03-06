/*
 * OpenProdoc
 * 
 * See the help doc files distributed with
 * this work for additional information regarding copyright ownership.
 * Joaquin Hierro licenses this file to You under:
 * 
 * License GNU GPL v3 http://www.gnu.org/licenses/gpl.html
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

package prodocUI.servlet;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import prodoc.DriverGeneric;
import prodoc.PDException;
import prodoc.PDFolders;
import prodoc.Record;
import prodocUI.forms.FMantFoldAdv;

/**
 *
 * @author jhierrot
 */
public class DelFold extends SParent
{
//-----------------------------------------------------------------------------------------------
/**
 *
 * @param Req
 * @param out
 * @throws Exception
 */
@Override
protected void ProcessPage(HttpServletRequest Req, PrintWriter out) throws Exception
{
if (!Reading(Req))
    {
    DriverGeneric PDSession=SParent.getSessOPD(Req)  ;
    PDFolders F = new PDFolders(PDSession);
    F.LoadFull(getActFolderId(Req));
    Record Rec=F.getRecSum();
    FMantFoldAdv f=new FMantFoldAdv(Req, FMantFoldAdv.DELMOD, Rec, getUrlServlet());
    out.println(f.ToHtml(Req.getSession()));
    return;
    }
else
    {
    try {
    boolean Acept=DelFolder(Req);
    if (Acept)
        {
        Record RecFoldMod=SParent.getActFold(Req);
        String ParentFoldId=(String)RecFoldMod.getAttr(PDFolders.fPARENTID).getValue();
        SParent.setActFoldId(Req, ParentFoldId);
        GenListForm(Req, out, LAST_FORM, "parent.Delete('"+(String)RecFoldMod.getAttr(PDFolders.fPDID).getValue()+"')", null);
        }
    else
        GenListForm(Req, out, LAST_FORM, null, null);
    } catch (PDException ex)
        {
        ShowMessage( Req,  out, SParent.TT(Req, ex.getLocalizedMessage()));
        }
    }
}
//-----------------------------------------------------------------------------------------------

/**
 * Returns a short description of the servlet.
 * @return a String containing servlet description
 */
@Override
public String getServletInfo()
{
return "DelFold Servlet";
}
//-----------------------------------------------------------------------------------------------
static public String getUrlServlet()
{
return("DelFold");
}
//-----------------------------------------------------------------------------------------------
private boolean DelFolder(HttpServletRequest Req) throws PDException
{
String Acept=Req.getParameter("BOk");
if (Acept==null || Acept.length()==0)
    return(false);
DriverGeneric PDSession=SParent.getSessOPD(Req);
PDFolders F = new PDFolders(PDSession);
F.setPDId(getActFolderId(Req));
F.delete();
return(true);
}
//-----------------------------------------------------------------------------------------------
}
