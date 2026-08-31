package models;

/**
 *
 * @author Diegomrza
 */
public class PersonaModel {
    private int Id;
    private String Usuario;
    private String Password;
    private int Edad;
    private String Carnet;

    public PersonaModel(int Id, String Usuario, String Password, int Edad, String Carnet) {
        this.Usuario = Usuario;
        this.Password = Password;
        this.Edad = Edad;
        this.Carnet = Carnet;
    }
    
    // Constructor
    public PersonaModel() {
        this(0, "", "", 0, "");
    }
    
    // Funcion
    public PersonaModel ObtenerInstancia() {
        return this;
    }

    // Funcion
    public int getId() {
        return Id;
    }

    // Metodo
    public void setId(int Id) {
        this.Id = Id;
        return;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        if (Usuario.length() > 10) {
            return;
        }
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getEdad() {
        int Edad = 0;
        return this.Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getCarnet() {
        return Carnet;
    }

    public void setCarnet(String Carnet) {
        this.Carnet = Carnet;
    }
    
    public void DesasignarmeIPC1() {
        
    }
}
