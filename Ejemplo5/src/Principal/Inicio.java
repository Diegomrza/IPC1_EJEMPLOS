package Principal;

import Entidades.Usuario;
import Entidades.Libro;
import Entidades.Prestamo;
import Utilidades.Archivo;
import VentanasAuth.Login;

public class Inicio {
	// Arreglos globales del sistema
	public static Libro[] libros = new Libro[10];
	public static Usuario[] usuarios = new Usuario[10];
	public static Prestamo[] prestamos = new Prestamo[10];

	// Contadores para saber cuantos hay realmente en cada arreglo
	public static int totalLibros = 0;
	public static int totalUsuarios = 0;
	public static int totalPrestamos = 0;

	// Contador para generar IDs unicos de prestamos
	public static int contadorIdPrestamos = 1;

	// Manejo de archivos
	public static Archivo archivoBitacora;
	public static Archivo archivoCuentas;
	public static Archivo archivoPrestamos;
	public static Archivo archivoReportes;

	// Ruta relativa del proyecto para guardar los archivos, se obtiene de la propiedad del sistema "user.dir"
	private static final String RUTA = System.getProperty("user.dir");

	// Usuario que ha iniciado sesion actualmente, se establece en null al iniciar el programa
	public static Usuario usuarioActual;

	public static void main(String[] args) {
		CrearArchivosIniciales();
		CrearAdmin();
		CargarDatos();
		Login lg = new Login();
		lg.setVisible(true);

		System.out.println(totalUsuarios);
		for (Usuario u : usuarios) {
			System.out.println(u);
		}
	}

	/**
	 * Crea los archivos necesarios para el funcionamiento del programa, si ya
	 * existen no los sobreescribe
	 */
	private static void CrearArchivosIniciales() {
		String rutaProyecto = RUTA;
		archivoBitacora = new Archivo(rutaProyecto, "bitacora.txt");
		archivoBitacora.CrearArchivo();
		archivoBitacora.AgregarLinea("InicioPrograma");

		archivoCuentas = new Archivo(rutaProyecto, "cuentas.txt");
		archivoCuentas.CrearArchivo();

		archivoPrestamos = new Archivo(rutaProyecto, "prestamos.txt");
		archivoPrestamos.CrearArchivo();

		// Reportes se guardaran en una carpeta llamada "reportes" dentro del proyecto
		archivoReportes = new Archivo(rutaProyecto + "reportes", "reporte_usuarios.html");
		archivoReportes.CrearArchivo();
	}

	/**
	 * Crea un usuario administrador por defecto con codigo "admin" y contraseña
	 * "admin", si ya existe no lo sobreescribe
	 */
	public static void CrearAdmin() {
		usuarios[0] = new Usuario(1, "admin", "admin", "Administrador", "");
		totalUsuarios = 1;
	}

	/**
	 * Carga los datos de los archivos de cuentas y prestamos al iniciar el programa, si los archivos no se pueden leer o no tienen el formato correcto, simplemente se omiten y se continua
	 */
	public static void CargarDatos() {
		CargarCuentas();
		CargarPrestamos();
	}

	/**
	 * Lee el archivo de cuentas y carga los usuarios al arreglo de usuarios, si el archivo no se puede leer o no tiene el formato correcto, simplemente se omite y se continua
	 */
	private static void CargarCuentas() {
		// Si al leer el archivo nos retorna false salimos
		if (!archivoCuentas.LeerArchivo())
			return;

		// Obtenemos el contenido del archivo leido
		String[] lineas = archivoCuentas.getContenido();

		// Si lineas es null significa que el archivo no se leyo correctamente
		if (lineas == null)
			return;

		for (String linea : lineas) {
			// Formato: rol;codigo;contrasena;nombre;carrera
			String[] partes = linea.split(";");

			// Si una linea tiene menos elementos continuamos con el siguiente
			if (partes.length < 5)
				continue;

			int rol = Integer.parseInt(partes[0]);
			String codigo = partes[1];
			String contrasena = partes[2];
			String nombre = partes[3];
			String carrera = partes[4];

			// Ingresamos al usuario al arreglo de usuarios
			Usuario temporal = new Usuario(rol, codigo, contrasena, nombre, carrera);

			usuarios[totalUsuarios] = temporal;

			System.out.println(usuarios[totalUsuarios]);

			// Incrementamos la cantidad de usuarios en el sistema
			totalUsuarios++;
		}
	}

