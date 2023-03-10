/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mx.itson.Philadelphia.ui;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mx.itson.Philadelphia.entidades.Multa;
import mx.itson.philadelphia.persistencia.MultaDAO;

/**
 *
 * @author pyatq
 */
public class MultaListado extends javax.swing.JFrame {
    
    int id;
    String folio;
    String motivo;
    String fecha;
    SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");

    /**
     * Creates new form MultaListado
     */
    public MultaListado() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMulta = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblMulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Folio", "Motivo", "Fecha", "Nombre Conductor", "Nombre Oficial"
            }
        ));
        tblMulta.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblMultaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tblMulta);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addGap(37, 37, 37)
                .addComponent(btnEditar)
                .addGap(31, 31, 31)
                .addComponent(btnEliminar)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMultaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblMultaAncestorAdded
        List<Multa> multas = MultaDAO.obtenerTodos();
        DefaultTableModel modelo = (DefaultTableModel) tblMulta.getModel();
        modelo.setRowCount(0);
        
       for(Multa m : multas){
           modelo.addRow(new Object[]{
               m.getId(),
               m.getFolio(),
               m.getMotivo(),
               formato.format(m.getFecha()),
               m.getConductor().getNombre(),
               m.getOficial().getNombre()       
          });
       }
    }//GEN-LAST:event_tblMultaAncestorAdded

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tblMulta.getModel();
        int fila = tblMulta.getSelectedRow();
        int id = Integer.parseInt(modelo.getValueAt(fila,0).toString());
        String folio = modelo.getValueAt(fila, 1).toString();
        String motivo = modelo.getValueAt(fila, 2).toString();
        String fecha = modelo.getValueAt(fila, 3).toString();
        
        
        
       MultaFormulario formulario = new MultaFormulario(this, true, id, folio, motivo, fecha);
       formulario.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        MultaFormulario formulario = new MultaFormulario(this, true, id, folio, motivo, fecha);
       
        formulario.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       
        int E = JOptionPane.showConfirmDialog(this, "Estas seguro de eliminar los datos de Multa?","estas Seguro?", JOptionPane.YES_NO_OPTION);
                
        if(E == 0){
        DefaultTableModel modelo = (DefaultTableModel) tblMulta.getModel();
        int fila = tblMulta.getSelectedRow();
        int id = Integer.parseInt(modelo.getValueAt(fila,0).toString());
        
        boolean operacion = false;
        try{
        MultaDAO multaDAO = new MultaDAO();
        operacion = multaDAO.eliminar(id);
        
        }catch(Exception ex){
         System.err.println("Ocurrio un error: " + ex);
        }
        
        if(operacion == true){
        JOptionPane.showMessageDialog(this, "La operacion de eliminacion fue exitosa","Operacion excitosa", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
        JOptionPane.showMessageDialog(this, "No se ha pudido eliminar","Operacion fallida", JOptionPane.WARNING_MESSAGE);
        
        this.dispose();
           MultaListado frame = new MultaListado();
            frame.setVisible(true);
        }
    }
                            
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MultaListado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MultaListado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MultaListado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MultaListado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultaListado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMulta;
    // End of variables declaration//GEN-END:variables
}
