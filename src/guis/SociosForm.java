
package guis;

import entidades.Socio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ISociosDAO;

public class SociosForm extends javax.swing.JFrame {

    private final ISociosDAO sociosDAO;
    
    public SociosForm(ISociosDAO sociosDAO) {
        initComponents();
        this.sociosDAO = sociosDAO;
        this.llenarTabla();
    }

    private void guardar(){
        if(this.txtIdSocio.getText().isEmpty()){
            this.agregar();
        }else{
            this.actualizar();
        }
    }
    
    private void agregar(){
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        // TODO: VALIDACIONES DE DATOS
        Socio socio = new Socio(nombre, curp);
        boolean seAgregoSocio = this.sociosDAO.agregar(socio);
        if(seAgregoSocio){
            JOptionPane.showMessageDialog(this, "Se agregó el socio", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
            this.llenarTabla();
        }else{
            JOptionPane.showMessageDialog(this, "No se pudo agregar el socio", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizar(){
        Long idSocio = Long.parseLong(this.txtIdSocio.getText());
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        // TODO: VALIDACIONES DE DATOS
        Socio socio = new Socio(idSocio, nombre, curp);
        boolean seActualizoSocio = this.sociosDAO.actualizar(socio);
        if(seActualizoSocio){
            JOptionPane.showMessageDialog(this, "Se actualizó el socio", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
            this.llenarTabla();
        }else{
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el socio", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void llenarTabla(){
        List<Socio> listaSocios = this.sociosDAO.consultarTodos();
        DefaultTableModel modeloTabla = (DefaultTableModel)this.tblSocios.getModel();
        modeloTabla.setRowCount(0);
        listaSocios.forEach(socio -> {
            Object[] fila = new Object[3];
            fila[0] = socio.getId();
            fila[1] = socio.getNombre();
            fila[2] = socio.getCurp();
            modeloTabla.addRow(fila); 
        });
    }
    
    private void eliminar(){
        Long idSocioSeleccionado = this.getIdSocioSeleccionado();
        if(idSocioSeleccionado == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un socio para eliminarlo", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, 
            "¿Estas seguro de eliminar el socio?", 
            "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if(opcionSeleccionada == JOptionPane.NO_OPTION){
            return;
        }
        
        // MANDEREMOS ELIMINAR CON LA DAO
        boolean seEliminoSocio = this.sociosDAO.eliminar(idSocioSeleccionado);
        // MOSTRAREMOS UN MENSAJE DE QUE PASO
        if(seEliminoSocio){
            JOptionPane.showMessageDialog(this, "Se eliminó el socio", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            this.llenarTabla();
        }else{
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el socio", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // COMPROBAR QUE HAYA UNA FILA SELECCIONADA
    // EXTRAER EL ID DE SOCIO DE ESA FILA
    private Long getIdSocioSeleccionado(){
        int indiceFilaSeleccionada = this.tblSocios.getSelectedRow();
        if(indiceFilaSeleccionada != -1){
            DefaultTableModel modelo = (DefaultTableModel)this.tblSocios.getModel();
            int indiceColumnaId = 0;
            Long idSocioSeleccionado = (Long)modelo.getValueAt(indiceFilaSeleccionada, 
                    indiceColumnaId);
            return idSocioSeleccionado;
        }else{
            return null;
        }
    }
    
    private void editar(){
        Long idSocioSeleccionado = this.getIdSocioSeleccionado();
        if(idSocioSeleccionado == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un socio para eliminarlo", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Socio socio = this.sociosDAO.consultar(idSocioSeleccionado);
        if(socio != null){
            this.llenarFormulario(socio);
        }
    }
    
    private void llenarFormulario(Socio socio){
        this.txtIdSocio.setText(socio.getId().toString());
        this.txtNombre.setText(socio.getNombre());
        this.txtCurp.setText(socio.getCurp());
    }
    
    private void limpiar(){
        this.txtIdSocio.setText("");
        this.txtNombre.setText("");
        this.txtCurp.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIdSocio = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCurp = new javax.swing.JLabel();
        txtIdSocio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCurp = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTablaSocios = new javax.swing.JScrollPane();
        tblSocios = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administración de Socios");

        lblIdSocio.setText("Id Socio");

        lblNombre.setText("Nombre");

        lblCurp.setText("Curp");

        txtIdSocio.setEditable(false);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Socio", "Nombre", "Curp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlTablaSocios.setViewportView(tblSocios);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdSocio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(lblCurp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(txtNombre)
                    .addComponent(txtCurp)
                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlTablaSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdSocio)
                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurp)
                    .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlTablaSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblCurp;
    private javax.swing.JLabel lblIdSocio;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JScrollPane pnlTablaSocios;
    private javax.swing.JTable tblSocios;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtIdSocio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