	/**
	 * Lee el archivo de prestamos y carga los prestamos al arreglo de prestamos, si el archivo no se puede leer o no tiene el formato correcto, simplemente se omite y se continua
	 */
	private static void CargarPrestamos() {
		// Si al leer el archivo nos retorna false salimos
		if (!archivoPrestamos.LeerArchivo())
			return;

		// Obtenemos el contenido del archivo leido
		String[] lineas = archivoPrestamos.getContenido();

		// Si lineas es null significa que el archivo no se leyo correctamente
		if (lineas == null)
			return;

		for (String linea : lineas) {
			// Formato:
			// id;codigoEstudiante;codigoLibro;fechaMs;fechaLimiteMs;fechaDevMs;estado
			String[] partes = linea.split(";");

			// Si una linea tiene menos elementos continuamos con el siguiente
			if (partes.length < 7)
				continue;

			int id = Integer.parseInt(partes[0]);
			String codigoEstudiante = partes[1];
			String codigoLibro = partes[2];
			java.util.Date fecha = new java.util.Date(Long.parseLong(partes[3]));
			java.util.Date fechaLim = new java.util.Date(Long.parseLong(partes[4]));
			int estado = Integer.parseInt(partes[6]);

			// Creamos el objeto prestamo
			Prestamo p = new Prestamo(id, codigoEstudiante, codigoLibro, fecha, fechaLim);

			// Le establecemos el estado al prestamo
			p.setEstado(estado);

			// Si la fecha de devolucion es null calculamos la fecha
			if (!partes[5].equals("null")) {
				p.setFechaDevolucion(new java.util.Date(Long.parseLong(partes[5])));
			}

			// Ingresamos al prestamo al arreglo de prestamos
			prestamos[totalPrestamos] = p;
			System.out.println(prestamos[totalPrestamos]);

			// Actualizamos la cantidad de prestamos en el sistema
			totalPrestamos++;

			// Mantener el contador de IDs actualizado
			if (id >= contadorIdPrestamos) {
				contadorIdPrestamos = id + 1;
			}
		}
	}

	/**
	 * Agrega un nuevo usuario al sistema
	 * @param usuarioNuevo
	 * @return boolean indicando si se agrego el usuario correctamente o no
	 */
	public static boolean AgregarUsuario(Usuario usuarioNuevo) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] == null) {
				usuarios[i] = usuarioNuevo;
				return true;
			}
		}
		return false;
	}

	/**
	 * Busca un usuario por su codigo y lo retorna, si no se encuentra retorna null
	 * @param codigo del usuario a buscar
	 * @return Usuario encontrado o null si no se encuentra
	 */
	public static Usuario ObtenerUsuario(String codigo) {
		for (Usuario user : usuarios) {
			if (user.getCodigo().equals(codigo)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Modifica un usuario existente en el sistema, busca el usuario por su codigo y lo reemplaza por el nuevo.
	 * @param u Usuario con los datos modificados, el codigo debe ser el mismo que el usuario a modificar
	 * @return boolean indicando si se modifico el usuario correctamente o no
	 */
	public static boolean ModificarUsuario(Usuario u) {
		for (int i = 0; i < usuarios.length; i++) {
                    if (usuarios[i] != null && usuarios[i].getCodigo().equals(u.getCodigo())) {
                            usuarios[i] = u;
                            return true;
                    }
		}
		return false;
	}
}
