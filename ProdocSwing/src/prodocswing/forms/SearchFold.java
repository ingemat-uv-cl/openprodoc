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

/*
 * DialogEditFold.java
 *
 * Created on 28-mar-2010, 20:14:35
 */

package prodocswing.forms;

import java.awt.Component;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import prodoc.Attribute;
import static prodoc.Attribute.DECIMALPATTERN;
import prodoc.Condition;
import prodoc.Conditions;
import prodoc.Cursor;
import prodoc.DriverGeneric;
import prodoc.PDException;
import prodoc.PDFolders;
import prodoc.PDObjDefs;
import prodoc.PDThesaur;
import prodoc.Record;
import prodocswing.ThesField;

/**
 *
 * @author jhierrot
 */
public class SearchFold extends javax.swing.JDialog
{

private Record Folder;
private boolean Cancel;
private boolean Modif=false;
private Vector InputFields=new Vector();
private Vector Comparators=new Vector();
private HashSet AttrExcluded=new HashSet();
private String ActFolderId=null;

/** Creates new form DialogEditFold
 * @param parent
 * @param modal 
 */
public SearchFold(java.awt.Frame parent, boolean modal)
{
super(parent, modal);
initComponents();
}

/** This method is called from within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        LabelOperation = new javax.swing.JLabel();
        LabelFoldType = new javax.swing.JLabel();
        FoldTypeCB = new javax.swing.JComboBox();
        Attributes = new javax.swing.JTabbedPane();
        ButtonCancel = new javax.swing.JButton();
        ButtonAcept = new javax.swing.JButton();
        SubTypesCheckB = new javax.swing.JCheckBox();
        SubFoldersCheckB = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(MainWin.TT("Search_Folders"));
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosed(java.awt.event.WindowEvent evt)
            {
                formWindowClosed(evt);
            }
        });

        LabelOperation.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        LabelOperation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelOperation.setText(MainWin.TT("Search_Folders"));

        LabelFoldType.setFont(MainWin.getFontDialog());
        LabelFoldType.setText(MainWin.TT("Folder_Type"));

        FoldTypeCB.setFont(MainWin.getFontDialog());
        FoldTypeCB.setModel(getComboModelFold());
        FoldTypeCB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                FoldTypeCBActionPerformed(evt);
            }
        });

        ButtonCancel.setFont(MainWin.getFontDialog());
        ButtonCancel.setText(MainWin.TT("Cancel"));
        ButtonCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ButtonCancelActionPerformed(evt);
            }
        });

        ButtonAcept.setFont(MainWin.getFontDialog());
        ButtonAcept.setText(MainWin.TT("Ok"));
        ButtonAcept.setEnabled(false);
        ButtonAcept.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ButtonAceptActionPerformed(evt);
            }
        });

        SubTypesCheckB.setFont(MainWin.getFontDialog());
        SubTypesCheckB.setText(MainWin.TT("Subtypes"));
        SubTypesCheckB.setToolTipText(MainWin.TT("When_checked_includes_subtypes_of_folders_in_results"));

        SubFoldersCheckB.setFont(MainWin.getFontDialog());
        SubFoldersCheckB.setText(MainWin.TT("SubFolders"));
        SubFoldersCheckB.setToolTipText(MainWin.TT("When_checked_limits_the_search_to_actual_folder_and_subfolders"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Attributes, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SubTypesCheckB)
                    .addComponent(LabelFoldType, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(SubFoldersCheckB)
                        .addContainerGap(231, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FoldTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(302, Short.MAX_VALUE)
                        .addComponent(ButtonAcept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(LabelOperation, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelOperation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelFoldType)
                    .addComponent(FoldTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubTypesCheckB)
                    .addComponent(SubFoldersCheckB))
                .addGap(18, 18, 18)
                .addComponent(Attributes, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCancel)
                    .addComponent(ButtonAcept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButtonCancelActionPerformed
    {//GEN-HEADEREND:event_ButtonCancelActionPerformed
Cancel=true;
this.dispose();
}//GEN-LAST:event_ButtonCancelActionPerformed

    private void ButtonAceptActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButtonAceptActionPerformed
    {//GEN-HEADEREND:event_ButtonAceptActionPerformed
try {
RetrieveFields(Folder, AttrExcluded, InputFields, Comparators);
Cancel = false;
PDFolders Fold=new PDFolders(MainWin.getSession());
Cursor Cur=Fold.Search(getFoldTyp(), getConds(), getSubTyp(), getSubFold(), ActFolderId, null);
ListFolders LF = new ListFolders(this, true);
LF.setLocationRelativeTo(null);
LF.setCursor(getFoldTyp(), Cur);
LF.RefreshTable();
LF.setVisible(true);
} catch (PDException ex)
    {
    MainWin.Message(MainWin.DrvTT(ex.getLocalizedMessage()));
    }
}//GEN-LAST:event_ButtonAceptActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
Cancel=true;
    }//GEN-LAST:event_formWindowClosed

    private void FoldTypeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FoldTypeCBActionPerformed
try {
PDFolders newFolder = new PDFolders(MainWin.getSession(), (String) FoldTypeCB.getSelectedItem());
Folder=newFolder.getRecSum();
setRecord(Folder);
ButtonAcept.setEnabled(true);
} catch (PDException ex)
    {
    MainWin.Message(MainWin.DrvTT(ex.getLocalizedMessage()));
    }
    }//GEN-LAST:event_FoldTypeCBActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Attributes;
    private javax.swing.JButton ButtonAcept;
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JComboBox FoldTypeCB;
    private javax.swing.JLabel LabelFoldType;
    private javax.swing.JLabel LabelOperation;
    private javax.swing.JCheckBox SubFoldersCheckB;
    private javax.swing.JCheckBox SubTypesCheckB;
    // End of variables declaration//GEN-END:variables

//----------------------------------------------------------------
/**
 * Obtains a list of clases of type folder allowed to the user
 * @return a DefaultComboModel with names of classes of folder
 */
private DefaultComboBoxModel getComboModelFold()
{
Vector VObjects=new Vector();
try {
DriverGeneric Session=MainWin.getSession();
PDObjDefs Obj = new PDObjDefs(Session);
Cursor CursorId = Obj.getListFold();
Record Res=Session.NextRec(CursorId);
while (Res!=null)
    {
    Attribute Attr=Res.getAttr(PDObjDefs.fNAME);
    VObjects.add(Attr.getValue());
    Res=Session.NextRec(CursorId);
    }
Session.CloseCursor(CursorId);
} catch (PDException ex)
    {
    MainWin.Message(MainWin.DrvTT(ex.getLocalizedMessage()));
    }
return(new DefaultComboBoxModel(VObjects));
}
//----------------------------------------------------------------
/**
* @return the Folder edited
*/
public Record getRecord()
{
return Folder;
}
//----------------------------------------------------------------
/**
* @param pFolder the User to set
*/
public void setRecord(Record pFolder) throws PDException
{
Folder = pFolder;
AttrExcluded.clear();
Attribute Attr;
Component[] List=Attributes.getComponents();
for (int i = 0; i < List.length; i++)
    {
    JPanel component =(JPanel) List[i];
    Attributes.remove(component);
    }
GenerateTabs(Folder, Attributes, 7, MainWin.TT("Search_Attributes"), AttrExcluded);
}
//----------------------------------------------------------------
/**
* @return the Cancel
*/
public boolean isCancel()
{
return Cancel;
}
//----------------------------------------------------------------
private void GenerateTabs(Record Rec, JTabbedPane Tabs, int MAXFIELDS, String Title, HashSet AttrExcluded) throws PDException
{
InputFields.clear();
Comparators.clear();
JPanel ActPanel=null;
GroupLayout layout= null;
int CountFields=0;
GroupLayout.SequentialGroup hGroup= null;
GroupLayout.SequentialGroup vGroup= null;
GroupLayout.ParallelGroup LGroup= null;
GroupLayout.ParallelGroup CGroup= null;
GroupLayout.ParallelGroup TGroup= null;
Rec.initList();
Attribute Attr=Rec.nextAttr();
while (Attr!=null)
    {
    if (!AttrExcluded.contains(Attr.getName()))
        {
        if (CountFields++%MAXFIELDS==0)
            {
            if (CountFields!=1)
                {
                hGroup.addGroup(LGroup);
                hGroup.addGroup(CGroup);
                hGroup.addGroup(TGroup);
                layout.setHorizontalGroup(hGroup);
                layout.setVerticalGroup(vGroup);
                }
            ActPanel=new JPanel();
            ActPanel.setName(Title+(int)CountFields/MAXFIELDS);
            Tabs.add(ActPanel);
            layout = new GroupLayout(ActPanel);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            hGroup = layout.createSequentialGroup();
            vGroup = layout.createSequentialGroup();
            LGroup=layout.createParallelGroup();
            CGroup=layout.createParallelGroup();
            TGroup=layout.createParallelGroup();
            ActPanel.setLayout(layout);
            }
        JLabel Lab=new JLabel(Attr.getUserName());
        Lab.setFont(MainWin.getFontDialog());
        JComboBox SelComb = new javax.swing.JComboBox();
        SelComb.setFont(MainWin.getFontDialog());
        SelComb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", ">", "<", ">=", "<=", "<>", "Contains" }));
        SelComb.setMaximumSize(new Dimension(40, 24));
        Comparators.add(SelComb);
        JComponent JTF=genComponent(Attr);
        InputFields.add(JTF);
        LGroup.addComponent(Lab);
        CGroup.addComponent(SelComb);
        TGroup.addComponent(JTF);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(Lab).addComponent(SelComb).addComponent(JTF));
        }
    Attr=Rec.nextAttr();
    }
if (CountFields==0)
    return;
hGroup.addGroup(LGroup);
hGroup.addGroup(CGroup);
hGroup.addGroup(TGroup);
layout.setHorizontalGroup(hGroup);
layout.setVerticalGroup(vGroup);
}
//----------------------------------------------------------------------
/**
 *
 * @param Attr
 * @param Modif
 * @return
 */
