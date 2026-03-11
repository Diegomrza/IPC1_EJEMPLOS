package Entidades;

import java.util.Date;

public class Prestamo {
	private int Id;
	private String CodigoEstudiante; // String para coincidir con el carne del estudiante, ej. "20250001"
	private String CodigoLibro; // String para coincidir con LIB001, etc
	private Date Fecha;
	private Date FechaLimite;
	private Date FechaDevolucion; // Se registra al devolver el libro, inicialmente null
	private int Estado; // 1 = Activo, 2 = Devuelto, 3 = Vencido

	public Prestamo(int Id, String CodigoEstudiante, String CodigoLibro, Date Fecha, Date FechaLimite) {
		this.Id = Id;
		this.CodigoEstudiante = CodigoEstudiante;
		this.CodigoLibro = CodigoLibro;
		this.Fecha = Fecha;
		this.FechaLimite = FechaLimite;
		this.Estado = 1; // Siempre inicia como Activo al crear un nuevo préstamo
	}

	/**
	 * Verifica si el préstamo está vencido (Activo y fecha actual > fecha límite)
	 * @return true si está vencido, false si no lo está
	 */
	public boolean EstaVencido() {
		return Estado == 1 && new Date().after(FechaLimite);
	}

	/**
	 * Calcula los días de atraso si el préstamo está vencido. Si no está vencido, retorna 0.
	 * @return Número de días de atraso o 0 si no está vencido
	 */
	public int getDiasAtraso() {
		if (!EstaVencido())
			return 0;
		long diferencia = new Date().getTime() - FechaLimite.getTime();
		return (int) (diferencia / (1000 * 60 * 60 * 24));
	}

	/**
	 * Marca el préstamo como devuelto, actualizando el estado y registrando la fecha de devolución.
	 */
	public void Devolver() {
		this.Estado = 2;
		this.FechaDevolucion = new Date();
	}

	/**
	 * Convierte el préstamo a una línea de texto para almacenamiento en archivo, con formato:
	 * Id;CodigoEstudiante;CodigoLibro;Fecha;FechaLimite;FechaDevolucion;Estado
	 * Las fechas se almacenan como milisegundos desde epoch. Si FechaDevolucion es null, se almacena como "null".
	 * @return La representación en línea del préstamo para almacenamiento.
	 */
	public String ToLinea() {
		return Id + ";" + CodigoEstudiante + ";" + CodigoLibro + ";" +
				Fecha.getTime() + ";" + FechaLimite.getTime() + ";" +
				(FechaDevolucion != null ? FechaDevolucion.getTime() : "null") + ";" + Estado;
	}

	// Getters
	public int getId() { return Id; }
	public String getCodigoEstudiante() { return CodigoEstudiante; }
	public String getCodigoLibro() { return CodigoLibro; }
	public Date getFecha() { return Fecha; }
	public Date getFechaLimite() { return FechaLimite; }
	public Date getFechaDevolucion() { return FechaDevolucion; }
	public int getEstado() { return Estado; }
	public void setEstado(int Estado) { this.Estado = Estado; }

	// Setters
	public void setFechaDevolucion(Date fecha) { this.FechaDevolucion = fecha; }

	/**
	 * Obtiene el nombre del estado del préstamo para mostrar en la interfaz, considerando si está vencido o no.
	 * @return "Activo" si está activo y no vencido, "VENCIDO" si está activo y vencido, "Devuelto" si está devuelto, "Vencido" si está vencido, o "Desconocido" para otros estados.
	 */
	public String GetEstadoNombre() {
		switch (Estado) {
			case 1:
				return EstaVencido() ? "VENCIDO" : "Activo";
			case 2:
				return "Devuelto";
			case 3:
				return "Vencido";
			default:
				return "Desconocido";
		}
	}
}