package Entidades;

/*
Atributos de acceso
public => cualquiera puede acceder desde cualquier parte del programa
private => sus atributos solo es accesible desde dentro la propia clase
protected => accesible dentro del mismo paquete
*/

public class Persona {    
    
    // Atributos de datos primitivos
    private String nombre;
    private int edad;
    private short ojos;
    private short manos;
    private short pies;
    private int telefono;
    
    // Atributo de tipo TarjetaCredito
    private TarjetaCredito tarjeta;

    // Constructor
    public Persona(String nombre, int edad, short ojos, short manos, short pies, int telefono, TarjetaCredito tarjeta) {
        this.nombre = nombre;
        this.edad = edad;
        this.ojos = ojos;
        this.manos = manos;
        this.pies = pies;
        this.telefono = telefono;
        this.tarjeta = tarjeta;
    }
    
    // Constructor con 2 parametros
    public Persona(String apodo, int edad) {       
        this.nombre = apodo;
        this.edad = edad;
    }
    
    // Constructor con 1 parametro
    public Persona(String nombre) {
        System.out.println("Constructor");
    }
    
    // Constructor vacio y sin atributos
    public Persona() {}
   
    // Sobreescribiendo el metodo toString(); con la notacion @Override para modificar su comportamiento
    @Override
    public String toString() {
        return "Tu nombre es: " + nombre + " y tu edad es: " + edad;
    }

    // ================================== Metodos ==================================
    public void Caminar() {
        System.out.println("Estoy caminando");
    }
    
    public void Hablar() {
        System.out.println("Estoy hablando");
    }
    
    public void Gastar() {
        System.out.println("me quedo sin dinero :C");
    }

    // ============================= Getters =============================
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public short getOjos() {
        return ojos;
    }

    public short getManos() {
        return manos;
    }

    public short getPies() {
        return pies;
    }

    public int getTelefono() {
        return telefono;
    }

    public TarjetaCredito getTarjeta() {
        return tarjeta;
    }
    
    // ============================= Setters =============================
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setOjos(short ojos) {
        this.ojos = ojos;
    }

    public void setManos(short manos) {
        this.manos = manos;
    }

    public void setPies(short pies) {
        this.pies = pies;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = tarjeta;
    }
    
}