private JComponent genComponent(Attribute Attr) throws PDException
{
JComponent JTF=null;
if (Attr.getType()==Attribute.tSTRING)
    {
    JTF=new JTextField();
    }
else if (Attr.getType()==Attribute.tTHES)
    {
    PDThesaur UseTerm=new PDThesaur(MainWin.getSession());    
    JTF=new ThesField(this, UseTerm, ""+Attr.getLongStr());
    }
else if (Attr.getType()==Attribute.tDATE)
    {
    JTF=new JFormattedTextField(MainWin.getFormatterDate());
    }
else if (Attr.getType()==Attribute.tTIMESTAMP)
    {
    JTF=new JFormattedTextField(MainWin.getFormatterTS());
    }
else if (Attr.getType()==Attribute.tBOOLEAN)
    {
    JCheckBox JCB=new JCheckBox( );
    }
else if (Attr.getType()==Attribute.tINTEGER)
    {
    JTF=new JFormattedTextField(new DecimalFormat());
    }
else if (Attr.getType()==Attribute.tFLOAT)
    {
    JTF=new JFormattedTextField(new DecimalFormat(DECIMALPATTERN));
    }
else
     JTF=new JTextField("Error");
//JTF.setEnabled(true);
if (Attr.getType()==Attribute.tDATE )
    JTF.setToolTipText(MainWin.DrvTT(Attr.getDescription()) +"( "+MainWin.getFormatDate()+" )");
else if(Attr.getType() == Attribute.tTIMESTAMP)
    JTF.setToolTipText(MainWin.DrvTT(Attr.getDescription()) +"( "+MainWin.getFormatTS()+" )");
else
    JTF.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
JTF.setFont(MainWin.getFontDialog());
return(JTF);
}
//--------------------------------------------------------------
/**
 *
 */
