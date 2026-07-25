package Entidades;

public class TarjetaCredito {
    // Propiedades de tipo primitivo
    private int numero;
    private String duenio;
    private double saldo;        

    // Constructor
    public TarjetaCredito(int numero, String duenio, double saldo) {
        this.numero = numero;
        this.duenio = duenio;
        this.saldo = saldo;
    }
        
    // Sobreescribiendo el metodo toString(); con la notacion @Override para modificar su comportamiento
    @Override
    public String toString() {
        return "Numero: " + this.numero + " saldo: " + this.saldo;
    }
    
    // ================================== Getters ==================================
    public int getNumero() {
        return numero;
    }

    public String getDuenio() {
        return duenio;
    }

    public double getSaldo() {
        return saldo;
    }
    
    // ================================== Setters ==================================
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}
