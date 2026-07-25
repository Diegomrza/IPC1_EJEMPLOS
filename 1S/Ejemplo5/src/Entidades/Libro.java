package Entidades;

public class Libro {
  private String Codigo; // Unico, ej: LIB001
  private String ISBN; // 10 o 13 digitos, unico
  private String Titulo;
  private String Autor;
  private String Genero;
  private int Anio;
  private int CantidadTotal;
  private int CantidadDisponible;

  public Libro(String Codigo, String ISBN, String Titulo, String Autor, String Genero, int Anio, int CantidadTotal) {
    this.Codigo = Codigo;
    this.ISBN = ISBN;
    this.Titulo = Titulo;
    this.Autor = Autor;
    this.Genero = Genero;
    this.Anio = Anio;
    this.CantidadTotal = CantidadTotal;
    this.CantidadDisponible = CantidadTotal; // Se inicializa igual al total
  }

  // Reduce disponibles al prestar
  public boolean Prestar() {
    if (CantidadDisponible > 0) {
      CantidadDisponible--;
      return true;
    }
    return false;
  }

  /**
   * Aumenta disponibles al devolver, pero no puede superar el total
   */
  public void Devolver() {
    if (CantidadDisponible < CantidadTotal) {
      CantidadDisponible++;
    }
  }

  /**
   * Calcula la cantidad prestada restando disponibles a total
   * 
   * @return
   */
  public int GetCantidadPrestada() {
    return CantidadTotal - CantidadDisponible;
  }

  /**
   * Verifica si hay copias disponibles para prestar
   * 
   * @return true si hay disponibles, false si no
   */
  public boolean EstaDisponible() {
    return CantidadDisponible > 0;
  }

  /**
   * Verifica si tiene préstamos activos (para no poder eliminarlo)
   * 
   * @return true si tiene préstamos activos, false si no
   */
  public boolean TienePrestamosActivos() {
    return GetCantidadPrestada() > 0;
  }

  /**
   * Permite cambiar la cantidad total, pero no puede reducirse por debajo de lo
   * prestado
   * 
   * @param nuevaCantidad
   * @return true si se actualizó correctamente, false si no se pudo actualizar
   *         por tener préstamos activos que exceden la nueva cantidad total
   */
  public boolean SetCantidadTotal(int nuevaCantidad) {
    if (nuevaCantidad < GetCantidadPrestada()) {
      return false; // No se puede reducir por debajo de lo prestado
    }
    int diferencia = nuevaCantidad - CantidadTotal;
    CantidadTotal = nuevaCantidad;
    CantidadDisponible += diferencia;
    return true;
  }

  /**
   * Busca parcialmente por título (insensible a mayúsculas)
   * 
   * @param texto
   * @return true si el texto está contenido en el título, false si no
   */
  public boolean ContieneTitulo(String texto) {
    return Titulo.toLowerCase().contains(texto.toLowerCase());
  }

  /**
   * Busca parcialmente por autor (insensible a mayúsculas)
   * 
   * @param texto
   * @return true si el texto está contenido en el autor, false si no
   */
  public boolean ContieneAutor(String texto) {
    return Autor.toLowerCase().contains(texto.toLowerCase());
  }

  // Getters
  public String getCodigo() {
    return Codigo;
  }

  public String getISBN() {
    return ISBN;
  }

  public String getTitulo() {
    return Titulo;
  }

  public String getAutor() {
    return Autor;
  }

  public String getGenero() {
    return Genero;
  }

  public int getAnio() {
    return Anio;
  }

  public int getCantidadTotal() {
    return CantidadTotal;
  }

  public int getCantidadDisponible() {
    return CantidadDisponible;
  }

  // Setters
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

  public void setAnio(int Anio) {
    this.Anio = Anio;
  }

  /**
   * Representación en cadena del libro, mostrando código, título, autor y
   * cantidad disponible
   * 
   * @return String con la información del libro
   */
  @Override
  public String toString() {
    return Codigo + " | " + Titulo + " | " + Autor + " | Disponibles: " + CantidadDisponible;
  }
}