private void RetrieveFields(Record Rec, HashSet AttrExc, Vector CompList, Vector CompOper) throws PDException
{
Rec.initList();
Attribute Attr=Rec.nextAttr();
int FieldNumber=0;
while (Attr!=null)
    {
    if (!AttrExc.contains(Attr.getName()))
        {
        FillAttr(Attr, (JComponent)CompList.get(FieldNumber));
        Attr.setDescription( (String)((JComboBox)CompOper.get(FieldNumber++)).getSelectedItem());
        }
    Attr=Rec.nextAttr();
    }
}
//--------------------------------------------------------------
/**
 * Fill the value of the attribute with the text o value of the fieldest1
 * @param Attr
 * @param jComponent
 */
private void FillAttr(Attribute Attr, JComponent JTF) throws PDException
{
if (Attr.getType()==Attribute.tSTRING)
    {
    if (Attr.isMultivalued())
        Attr.Import(((JTextField)JTF).getText());
    else
        Attr.setValue(((JTextField)JTF).getText());
    }
else if (Attr.getType()==Attribute.tTHES)
    {
    if (Attr.isMultivalued())
        Attr.Import( ((ThesField)JTF).getUseTerm().getPDId());
    else    
        Attr.setValue( ((ThesField)JTF).getUseTerm().getPDId());
    }
else if (Attr.getType()==Attribute.tDATE)
    {
    if (((JFormattedTextField)JTF).getText().length()!=0)    
        Attr.setValue((Date)((JFormattedTextField)JTF).getValue());
    }
else if (Attr.getType()==Attribute.tTIMESTAMP)
    {
    if (((JFormattedTextField)JTF).getText().length()!=0)    
        Attr.setValue((Date)((JFormattedTextField)JTF).getValue());
    }
else if (Attr.getType()==Attribute.tBOOLEAN)
    {
    Boolean Act;
    if (((JCheckBox)JTF).isSelected())
        Act=new Boolean(true);
    else
        Act=new Boolean(false);
    Attr.setValue(Act);
    }
else if (Attr.getType()==Attribute.tINTEGER)
    {
    Long l=(Long)((JFormattedTextField)JTF).getValue();
    if (l!=null)
        Attr.setValue(new Integer(l.intValue()));
    }
else if (Attr.getType()==Attribute.tFLOAT)
    {
    BigDecimal BD=(BigDecimal)((JFormattedTextField)JTF).getValue();
    if (BD!=null)
        Attr.setValue(BD);
    }
else
    Attr.setValue("Error");
}
//--------------------------------------------------------------
/**
 * 
 * @return
 */
