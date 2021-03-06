/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prodocswing.forms;

import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import prodoc.Attribute;
import prodoc.PDException;

/**
 *
 * @author jhierrot
 */
public class AttrMultiEdit extends javax.swing.JDialog
{
private TreeSet Values=null;
private Attribute Attr=null;
private boolean Cancel=true;
/**
* Creates new form MultiEdit
*/
public AttrMultiEdit(java.awt.Frame parent, boolean modal)
{
super(parent, modal);
initComponents();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jToolBar1 = new javax.swing.JToolBar();
        AddButton = new javax.swing.JButton();
        DelButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        EditVal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        ButtonOk = new javax.swing.JButton();
        ButtonCancel = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setRollover(true);

        AddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        AddButton.setToolTipText("");
        AddButton.setFocusable(false);
        AddButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(AddButton);

        DelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/del.png"))); // NOI18N
        DelButton.setToolTipText("");
        DelButton.setFocusable(false);
        DelButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DelButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(DelButton);

        EditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        EditButton.setToolTipText("");
        EditButton.setFocusable(false);
        EditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EditButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(EditButton);

        jLabel1.setFont(MainWin.getFontDialog());
        jLabel1.setText("AttrName");

        EditVal.setFont(MainWin.getFontDialog());
        EditVal.setToolTipText("Introducir nombre completo o parcial del usuario");
        EditVal.setName("EditVal"); // NOI18N

        jList1.setFont(MainWin.getFontList());
        jList1.setModel(getListModel());
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        ButtonOk.setFont(MainWin.getFontDialog());
        ButtonOk.setText(MainWin.TT("Ok"));
        ButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOkActionPerformed(evt);
            }
        });

        ButtonCancel.setFont(MainWin.getFontDialog());
        ButtonCancel.setText(MainWin.TT("Cancel"));
        ButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EditVal, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(9, 9, 9))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonOk)
                        .addComponent(ButtonCancel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AddButtonActionPerformed
    {//GEN-HEADEREND:event_AddButtonActionPerformed
if (EditVal.getText().length()==0)
    return;
getValues().add(EditVal.getText());
RefreshList();
    }//GEN-LAST:event_AddButtonActionPerformed

    private void DelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DelButtonActionPerformed
    {//GEN-HEADEREND:event_DelButtonActionPerformed
String Val2Del=(String)jList1.getSelectedValue();
if (Val2Del==null)
    return;
getValues().remove(Val2Del);
RefreshList();
    }//GEN-LAST:event_DelButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EditButtonActionPerformed
    {//GEN-HEADEREND:event_EditButtonActionPerformed
String ValSel=(String)jList1.getSelectedValue();
if (ValSel==null || EditVal.getText().length()==0)
    return;
getValues().remove(ValSel);
getValues().add(EditVal.getText());
RefreshList();
    }//GEN-LAST:event_EditButtonActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jList1ValueChanged
    {//GEN-HEADEREND:event_jList1ValueChanged
String ValSel=(String)jList1.getSelectedValue();
if (ValSel==null)
    return;
EditVal.setText(ValSel);       
    }//GEN-LAST:event_jList1ValueChanged

    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButtonOkActionPerformed
    {//GEN-HEADEREND:event_ButtonOkActionPerformed
Cancel = false;
this.dispose();
    }//GEN-LAST:event_ButtonOkActionPerformed

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButtonCancelActionPerformed
    {//GEN-HEADEREND:event_ButtonCancelActionPerformed
Cancel = true;
this.dispose();
    }//GEN-LAST:event_ButtonCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
Cancel = true;
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JButton ButtonOk;
    private javax.swing.JButton DelButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JTextField EditVal;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
//----------------------------------------------------------------------------
private void SetValues(TreeSet pValues)
{
Values=pValues;
RefreshList();
}
//----------------------------------------------------------------------------
/**
* @return the Values
*/
private TreeSet getValues()
{
if (Values==null)
    Values=new TreeSet();
return Values;
}
//----------------------------------------------------------------------------    
private ListModel getListModel()
{
DefaultListModel LM=new DefaultListModel();
for (Iterator it = getValues().iterator(); it.hasNext();)
    {
    LM.addElement(it.next());
    }
return(LM);
}
//-----------------------------------------
private void RefreshList()
{
jList1.setModel(getListModel());
}
//-----------------------------------------

void setAttr(Attribute pAttr)
{
Attr=pAttr;
setTitle(Attr.getUserName());
jLabel1.setText(Attr.getUserName());
this.EditVal.setToolTipText(Attr.getDescription());
try{
    SetValues(Attr.getValuesList());
} catch (PDException ex)
    { // Called always for Multivalued, the exception generated
    }
}
//-----------------------------------------
/**
* @return the Cancel
*/
public boolean isCancel()
{
return Cancel;
}
//----------------------------------------------------------------

}
