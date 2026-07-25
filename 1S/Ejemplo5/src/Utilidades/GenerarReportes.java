package Utilidades;

import Entidades.Usuario;
import Principal.Inicio;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * Clase para generar reportes en formato HTML
 * Ejemplo básico de cómo crear archivos HTML desde Java
 */
public class GenerarReportes {

	/**
	 * Genera un reporte HTML simple con la lista de usuarios
	 * Ejemplo de estructura HTML básica con tabla
	 */
	public static boolean generarReporteUsuarios() {
		// Configurar ruta del archivo
		String rutaProyecto = System.getProperty("user.dir");
		Inicio.archivoReportes.setRuta(rutaProyecto);
		Inicio.archivoReportes.setNombre("reporte_usuarios.html");
		Inicio.archivoReportes.CrearArchivo();

		// Estructura HTML básica
		Inicio.archivoReportes.AgregarLinea("<!DOCTYPE html>");
		Inicio.archivoReportes.AgregarLinea("<html>");
		Inicio.archivoReportes.AgregarLinea("<head>");
		Inicio.archivoReportes.AgregarLinea("    <meta charset='UTF-8'>");
		Inicio.archivoReportes.AgregarLinea("    <title>Reporte de Usuarios</title>");
		
		// Estilos básicos para que no se vea mal
		Inicio.archivoReportes.AgregarLinea("    <style>");
		Inicio.archivoReportes.AgregarLinea("        body { font-family: Arial; margin: 20px; }");
		Inicio.archivoReportes.AgregarLinea("        table { border-collapse: collapse; width: 100%; }");
		Inicio.archivoReportes.AgregarLinea("        th, td { border: 1px solid black; padding: 8px; text-align: left; }");
		Inicio.archivoReportes.AgregarLinea("        th { background-color: #ddd; }");
		Inicio.archivoReportes.AgregarLinea("    </style>");
		Inicio.archivoReportes.AgregarLinea("</head>");
		Inicio.archivoReportes.AgregarLinea("<body>");

		// Título
		Inicio.archivoReportes.AgregarLinea("    <h1>Lista de Usuarios</h1>");

		// Tabla con los usuarios
		Inicio.archivoReportes.AgregarLinea("    <table>");
		Inicio.archivoReportes.AgregarLinea("        <tr>");
		Inicio.archivoReportes.AgregarLinea("            <th>Código</th>");
		Inicio.archivoReportes.AgregarLinea("            <th>Nombre</th>");
		Inicio.archivoReportes.AgregarLinea("            <th>Carrera</th>");
		Inicio.archivoReportes.AgregarLinea("            <th>Rol</th>");
		Inicio.archivoReportes.AgregarLinea("        </tr>");

		// Recorrer usuarios y agregarlos a la tabla
		for (int i = 0; i < Inicio.totalUsuarios; i++) {
			Usuario user = Inicio.usuarios[i];
			if (user != null) {
                            Inicio.archivoReportes.AgregarLinea("        <tr>");
                            Inicio.archivoReportes.AgregarLinea("            <td>" + user.getCodigo() + "</td>");
                            Inicio.archivoReportes.AgregarLinea("            <td>" + user.getNombre() + "</td>");
                            Inicio.archivoReportes.AgregarLinea("            <td>" + user.getCarrera() + "</td>");
                            Inicio.archivoReportes.AgregarLinea("            <td>" + user.GetRolNombre() + "</td>");
                            Inicio.archivoReportes.AgregarLinea("        </tr>");
			}
		}

		Inicio.archivoReportes.AgregarLinea("    </table>");
		Inicio.archivoReportes.AgregarLinea("</body>");
		Inicio.archivoReportes.AgregarLinea("</html>");

		// Mostrar mensaje
		JOptionPane.showMessageDialog(null,
				"Reporte generado en:\n" + Inicio.archivoReportes.getRuta() + File.separator + Inicio.archivoReportes.getNombre(),
				"Éxito",
				JOptionPane.INFORMATION_MESSAGE);

		return true;
	}

	/**
	 * Genera un reporte HTML simple solo de operadores
	 */
	public static boolean generarReporteOperadores() {
		String rutaProyecto = System.getProperty("user.dir");
		Inicio.archivoReportes.setRuta(rutaProyecto);
		Inicio.archivoReportes.setNombre("reporte_operadores.html");
		Inicio.archivoReportes.CrearArchivo();

		// Estructura HTML básica
		Inicio.archivoReportes.AgregarLinea("<!DOCTYPE html>");
		Inicio.archivoReportes.AgregarLinea("<html>");
		Inicio.archivoReportes.AgregarLinea("<head>");
		Inicio.archivoReportes.AgregarLinea("    <meta charset='UTF-8'>");
		Inicio.archivoReportes.AgregarLinea("    <title>Reporte de Operadores</title>");
		Inicio.archivoReportes.AgregarLinea("    <style>");
		Inicio.archivoReportes.AgregarLinea("        body { font-family: Arial; margin: 20px; }");
		Inicio.archivoReportes.AgregarLinea("        table { border-collapse: collapse; width: 100%; }");
		Inicio.archivoReportes.AgregarLinea("        th, td { border: 1px solid black; padding: 8px; }");
		Inicio.archivoReportes.AgregarLinea("        th { background-color: #ddd; }");
		Inicio.archivoReportes.AgregarLinea("    </style>");
		Inicio.archivoReportes.AgregarLinea("</head>");
		Inicio.archivoReportes.AgregarLinea("<body>");
		Inicio.archivoReportes.AgregarLinea("    <h1>Lista de Operadores</h1>");
		Inicio.archivoReportes.AgregarLinea("    <table>");
		Inicio.archivoReportes.AgregarLinea("        <tr><th>Código</th><th>Nombre</th><th>Carrera</th></tr>");

		// Filtrar solo operadores (rol = 2)
		for (int i = 0; i < Inicio.totalUsuarios; i++) {
			Usuario user = Inicio.usuarios[i];
			if (user != null && user.getRol() == 2) {
				Inicio.archivoReportes.AgregarLinea("        <tr>");
				Inicio.archivoReportes.AgregarLinea("            <td>" + user.getCodigo() + "</td>");
				Inicio.archivoReportes.AgregarLinea("            <td>" + user.getNombre() + "</td>");
				Inicio.archivoReportes.AgregarLinea("            <td>" + user.getCarrera() + "</td>");
				Inicio.archivoReportes.AgregarLinea("        </tr>");
			}
		}

		Inicio.archivoReportes.AgregarLinea("    </table>");
		Inicio.archivoReportes.AgregarLinea("</body>");
		Inicio.archivoReportes.AgregarLinea("</html>");

		JOptionPane.showMessageDialog(null,
				"Reporte generado en:\n" + Inicio.archivoReportes.getRuta() + File.separator + Inicio.archivoReportes.getNombre());

		return true;
	}
}
