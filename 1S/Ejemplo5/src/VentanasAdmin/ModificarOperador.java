package VentanasAdmin;

import Entidades.Usuario;
import Principal.Inicio;
import javax.swing.JOptionPane;

/**
 * ========================================================================
 * Clase: ModificarOperador
 * ========================================================================
 * Esta ventana permite editar los datos de un operador existente.
 * 
 * FUNCIONAMIENTO:
 * 1. Recibe el código del operador a modificar
 * 2. Busca el operador en el arreglo global de usuarios
 * 3. Muestra sus datos actuales en los campos de texto
 * 4. Permite modificar nombre, carrera y contraseña
 * 5. Guarda los cambios en el arreglo y el archivo
 * 
 * COMUNICACIÓN CON LA VENTANA PADRE:
 * - Esta ventana recibe una referencia a GestionesAdmin (ventanaPadre)
 * - Cuando se confirman los cambios, llama a guardarYRecargarOperadores()
 * - Esto actualiza la tabla en la ventana padre
 * ========================================================================
 */
public class ModificarOperador extends javax.swing.JFrame {
    
    // ========================================================================
    // VARIABLES DE INSTANCIA
    // ========================================================================
    
    // Usuario que se está modificando (referencia al objeto del arreglo global)
    private Usuario usuarioModificacion;
    
    // Referencia a la ventana padre (GestionesAdmin)
    // Se usa para actualizar la tabla cuando se guardan cambios
    private GestionesAdmin ventanaPadre;
    
    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================
    /**
     * Constructor de la ventana de modificar operador.
     * 
     * @param carnet - Código del operador a modificar
     * @param padre - Referencia a la ventana GestionesAdmin (para actualizar la tabla)
     */
    public ModificarOperador(String carnet, GestionesAdmin padre) {
        // PASO 1: Inicializar componentes gráficos (generado por NetBeans)
        initComponents();
        
        // PASO 2: Centrar la ventana en la pantalla
        this.setLocationRelativeTo(null);
        
        // PASO 3: Guardar la referencia a la ventana padre
        this.ventanaPadre = padre;
        
        // PASO 4: Buscar el usuario en el arreglo global usando el código
        usuarioModificacion = Inicio.ObtenerUsuario(carnet);
        
        // PASO 5: Validar que el usuario existe
        if (usuarioModificacion == null) {
            // Si no existe, mostrar error y cerrar la ventana
            JOptionPane.showMessageDialog(this, 
                "No se encontró el usuario", "Error", 
                JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Cerrar esta ventana
            return; // Salir del constructor
        }
        
        // PASO 6: Configurar los eventos de los botones
        configurarEventos();
        
        // PASO 7: Llenar los campos con los datos actuales del usuario
        rellenarCamposConDatosActuales();
    }
    
    // ========================================================================
    // MÉTODOS DE INICIALIZACIÓN Y CONFIGURACIÓN
    // ========================================================================
    
    /**
     * MÉTODO: configurarEventos()
     * -----------------------------------------------------------------------
     * Configura los eventos (listeners) de los botones.
     * 
     * ¿Qué es una expresión lambda (->)?
     * Es una forma corta de escribir un ActionListener.
     * Equivale a escribir:
     *   btnConfirmar.addActionListener(new ActionListener() {
     *       public void actionPerformed(ActionEvent evt) {
     *           btnConfirmarActionPerformed(evt);
     *       }
     *   });
     */
    private void configurarEventos() {
        // Configurar el evento del botón Confirmar
        btnConfirmar.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnConfirmarActionPerformed(evt);
        });
        
