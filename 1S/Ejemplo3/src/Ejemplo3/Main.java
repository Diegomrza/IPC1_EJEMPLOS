package Ejemplo3;

import Archivos.Archivo;
import Entidades.Persona;
import Entidades.TarjetaCredito;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Arreglo de tipo persona de tamano 10
        Persona[] usuarios = new Persona[10];
        
        // Leer entradas de usuario por la consola
        Scanner sc = new Scanner(System.in);
        
        // Mostrando mensaje en consola
        System.out.println("Ingrese su nombre: ");
        
        // Capturando lo que ingrese el usuario
        String nombre = sc.nextLine();
        
        // Creando/Instanciando un objeto de tipo tarjeta de credito
        // Con la palabra reservada new
        TarjetaCredito tc = new TarjetaCredito(1234, nombre, 0);
        
        // Creando/Instanciando un objeto de tipo persona
        // Con dos atributos enviado por constructor
        Persona p = new Persona("Diego", 28);
        
        // Modificando datos del objeto persona por medio de metodos setter
        p.setNombre(nombre);
        p.setEdad(28);
        p.setTarjeta(tc);
        
        // Insertando el usuario en la posicion 0 del arreglo de usuarios
        usuarios[0] = p;
       
        // Recorriendo el arreglo de usuarios
        for(int i = 0; i < usuarios.length; i++) {
            
            // Verificando que la posicion actual sea diferente de null
            // Para evitar error de nullPointerException
            if (usuarios[i] != null) {
                // Imprimiendo datos del objeto usuario actual
                System.out.println("usuario: " + usuarios[i]);
                System.out.println("Tarjeta: " + usuarios[i].getTarjeta());
                
                // Ejecutando metodos del objeto usuario
                usuarios[i].Caminar();
                usuarios[i].Hablar();
                usuarios[i].Gastar();
            }            
        }
        
        System.out.println("Manejo de archivos");
        LeerYEscribirArchivo();
    }
    
    public static void LeerYEscribirArchivo() {
        String ruta = "C:\\Users\\Diego\\Desktop\\USAC\\2026\\1S\\IPC1\\Ejemplos\\IPC1_EJEMPLOS\\Ejemplo3";
        String nombre = "archivo.txt";
        
        // Creando objeto tipo archivo
        Archivo a = new Archivo(ruta, nombre);
        
        // Leemos el contenido del archivo
        a.LeerArchivo();
        
        System.out.println("===== Imprimiendo el contenido del archivo =====");
        // imprimiendo cada linea leida del archivo
        for(String linea: a.getContenido()) {
            System.out.println(linea);
        }
        
        // Agregamos una linea nueva en el final del archivo
        a.AgregarLinea("***** Fila nueva *****");
        
        // Leemos de nuevo el archivo
        a.LeerArchivo();
        
        System.out.println("===== Imprimiendo el contenido del archivo =====");
        // imprimiendo cada linea leida del archivo
        for(String linea: a.getContenido()) {
            System.out.println(linea);
        }
        
        // creamos un arreglo de strings para reescribir el contenido del archivo
        String[] nuevoContenido = { "Prueba", "de", "ingreso", "de", "contenido" };
        
        // Enviamos el nuevo contenido para ser reescrito en el archivo
        a.EscribirArchivo(nuevoContenido);
        
        // Leemos de nuevo el archivo
        a.LeerArchivo();
        
        System.out.println("===== Imprimiendo el contenido del archivo =====");
        // imprimiendo cada linea leida del archivo
        for(String linea: a.getContenido()) {
            System.out.println(linea);
        }
    }
}
