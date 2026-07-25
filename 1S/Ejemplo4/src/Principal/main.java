package Principal;

import Entidades.Libro;
import Entidades.Usuario;
import Utilidades.Archivo;
import VentanasAutenticacion.Login;

public class main {    
    // Arreglo de Usuarios
    public static Usuario[] usuarios = new Usuario[100];
    
    // Arreglo de Libros
    public static Libro[] libros = new Libro[100];
    
    // Objeto para el manejo de archivos
    public static Archivo bitacora;
    
    public static void main(String[] args) {        
        // Registramos el primer usuario
        Usuario usuario = new Usuario("admin", "admin", "Ing. Sistemas", "admin");
        usuarios[0] = usuario;
        
        // Ruta del archivo
        String rutaBitacora = "C:\\Users\\Diego\\Desktop\\USAC\\2026\\1S\\IPC1\\Ejemplos\\IPC1_EJEMPLOS\\Ejemplo4";
        
        // Nombre del archivo
        String nombreBitacora = "bitacora.txt";
        
        // Creacion del objeto para el manejo del archivo
        bitacora = new Archivo(rutaBitacora, nombreBitacora);
        
        if (bitacora.LeerArchivo()) {
            System.out.println("Archivo leido con exito.");
        }
        
        // Creacion de ventana para inicio de sesion
        Login ventanaLogin = new Login();
        
        // Mostramos la ventana de inicio de sesion
        ventanaLogin.setVisible(true);
    }
    
    // Agrega un usuario en la primer posicion disponible (null) que encuentre
    // Retorna true si logro insertar y false si no encontró espacio disponible
    public static boolean AgregarUsuario(Usuario usuarioNuevo) {        
        for(int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = usuarioNuevo;
                return true;
            }
        }        
        return false;
    }
    
    // Obtiene un usuario por medio de su carnet, si no existe retorna null
    public static Usuario ObtenerUsuario(String username) {
        for (Usuario user: usuarios) {
            if (user.getCarnet().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
