package Entidades;

public class Libro {
    private int CodigoInterno;
    private String ISBN;
    private String Titulo;
    private String Autor;
    private String Genero;
    private int AnioPublicacion;
    private int cantidad;

    // Constructor
    public Libro(int CodigoInterno, String ISBN, String Titulo, String Autor, String Genero, int AnioPublicacion) {
        this.CodigoInterno = CodigoInterno;
        this.ISBN = ISBN;
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.Genero = Genero;
        this.AnioPublicacion = AnioPublicacion;
    }

    // Getters
    public int getAnioPublicacion() {
        return AnioPublicacion;
    }

    public int getCodigoInterno() {
        return CodigoInterno;
    }
    
    public String getTitulo() {
        return Titulo;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public String getGenero() {
        return Genero;
    }
    
    public String getAutor() {
        return Autor;
    }
    
    // Setters
    public void setAnioPublicacion(int AnioPublicacion) {
        this.AnioPublicacion = AnioPublicacion;
    }

    public void setCodigoInterno(int CodigoInterno) {
        this.CodigoInterno = CodigoInterno;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }
}