public boolean getSubFold()
{
return(SubFoldersCheckB.isSelected());
}
//--------------------------------------------------------------
/**
 * 
 * @return
 */
public boolean getSubTyp()
{
return(SubTypesCheckB.isSelected());
}
//--------------------------------------------------------------
/**
 * 
 * @return
 */
public String getFoldTyp()
{
return((String)FoldTypeCB.getSelectedItem());
}
//--------------------------------------------------------------
/**
 * 
 * @return
 * @throws PDException
 */
public Conditions getConds() throws PDException
{
Conditions Conds=new Conditions();
Folder.initList();
Attribute Attr=Folder.nextAttr();
while (Attr!=null)
    {
    int Operator=Condition.cEQUAL;
    String OperText=Attr.getDescription();
    if (OperText.equals("="))
       Operator=Condition.cEQUAL;
    else if (OperText.equals("<"))
       Operator=Condition.cLT;
    else if (OperText.equals(">"))
       Operator=Condition.cGT;
    else if (OperText.equals("<>"))
       Operator=Condition.cNE;
    else if (OperText.equals("<="))
       Operator=Condition.cLET;
    else if (OperText.equals(">="))
       Operator=Condition.cGET;
    else if (OperText.equals("Contains"))
       Operator=Condition.cLIKE;
    if (Attr.isMultivalued())
        {
        if (Attr.getValuesList()!=null && !Attr.getValuesList().isEmpty())
            {
            Conditions CMulti=new Conditions();
            for (Iterator it = Attr.getValuesList().iterator(); it.hasNext();)
                {
                String Val = (String)it.next();
                Condition c=new Condition( Attr.getName(), Operator, Val);
                CMulti.addCondition(c);
                }
            Conds.addCondition(CMulti);    
            }
        }
    else if (Attr.getValue()!=null)
        {
        if (! (Attr.getType()==Attribute.tSTRING && ((String)Attr.getValue()).length()==0) )
            {
            Condition c=new Condition( Attr, Operator);
            Conds.addCondition(c);
            }
        }
    Attr=Folder.nextAttr();
    }
return(Conds);
}
//--------------------------------------------------------------
/**
 * 
 * @param FoldAct
 */
public void setFoldAct(String FoldAct)
{
ActFolderId=FoldAct;
}
//--------------------------------------------------------------
}
