package Utilidades;

// crear archivo
import java.io.File;

// Leer archivo
import java.io.BufferedReader;
import java.io.FileReader;

// Escribir archivo
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Archivo {

	// Propiedades
	private String ruta; // C:/Users/Example/Archivos/IPC1
	private String nombre; // bitacora.txt
	private String[] contenido; // Almacena todas las lineas del archivo leido

	// Constructor => Inicializa valores de las propiedades
	public Archivo(String ruta, String nombre) {
		this.ruta = ruta;
		this.nombre = nombre;
	}

	// Crea el archivo en la ruta especificada si no existe
	public boolean CrearArchivo() {
		try {
			// Crear carpeta si no existe
			File carpeta = new File(this.ruta);
			if (!carpeta.exists()) {
				carpeta.mkdirs();
			}

			File f = new File(this.ruta + File.separator + this.nombre);
			if (!f.exists()) {
				return f.createNewFile(); // true si lo creo, false si ya existia
			}
			return true;
		} catch (IOException ex) {
			System.out.println("Error al crear archivo: " + ex.getMessage());
			return false;
		}
	}

	// Lee el contenido de un archivo y lo ingresa en el arreglo llamado contenido
	public boolean LeerArchivo() {
		File archivo = new File(this.ruta + File.separator + this.nombre);

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			// Contamos cuantas lineas tiene el archivo
			int contador = 0;
			while (br.readLine() != null) {
				contador++;
			}
			br.close();

			// Establecemos el tamanio de nuestro arreglo de lineas
			this.contenido = new String[contador];

			// Cerramos el primer bufferReader
			BufferedReader br2 = new BufferedReader(new FileReader(archivo));

			// Variable para leer la linea actual
			String linea;
			// Indice para llevar la linea actual
			int i = 0;

			while ((linea = br2.readLine()) != null) {
				this.contenido[i] = linea;
				i++;
			}

			// Cerramos el bufferReader;
			br2.close();

			return true;
		} catch (Exception ex) {
			this.contenido = new String[0];
			System.out.println("Error al leer archivo: " + ex.getMessage());
			return false;
		}
	}

	// Sobreescribe el contenido (Borra lo que tenia y coloca lo nuevo)
	public boolean EscribirArchivo(String[] data) {
		File archivo = new File(this.ruta + File.separator + this.nombre);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
			for (String linea : data) {
				if (linea != null) { // Evita escribir nulls si el arreglo no esta lleno
					bw.write(linea);
					bw.newLine();
				}
			}
			return true;
		} catch (Exception ex) {
			System.out.println("Error al escribir archivo: " + ex.getMessage());
			return false;
		}
	}

	// Escribe una linea nuev al final del archivo
	public boolean AgregarLinea(String linea) {
		File archivo = new File(ruta + File.separator + nombre);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {

			bw.write(linea);
			bw.newLine();

			return true;
		} catch (Exception e) {
			System.out.println("Error al agregar línea: " + e.getMessage());
			return false;
		}
	}

	// Getters
	public String getRuta() {
		return ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public String[] getContenido() {
		return contenido;
	}

	// Setters
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
