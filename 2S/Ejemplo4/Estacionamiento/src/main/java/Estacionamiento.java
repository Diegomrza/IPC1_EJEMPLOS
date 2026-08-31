import java.util.Scanner;

/**
 *
 * @author Diegomrza
 */
public class Estacionamiento {

    // Arreglo para almacenar el estacionamiento, en cada celda ira la placa ingresada
    public static String[][] Estacionamiento = new String[10][10];
    // Variable para ir acumulando los ingresos recibidos por cada estacionamiento correcto
    public static double Ingresos = 0.0;
    // Variable para almacenar el conteo de cuantos ingresos lleva actualmente el sistema
    public static int Conteo = 0;
    
    public static void main(String[] args) {
        // Llamada al metodo que llena el estacionamiento al inicio para que no sean nulos los espacios
        LlenarEstacionamientoDeLibres();

        // Llamada a la funcion que calcula la posicion en la que debe ir la entrada (aleatoria)
        GenerarEntradaSalida("E");
        // Llamada a la funcion que calcula la posicion en la que debe ir la salida (aleatoria)
        GenerarEntradaSalida("S");
        int opcion = -1;
        do {
            MostrarMenu();
            opcion = LecturaNumero("Ingrese una opcion:", false, 1, 7);
            switch (opcion) {
                case 1:
                    boolean ingresado = IngresarVehiculo();
                    if (ingresado) {
                        System.out.println("Vehiculo ingresado correctamente");
                    } else {
                        System.out.println("");
                    }
                    System.out.println("...");
                    break;
                case 2:
                    String placaRetirar = LecturaTexto("Ingrese la placa: ", false);
                    RetirarVehiculo(placaRetirar);
                    System.out.println("...");
                    break;
                case 3:
                    MostrarEstacionamiento();
                    break;
                case 4:
                    String placaBuscar = LecturaTexto("Ingrese la placa: ", false);
                    BuscarPlaca(placaBuscar, false);
                    break;
                case 5:
                    System.out.println("...");
                    break;
                case 6:
                    System.out.println("Ingresos: " + Ingresos);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
        } while (opcion != 7);
    }
    
    public static void MostrarMenu(){
        System.out.println("** SISTEMA DE ESTACIONAMIENTO **");
        System.out.println("1. Ingresar vehiculo");
        System.out.println("2. Retirar vehiculo");
        System.out.println("3. Mostrar estacionamiento");
        System.out.println("4. Buscar vehiculo por placa");
        System.out.println("5. Mostrar ruta mas corta entre entrada y salida");
        System.out.println("6. Mostrar ingresos");
        System.out.println("7. Salir");
        System.out.println("********************************");
    }
    
    public static void LlenarEstacionamientoDeLibres() {
        for(int i = 0; i < Estacionamiento.length; i++){            
            for(int j = 0; j < Estacionamiento[i].length; j++) {
                Estacionamiento[i][j] = "";
            }
        }
    }
    
    // Metodo para mostrar el estado actual del estacionamiento en conjunto con los bordes
    public static void MostrarEstacionamiento(){
        for(int i = 0; i < Estacionamiento.length; i++){
            boolean esBorde = false;
            // Si la fila es 0 o 9 es borde superior o inferior
            if (i == 0 || i == 9) {
                esBorde = true;
            }
            for(int j = 0; j < Estacionamiento[i].length; j++) {
                // Si es borde unicamente validamos si hay algo, si esta vacio es un =, sino es E o S
                if (esBorde) {
                    // Para entrada y salida
                    if (PosicionOcupada(i, j)) {
                        System.out.print(Estacionamiento[i][j]);
                    } else {
                        System.out.print("=");
                    }                    
                } else {
                    // Si la columna es 0 o 9 es un borde izquierdo o derecho
                    if (j == 0 || j == 9) {
                        // Para entrada y salida
                        if (PosicionOcupada(i, j)) {
                            System.out.print(Estacionamiento[i][j]);
                        } else {
                            System.out.print("=");
                        }
                        continue;
                    }
                    // Si no es borde pues es una placa, si esta ocupado pintamos A (Automovil) y sino pintamos L (Libre)
                    if (PosicionOcupada(i, j)) {
                        System.out.print("A");
                    } else {
                        System.out.print("L");
                    }
                }
            }
            System.out.println("");
        }
    }
    
    // Funcion para solicitar los datos necesarios para registrar un estacionamiento
    public static boolean IngresarVehiculo(){
        // Pedir la placa
        String placa = LecturaTexto("Ingrese la placa del vehiculo:", false);
        if (!PlacaValida(placa)) {
            System.out.println("Placa invalida, intente nuevamente");
            return false;
        }
        
        int posX = -1;
        int posY = -1;
        do {
            posX = LecturaNumero("Ingrese la fila: ", false, 1, 9);
            posY = LecturaNumero("Ingrese la columna: ", false, 1, 9);
            if (PosicionOcupada(posX, posY)) {
                posX = -1;
                posY = -1;
            }
        } while (posX == -1 || posY == -1);
        
        if (BuscarPlaca(placa, false)) {
            return false;
        }
        if (CobrarTarifa()) {
            Ingresos += 10;
        }
        Estacionamiento[posX][posY] = placa;
        
        return true;
    }
    
    // Metodo que calcula la posicion en la que debe generar entrada o salida (recibida por parametro)
    public static void GenerarEntradaSalida(String tipo) {
        boolean exito = false;
                
        do {
            int fila = GenerarAleatorio(0, 9);
            int columna = GenerarAleatorio(0, 9);

            if ((fila == 0 || fila == 9 || columna == 0 || columna == 9) &&
                !(fila == 0 && (columna == 0 || columna == 9)) &&
                !(fila == 9 && (columna == 0 || columna == 9))) {

                if (!PosicionOcupada(fila, columna)) {
                    Estacionamiento[fila][columna] = tipo;
                    System.out.println(tipo + " generado en [" + fila + "][" + columna + "]");
                    exito = true;
                }
            }
        } while (!exito);
        
    }
    
    // Funcion para buscar la placa (si recibe verdadero en eliminar, ademas de mostrarla la elimina)
    public static boolean BuscarPlaca(String placa, boolean eliminar) {        
        for (int i = 0; i < Estacionamiento.length; i++) {
            for (int j = 0; j < Estacionamiento[i].length; j++) {
                if (Estacionamiento[i][j].equals(placa)) {
                    System.out.println("Vehiculo encontado");
                    System.out.println("Fila: " + i);
                    System.out.println("Columna: " + j);
                    if (eliminar) {
                        Estacionamiento[i][j] = "";
                        System.out.println("Eliminado correctamente el vehiculo con placa: " + placa);
                    }
                    return true;
                }
            }
        }
        System.out.println("Error, placa no encontrada");
        return false;
    }
    
    // Metodo para retirar un vehiculo, reutilizando el de BuscarPlaca
    public static void RetirarVehiculo(String placa) {
        BuscarPlaca(placa, true);
    }
    
    // Funcion para cobrar la tarifa establecida
    public static boolean CobrarTarifa() {
        boolean pagado = false;
        do {
            int tarifa = LecturaNumero("Tarifa: Q. 10.00\nIngrese el monto entregado: ", false, 0, 100);
            
            if (tarifa < 10) {
                System.out.println("El pago es insuficiente, ingrese una nueva cantidad.");
                pagado = false;
                continue;
            }
            
            System.out.println("El cambio es: " + (tarifa - 10));
            pagado = true;
        } while (!pagado);
                
        return pagado;
    }
    
    /**
     * Utiles
     */

    // Funcion que solicita un dato de tipo cadena de texto al usuario por consola
    public static String LecturaTexto(String mensaje, boolean salto) {
        String entrada = "";
        Scanner sc = new Scanner(System.in);
        
        if (salto) {
            System.out.println(mensaje);            
        } else {
            System.out.print(mensaje);
        }
        
        try {
            entrada = sc.nextLine().toUpperCase();
        } catch(Exception ex) {
            System.out.print("Error: " + ex.getMessage());
        }
        return entrada;
    }
    
    // Funcion que solicita un dato de tipo entero al usuario por consola
    public static int LecturaNumero(String mensaje, boolean salto, int min, int max) {
        String entrada = LecturaTexto(mensaje, salto);

        try {
            int numero = Integer.parseInt(entrada);

            if (numero >= min && numero <= max) {
                return numero;
            } else {
                return -1;
            }

        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    // Funcion que determina si un valor es un numero
    public static boolean EsNumero(String cadena) {
        if (cadena == null || cadena.length() != 1) {
            return false;
        }
        char c = cadena.charAt(0);
        return c >= '0' && c <= '9';
    }
    
    // Funcion que determina si un valor pertenece al abecedario (es una letra)
    public static boolean EsLetra(String cadena) {
        if (cadena == null || cadena.length() != 1) {
            return false;
        }
        char c = cadena.charAt(0);
        return c >= 'A' && c <= 'Z';
    }
    
    // Funcion que realiza las validaciones para el formato de la placa
    public static boolean PlacaValida(String placa){
        // quitamos espacios al inicio y al final de la placa con trim()
        // ademas separamos la cadena con el metodo split       "P123FRG"     
        String[] elementos = placa.trim().split("");
        
        // la placa debe tener exactamente 7 caracteres
        if (elementos.length != 7) {
            return false;
        }
        
        // Validar que la primera letra sea una P
        if (!elementos[0].equals("P")) {
            return false;
        }
        
        if (!EsNumero(elementos[1]) || !EsNumero(elementos[2]) || !EsNumero(elementos[3])) {
            return false;
        }
        
        if (!EsLetra(elementos[4]) || !EsLetra(elementos[5]) || !EsLetra(elementos[6])) {
            return false;
        }
        
        return true;
    }
    
    // Funcion que determina si una posicion recibida ya esta siendo ocupada en el estacionamiento
    public static boolean PosicionOcupada(int fila, int columna) {
        return !Estacionamiento[fila][columna].equals("");
    }
    
    // Funcion que genera un numero entero aleatorio entre un minimo y un maximo recibido
    public static int GenerarAleatorio(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
}
