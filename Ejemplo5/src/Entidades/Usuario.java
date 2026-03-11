package Entidades;

import java.util.Date;

public class Usuario {
	private int Rol; // 1 = Admin, 2 = Operador, 3 = Estudiante
	private String Codigo;
	private String Contrasena;
	private String Nombre;
	private String Carrera;

	// Cada usuario puede tener hasta 3 préstamos activos y un historial de hasta
	// 100 préstamos
	private Prestamo[] prestamos = new Prestamo[3];
	private Prestamo[] historialPrestamos = new Prestamo[100];

	// Contador para el historial, para saber cuántos préstamos se han registrado en
	// el historial
	private int totalHistorial = 0;

	public Usuario(int Rol, String Codigo, String Contrasena, String Nombre, String Carrera) {
		this.Rol = Rol;
		this.Codigo = Codigo;
		this.Contrasena = Contrasena;
		this.Nombre = Nombre;
		this.Carrera = Carrera;
	}

	/**
	 * Verifica si el usuario tiene préstamos activos (estado 1) en su arreglo de préstamos.
	 * @return true si tiene al menos un préstamo activo, false si no tiene ninguno
	 */
	public boolean PrestamosActivo() {
		for (Prestamo p : prestamos) {
			if (p != null && p.getEstado() == 1)
				return true;
		}
		return false;
	}

	/**
	 * Cuenta cuántos préstamos activos (estado 1) tiene el usuario en su arreglo de préstamos.
	 * @return el número de préstamos activos que tiene el usuario
	 */
	public int ContarPrestamosActivos() {
		int count = 0;
		for (Prestamo p : prestamos) {
			if (p != null && p.getEstado() == 1)
				count++;
		}
		return count;
	}

	/**
	 * Verifica si el usuario puede realizar un nuevo préstamo, es decir, si tiene menos de 3 préstamos activos.
	 * @return true si puede prestar, false si no puede prestar porque ya tiene 3 préstamos activos
	 */
	public boolean PuedePrestar() {
		for (Prestamo p : prestamos) {
			if (p == null)
				return true;
		}
		return false;
	}

	/**
	 * Agrega un nuevo préstamo al arreglo de préstamos del usuario, buscando la primera posición disponible (null).
	 * Si se agrega correctamente, devuelve true. Si el usuario ya tiene 3 préstamos activos y no hay espacio para el nuevo préstamo, devuelve false.
	 * Nota: Este método no verifica si el préstamo es válido o si el usuario puede prestar, solo intenta agregarlo al arreglo.
	 * @param nuevo
	 * @return true si el préstamo se agregó correctamente, false si no se pudo agregar porque el usuario ya tiene 3 préstamos activos
	 */
	public boolean AgregarPrestamo(Prestamo nuevo) {
		for (int i = 0; i < prestamos.length; i++) {
			if (prestamos[i] == null) {
				prestamos[i] = nuevo;
				return true;
			}
		}
		return false;
	}

	/**
	 * Busca el préstamo con el ID especificado en el arreglo de préstamos del usuario. Si lo encuentra, cambia su estado a 2 (devuelto), lo agrega al historial de préstamos (si hay espacio) y lo elimina del arreglo de préstamos (poniéndolo a null).
	 * @param idPrestamo
	 * @return true si se encontró el préstamo y se devolvió correctamente, false si no se encontró ningún préstamo con el ID especificado
	 */
	public boolean DevolverPrestamo(int idPrestamo) {
		for (int i = 0; i < prestamos.length; i++) {
			if (prestamos[i] != null && prestamos[i].getId() == idPrestamo) {
				prestamos[i].setEstado(2);
				if (totalHistorial < historialPrestamos.length) {
					historialPrestamos[totalHistorial] = prestamos[i];
					totalHistorial++;
				}
				prestamos[i] = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica si el usuario tiene préstamos vencidos, es decir, préstamos con estado 1 (activo) cuya fecha límite es anterior a la fecha actual. Si encuentra un préstamo vencido, cambia su estado a 3 (vencido) y devuelve true. Si no encuentra ningún préstamo vencido, devuelve false.
	 */
	public boolean TienePrestamosVencidos() {
		Date hoy = new Date();
		for (Prestamo p : prestamos) {
			if (p != null && p.getEstado() == 1 && p.getFechaLimite().before(hoy)) {
				p.setEstado(3);
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica si el usuario tiene un préstamo activo (estado 1) con el código de libro especificado.
	 * @param codigoLibro
	 * @return true si el usuario tiene un préstamo activo con el código de libro especificado, false si no tiene ningún préstamo activo con ese código de libro
	 */
	public boolean TieneLibroPrestado(String codigoLibro) {
		for (Prestamo p : prestamos) {
			if (p != null && p.getCodigoLibro().equals(codigoLibro) && p.getEstado() == 1)
				return true;
		}
		return false;
	}

	/**
	 * Verifica si la contraseña proporcionada coincide con la contraseña del usuario. Devuelve true si coinciden, false si no coinciden.
	 * @param contrasena
	 * @return true si la contraseña proporcionada coincide con la contraseña del usuario, false si no coinciden
	 */
	public boolean ValidarContrasena(String contrasena) {
		return this.Contrasena.equals(contrasena);
	}

	/**
	 * Devuelve el nombre del rol del usuario según su valor numérico (1 = Administrador, 2 = Operador, 3 = Estudiante). Si el valor del rol no coincide con ninguno de los casos, devuelve "Desconocido".
	 * @return el nombre del rol del usuario según su valor numérico, o "Desconocido" si el valor del rol no es válido
	 */
	public String GetRolNombre() {
		switch (Rol) {
			case 1:
				return "Administrador";
			case 2:
				return "Operador";
			case 3:
				return "Estudiante";
			default:
				return "Desconocido";
		}
	}

	/**
	 * Devuelve un arreglo con el historial de préstamos del usuario, ordenado desde el más reciente hasta el más antiguo. El arreglo contiene solo los préstamos que se han registrado en el historial (hasta totalHistorial), y no incluye los préstamos activos.
	 * @return un arreglo con el historial de préstamos del usuario, ordenado desde el más reciente hasta el más antiguo
	 */
	public Prestamo[] GetHistorial() {
		Prestamo[] resultado = new Prestamo[totalHistorial];
		for (int i = 0; i < totalHistorial; i++) {
			resultado[i] = historialPrestamos[totalHistorial - 1 - i];
		}
		return resultado;
	}

	// Getters
	public int getTotalHistorial() { return totalHistorial; }
	public int getRol() { return Rol; }
	public String getCodigo() { return Codigo; }
	public String getContrasena() { return Contrasena; }
	public String getNombre() { return Nombre; }
	public String getCarrera() { return Carrera; }
	public Prestamo[] getPrestamos() { return prestamos; }

	// Setters
	public void setContrasena(String Contrasena) { this.Contrasena = Contrasena; }
	public void setNombre(String Nombre) { this.Nombre = Nombre; }
	public void setCarrera(String Carrera) { this.Carrera = Carrera; }

	/**
	 * Devuelve una representación en cadena del usuario, que incluye su rol, nombre, código y el número de préstamos activos que tiene. El formato es: "[Rol] Nombre | Código | Prestamos activos: X", donde Rol es el nombre del rol del usuario, Nombre es su nombre, Código es su código de usuario y X es el número de préstamos activos que tiene.
	 * @return una representación en cadena del usuario con su rol, nombre, código y número
	 */
	@Override
	public String toString() {
		return "[" + GetRolNombre() + "] " + Nombre + " | " + Codigo +
				" | Prestamos activos: " + ContarPrestamosActivos();
	}
}