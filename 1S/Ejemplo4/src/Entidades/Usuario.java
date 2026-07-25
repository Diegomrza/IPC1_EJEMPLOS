package Entidades;

public class Usuario {
    private String Carnet;
    private String Nombre;
    private String Carrera;
    private String Contrasena;
    
    private Libro[] libros = new Libro[20];
    private int LibrosPrestados;

    public Usuario(String Carnet, String Nombre, String Carrera, String Contrasena) {
        this.Carnet = Carnet;
        this.Nombre = Nombre;
        this.Carrera = Carrera;
        this.Contrasena = Contrasena;
    }
    
    // =============== Metodos
    public boolean ContrasenaCorrecta(String contrasena) {
        if (contrasena != null && this.Contrasena.equals(contrasena)) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return "Usuario con nombre: " + this.Nombre + " y carnet: " + this.Carnet;
    }

    // Getters
    public String getCarnet() {
        return Carnet;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCarrera() {
        return Carrera;
    }

    public String getContrasena() {
        return Contrasena;
    }

    // Setters
    public void setCarnet(String Carnet) {
        this.Carnet = Carnet;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    
}