        // Configurar el evento del botón Cancelar
        btnCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnCancelarActionPerformed(evt);
        });
    }
    
    /**
     * MÉTODO: rellenarCamposConDatosActuales()
     * -----------------------------------------------------------------------
     * Rellena los campos del formulario con los datos actuales del usuario.
     * 
     * NOTA: El campo de código se pone como no editable porque el código
     * es el identificador único y no debe cambiar.
     */
    private void rellenarCamposConDatosActuales() {
        // Mostrar el código (pero no permitir editarlo)
        this.txtCodigo.setText(usuarioModificacion.getCodigo());
        this.txtCodigo.setEditable(false);
        
        // Mostrar el nombre actual (este SÍ se puede editar)
        this.txtNombre.setText(usuarioModificacion.getNombre());
        
        // Mostrar la carrera actual (este SÍ se puede editar)
        this.txtCarrera.setText(usuarioModificacion.getCarrera());
        
        // Mostrar la contraseña actual (este SÍ se puede editar)
        this.txtContrasena.setText(usuarioModificacion.getContrasena());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        lblCarrera = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCarrera = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JPasswordField();
        lblContrasena = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConfirmar.setText("Confirmar");

        btnCancelar.setText("Cancelar");

        lblNombre.setText("Nuevo nombre");

        lblCarrera.setText("Nueva Carrera");

        lblCodigo.setText("Codigo");

        txtCodigo.setEnabled(false);

        lblContrasena.setText("Contraseña");

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(lblCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCarrera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addGap(18, 18, 18)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNombre)
                .addGap(18, 18, 18)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCarrera)
                .addGap(18, 18, 18)
                .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblContrasena)
                .addGap(18, 18, 18)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnConfirmar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ========================================================================
    // EVENTOS DE BOTONES (Código que se ejecuta al hacer clic)
    // ========================================================================
    
    /**
     * EVENTO: Click en botón "Confirmar"
     * -----------------------------------------------------------------------
     * Se ejecuta cuando el usuario hace clic en el botón "Confirmar".
     * 
     * FLUJO:
     * 1. Validar que los campos no estén vacíos
     * 2. Mostrar un diálogo de confirmación
     * 3. Si el usuario confirma, actualizar el objeto Usuario
     * 4. Guardar los cambios en el archivo
     * 5. Cerrar esta ventana
     */
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // PASO 1: Validar que todos los campos estén llenos
        if (!validarCampos()) {
            return; // Si la validación falla, salir del método
        }
        
        // PASO 2: Obtener los valores de los campos
        String nombre = txtNombre.getText().trim();
        String carrera = txtCarrera.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        
        // PASO 3: Mostrar diálogo de confirmación
        // showConfirmDialog retorna YES_OPTION si el usuario hace clic en "Sí"
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de modificar los datos del usuario?\n\n" +
            "Código: " + usuarioModificacion.getCodigo() + "\n" +
            "Nombre: " + nombre + "\n" +
            "Carrera: " + carrera,
            "Confirmar modificación", 
            JOptionPane.YES_NO_OPTION);
        
        // PASO 4: Verificar si el usuario confirmó
        if (confirmacion != JOptionPane.YES_OPTION) {
            return; // Si no confirmó, salir del método
        }
        
        // PASO 5: Actualizar los datos del usuario en memoria
        // IMPORTANTE: usuarioModificacion es una REFERENCIA al objeto en Inicio.usuarios
        // Al modificar este objeto, automáticamente se modifica en el arreglo global
        usuarioModificacion.setNombre(nombre);
        usuarioModificacion.setCarrera(carrera);
        usuarioModificacion.setContrasena(contrasena);
        
        // PASO 6: Guardar los cambios en el archivo
        guardarCambios();
        
        // PASO 7: Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, 
            "Usuario modificado exitosamente", "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // PASO 8: Cerrar esta ventana
        this.dispose();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    /**
     * EVENTO: Click en botón "Cancelar"
     * -----------------------------------------------------------------------
     * Se ejecuta cuando el usuario hace clic en el botón "Cancelar".
     * 
     * Pregunta al usuario si está seguro de cancelar (para evitar pérdida
     * accidental de cambios).
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // Mostrar diálogo de confirmación
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de cancelar? Los cambios no se guardarán", 
            "Confirmar cancelación", 
            JOptionPane.YES_NO_OPTION);
        
        // Si el usuario confirma, cerrar la ventana
        if (confirmacion == JOptionPane.YES_OPTION) {
            this.dispose(); // Cerrar sin guardar
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

// ========================================================================
    // MÉTODOS DE VALIDACIÓN
    // ========================================================================
    
    /**
     * MÉTODO: validarCampos()
     * -----------------------------------------------------------------------
     * Valida que todos los campos obligatorios estén llenos.
     * 
     * @return true si todos los campos son válidos, false si hay algún error
     * 
     * ¿Qué hace trim()?
     * Elimina espacios en blanco al inicio y al final de un texto.
     * Ejemplo: "  hola  ".trim() -> "hola"
     * 
     * ¿Qué hace isEmpty()?
     * Retorna true si el texto está vacío (no tiene caracteres).
     */
    private boolean validarCampos() {
        // Obtener el texto de los campos (sin espacios al inicio/final)
        String nombre = txtNombre.getText().trim();
        String carrera = txtCarrera.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        
        // Verificar que ningún campo esté vacío
        if (nombre.isEmpty() || carrera.isEmpty() || contrasena.isEmpty()) {
            // Mostrar mensaje de advertencia
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios", "Validación", 
                JOptionPane.WARNING_MESSAGE);
            return false; // Validación fallida
        }
        
        return true; // Validación exitosa
    }
    
    // ========================================================================
    // MÉTODOS DE LÓGICA DE NEGOCIO
    // ========================================================================
    
    /**
     * MÉTODO: guardarCambios()
     * -----------------------------------------------------------------------
     * Guarda los cambios en el archivo y recarga la tabla en la ventana padre.
     * 
     * Este es un ejemplo de "comunicación entre ventanas":
     * - Esta ventana (ventana hija) llama a un método de GestionesAdmin (ventana padre)
     * - Esto actualiza la tabla para reflejar los cambios
     */
    private void guardarCambios() {
        if (ventanaPadre != null) {
            // Llamar al método de la ventana padre para guardar y recargar
            ventanaPadre.guardarYRecargarOperadores();
        }
    }
    
    // ========================================================================
    // ZONA DE CÓDIGO GENERADO POR NETBEANS
    // ========================================================================
    // ADVERTENCIA: NO modificar el código entre estos comentarios
    // NetBeans lo regenera automáticamente cuando se edita el formulario

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
