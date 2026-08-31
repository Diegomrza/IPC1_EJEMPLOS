package ipc1.ejemplo1;


/**
 * Introduccion a la Programacion y Computacion 1 - A.
 *
 * Contiene ejemplos FUNCIONALES (para explicar el tema) y ejemplos CON
 * ERRORES (para practicar debug con el depurador de Apache NetBeans:
 * breakpoints, step over / step into, inspeccion de variables, etc.).
 *
 * Como usarlo en clase:
 *  1. Descomenta UNA llamada a la vez en el main() para no saturar la
 *     salida de consola.
 *  2. Para los ejemplos "conError", coloca un breakpoint (clic en el
 *     margen izquierdo de la linea) antes de correr en modo Debug
 *     (Shift+F5), y luego usa Step Over (F8) / Step Into (F7) para
 *     ver donde se rompe la logica o donde lanza la excepcion.
 *
 * @author Diegomrza
 */
public class Ejemplo1 {
    public static int edad = 29;    
    public static char Nombre = 'C';
    
    public static void main(String[] args) {
//        ejemploTiposDeDatos();
//        ejemploVariables();
//        ejemploOperadoresAsignacion();
//        ejemploOperadoresLogicosBooleanos();
//        ejemploEstructurasControl();
//        ejemploEstructurasCiclicas();
//        ejemploArreglos1D();
//        ejemploArreglos2D();

        // ===== Ejemplos CON ERRORES para practicar debug =====
//        ejemploErrorTipoDeDatoOverflow();
//        ejemploErrorDivisionEnteraPorCero();
//        ejemploErrorDivisionDecimalPorCero();
//        ejemploErrorIndiceFueraDeRango();
//        ejemploErrorNullPointer();
//        ejemploErrorCastInvalido();
//        ejemploErrorLogicoOffByOne();
//        ejemploErrorLogicoAsignacionEnVezDeComparacion();
//        ejemploErrorArreglo2DIndiceInvertido();
//        ejemploErrorCicloInfinito(); // CUIDADO: se queda pegado (Ctrl+C o boton Stop)
    }   

    // =====================================================================
    // 1. TIPOS DE DATOS
    // =====================================================================
    public static void ejemploTiposDeDatos() {
        System.out.println("=== TIPOS DE DATOS ===");

        // Tipos enteros
        byte  unByte   = 120;              // -128 a 127
        short unShort  = 30000;            // -32,768 a 32,767
        int   unInt    = 2_000_000;        // el guion bajo solo ayuda a leer el numero
        long  unLong   = 9_000_000_000L;   // la "L" es obligatoria para valores grandes

        // Tipos decimales
        float  unFloat  = 3.14f;   // la "f" es obligatoria, si no, es double
        double unDouble = 3.14159265358979;

        // Caracter y booleano
        char unChar    = 'A';
        boolean unBool = true;

        // Texto (no es primitivo, es una clase)
        String unString = "Hola IPC1";

        System.out.println("byte: " + unByte);
        System.out.println("short: " + unShort);
        System.out.println("int: " + unInt);
        System.out.println("long: " + unLong);
        System.out.println("float: " + unFloat);
        System.out.println("double: " + unDouble);
        System.out.println("char: " + unChar);
        System.out.println("boolean: " + unBool);
        System.out.println("String: " + unString);

        // Ejemplo util para debug: casteo explicito con perdida de datos
        double valorGrande = 9.99;
        int valorTruncado = (int) valorGrande; // se pierde el decimal, no redondea
        System.out.println("double 9.99 casteado a int: " + valorTruncado);
    }

    // =====================================================================
    // 2. VARIABLES (declaracion, inicializacion, alcance/scope)
    // =====================================================================
    public static void ejemploVariables() {
        System.out.println("=== VARIABLES ===");

        int edad;       // declaracion sin inicializar
        edad = 20;      // inicializacion en otra linea

        int nota = 85;  // declaracion + inicializacion en una linea

        // Constante (no puede cambiar despues de asignada)
        final double IVA = 0.12;

        System.out.println("Edad: " + edad);
        System.out.println("Nota: " + nota);
        System.out.println("IVA constante: " + IVA);

        {
            // Bloque interno: buen momento para explicar "alcance" (scope)
            int variableLocal = 5;
            System.out.println("Variable local dentro del bloque: " + variableLocal);
        }
        // Si descomentas la siguiente linea, marca ERROR DE COMPILACION
        // porque variableLocal no existe fuera del bloque:
        // System.out.println(variableLocal);
    }

    // =====================================================================
    // 3. OPERADORES DE ASIGNACION
    // =====================================================================
    public static void ejemploOperadoresAsignacion() {
        System.out.println("=== OPERADORES DE ASIGNACION ===");

        int x = 10;
        System.out.println("Valor inicial x = " + x);

        x += 5;  // x = x + 5
        System.out.println("x += 5  -> " + x);

        x -= 3;  // x = x - 3
        System.out.println("x -= 3  -> " + x);

        x *= 2;  // x = x * 2
        System.out.println("x *= 2  -> " + x);

        x /= 4;  // x = x / 4  (division entera, cuidado con el resultado)
        System.out.println("x /= 4  -> " + x);

        x %= 5;  // x = x % 5  (residuo/modulo)
        System.out.println("x %= 5  -> " + x);

        // Incremento y decremento
        int contador = 0;
        contador++;       // post-incremento
        System.out.println("contador++ -> " + contador);
        ++contador;       // pre-incremento
        System.out.println("++contador -> " + contador);
    }

    // =====================================================================
    // 4. OPERADORES LOGICOS / BOOLEANOS
    // =====================================================================
    public static void ejemploOperadoresLogicosBooleanos() {
        System.out.println("=== OPERADORES LOGICOS BOOLEANOS ===");

        boolean tieneCarnet = true;
        boolean mayorDeEdad = true;
        boolean estaSuspendido = false;

        boolean puedeVotar = tieneCarnet && mayorDeEdad; // AND: ambas deben ser true
        boolean puedeParticipar = tieneCarnet || estaSuspendido; // OR: al menos una true
        boolean noPuedeVotar = !puedeVotar; // NOT: invierte el valor

        System.out.println("puedeVotar (AND): " + puedeVotar);
        System.out.println("puedeParticipar (OR): " + puedeParticipar);
        System.out.println("noPuedeVotar (NOT): " + noPuedeVotar);

        // Operadores relacionales que generan booleanos
        int a = 7, b = 10;
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a < b: "  + (a < b));
        System.out.println("a >= b: " + (a >= b));

        // Cortocircuito: buen tema para debug paso a paso
        // Si evaluarPrimero() devuelve false, evaluarSegundo() NUNCA se ejecuta
        boolean resultado = evaluarPrimero() && evaluarSegundo();
        System.out.println("Resultado cortocircuito: " + resultado);
    }

    private static boolean evaluarPrimero() {
        System.out.println("  -> Se ejecuto evaluarPrimero()");
        return false;
    }

    private static boolean evaluarSegundo() {
        System.out.println("  -> Se ejecuto evaluarSegundo()"); // no deberia imprimir
        return true;
    }

    // =====================================================================
    // 5. SENTENCIAS DE CONTROL (if / else if / else, switch)
    // =====================================================================
    public static void ejemploEstructurasControl() {
        System.out.println("=== SENTENCIAS DE CONTROL ===");

        int nota = 75;

        if (nota >= 90) {
            System.out.println("Nota: A");
        } else if (nota >= 80) {
            System.out.println("Nota: B");
        } else if (nota >= 70) {
            System.out.println("Nota: C");
        } else {
            System.out.println("Nota: Reprobado");
        }

        int diaSemana = 1;
        switch (diaSemana) {
            case 1:
                System.out.println("Lunes");
                
            case 2:
                System.out.println("Martes");
                
            case 3:
                System.out.println("Miercoles");
                
            default:
                System.out.println("Otro dia");
                break;
        }

        // Operador ternario (if/else corto)
        int numero = 8;
        String paridad = (numero % 2 == 0) ? "par" : "impar";
        System.out.println(numero + " es " + paridad);
    }

    // =====================================================================
    // 6. ESTRUCTURAS CICLICAS (for, while, do-while)
    // =====================================================================
    public static void ejemploEstructurasCiclicas() {
        System.out.println("=== ESTRUCTURAS CICLICAS ===");

        System.out.println("-- for --");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        System.out.println("-- while --");
        int contador = 1;
        while (contador <= 5) {
            System.out.println("contador = " + contador);
            contador++;
        }

        System.out.println("-- do-while --");
        int j = 1;
        do {
            System.out.println("j = " + j);
            j++;
        } while (j <= 5);

        System.out.println("-- for con break y continue --");
        for (int i = 1; i <= 10; i++) {
            if (i == 7) {
                break; // corta el ciclo por completo
            }
            if (i % 2 == 0) {
                continue; // salta esta iteracion, sigue con la siguiente
            }
            System.out.println("Impar antes del 7: " + i);
        }
    }

    // =====================================================================
    // 7. ARREGLOS DE 1 DIMENSION
    // =====================================================================
    public static void ejemploArreglos1D() {
        System.out.println("=== ARREGLOS 1D ===");

        int[] notas = {85, 90, 70, 60, 95};

        System.out.println("Recorrido con for tradicional:");
        for (int i = 0; i < notas.length; i++) {
            System.out.println("notas[" + i + "] = " + notas[i]);
        }

        System.out.println("Recorrido con for-each:");
        for (int nota : notas) {
            System.out.println("nota = " + nota);
        }

        // Calculo de promedio (util para practicar acumuladores)
        int suma = 0;
        for (int nota : notas) {
            suma += nota;
        }
        double promedio = (double) suma / notas.length; // cuidado: sin el (double) da division entera
        System.out.println("Promedio: " + promedio);

        // Arreglo declarado vacio y llenado luego
        String[] nombres = new String[3];
        nombres[0] = "Ana";
        nombres[1] = "Luis";
        nombres[2] = "Carlos";
        for (String nombre : nombres) {
            System.out.println("Nombre: " + nombre);
        }
    }

    // =====================================================================
    // 8. ARREGLOS DE 2 DIMENSIONES (matrices)
    // =====================================================================
    public static void ejemploArreglos2D() {
        System.out.println("=== ARREGLOS 2D (matrices) ===");

        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Recorrido completo con doble for:");
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                System.out.print(matriz[fila][columna] + " ");
            }
            System.out.println(); // salto de linea al terminar cada fila
        }

        // Suma de todos los elementos de la matriz
        int sumaTotal = 0;
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                sumaTotal += matriz[fila][columna];
            }
        }
        System.out.println("Suma total de la matriz: " + sumaTotal);

        // Matriz creada vacia y llenada con formula (buena para debug paso a paso)
        int filas = 3;
        int columnas = 3;
        int[][] tablaMultiplicar = new int[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                tablaMultiplicar[f][c] = (f + 1) * (c + 1);
            }
        }
        System.out.println("Tabla de multiplicar generada:");
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                System.out.print(tablaMultiplicar[f][c] + "\t");
            }
            System.out.println();
        }
    }

    // #####################################################################
    // #  EJEMPLOS CON ERRORES - PARA PRACTICAR DEBUG EN NETBEANS
    // #####################################################################

    /**
     * ERROR DE LOGICA / OVERFLOW: byte solo llega hasta 127.
     * Al sumarle 1, "da la vuelta" y se vuelve negativo (-128).
     * Bueno para explicar rango de tipos de dato con el debugger,
     * inspeccionando el valor de "valor" antes y despues del ++.
     */
    public static void ejemploErrorTipoDeDatoOverflow() {
        System.out.println("=== ERROR: Overflow de tipo byte ===");
        byte valor = 127;
        System.out.println("Antes: " + valor);
        valor++; // Coloca un breakpoint aqui y observa el resultado
        System.out.println("Despues del overflow: " + valor); // imprime -128
    }

    /**
     * ERROR EN TIEMPO DE EJECUCION: ArithmeticException.
     * La division ENTERA entre cero lanza excepcion (distinto a la
     * division decimal, que da Infinity, ver siguiente metodo).
     */
    public static void ejemploErrorDivisionEnteraPorCero() {
        System.out.println("=== ERROR: Division entera por cero ===");
        int a = 10;
        int b = 0;
        System.out.println("Antes de dividir...");
        int resultado = a / b; // Lanza: ArithmeticException: / by zero
        System.out.println("Resultado: " + resultado); // nunca se imprime
    }

    /**
     * CASO ESPECIAL (no lanza excepcion, pero confunde a estudiantes):
     * la division decimal entre cero NO truena, da Infinity o NaN.
     * Bueno para contrastar con el metodo anterior.
     */
    public static void ejemploErrorDivisionDecimalPorCero() {
        System.out.println("=== Caso especial: division decimal por cero ===");
        double a = 10.0;
        double b = 0.0;
        double resultado = a / b;
        System.out.println("10.0 / 0.0 = " + resultado); // imprime Infinity
        System.out.println("0.0 / 0.0 = " + (0.0 / 0.0)); // imprime NaN
    }

    /**
     * ERROR EN TIEMPO DE EJECUCION: ArrayIndexOutOfBoundsException.
     * El arreglo tiene indices validos de 0 a 4 (5 elementos),
     * pero se intenta acceder al indice 5.
     */
    public static void ejemploErrorIndiceFueraDeRango() {
        System.out.println("=== ERROR: Indice fuera de rango ===");
        int[] numeros = {10, 20, 30, 40, 50};
        for (int i = 0; i <= numeros.length; i++) { // <= en vez de < : error clasico
            System.out.println("numeros[" + i + "] = " + numeros[i]);
        }
    }

    /**
     * ERROR EN TIEMPO DE EJECUCION: NullPointerException.
     * Se declara el String pero nunca se inicializa (queda en null),
     * y luego se intenta usar un metodo sobre el (length()).
     */
    public static void ejemploErrorNullPointer() {
        System.out.println("=== ERROR: NullPointerException ===");
        String nombre = null;
        System.out.println("Voy a intentar medir el nombre...");
        int longitud = nombre.length(); // Lanza NullPointerException
        System.out.println("Longitud: " + longitud);
    }

    /**
     * ERROR EN TIEMPO DE EJECUCION: NumberFormatException.
     * Se intenta convertir un texto que no es numerico a entero.
     */
    public static void ejemploErrorCastInvalido() {
        System.out.println("=== ERROR: conversion invalida de texto a numero ===");
        String textoNota = "ochenta"; // deberia ser algo como "80"
        int nota = Integer.parseInt(textoNota); // Lanza NumberFormatException
        System.out.println("Nota: " + nota);
    }

    /**
     * ERROR LOGICO (compila y corre, pero el resultado esta mal):
     * "Off by one". El ciclo deberia recorrer 5 elementos (indices 0-4),
     * pero por el <= en vez de <, se pasa un elemento... aqui SIN que
     * truene, para variar (arreglo mas grande que el recorrido real).
     * Bueno para mostrar que no todo error se ve como una excepcion.
     */
    public static void ejemploErrorLogicoOffByOne() {
        System.out.println("=== ERROR LOGICO: off-by-one silencioso ===");
        int[] calificaciones = {60, 70, 80, 90, 100, 55}; // 6 elementos
        int sumaPrimerosCinco = 0;
        // Intencion: sumar solo los primeros 5, pero el <= incluye el sexto
        for (int i = 0; i <= 4; i++) {
            sumaPrimerosCinco += calificaciones[i];
        }
        System.out.println("Suma (deberia ser 400): " + sumaPrimerosCinco);
        // Con este arreglo en particular da 400 igual (60+70+80+90+100),
        // cambia el arreglo en clase para evidenciar el error con otros valores.
    }

    /**
     * ERROR LOGICO CLASICO: usar "=" (asignacion) en vez de "==" (comparacion)
     * dentro de un if. Esto en Java SOLO compila si la variable es boolean;
     * si es boolean, es un error de logica silencioso: la condicion "cambia"
     * la variable en vez de solo compararla.
     */
    public static void ejemploErrorLogicoAsignacionEnVezDeComparacion() {
        System.out.println("=== ERROR LOGICO: '=' en vez de '==' ===");
        boolean activo = false;
        System.out.println("Valor antes del if: " + activo);
        if (activo = true) { // BUG: deberia ser (activo == true) o solo (activo)
            System.out.println("Entro al if (pero puede que no era la intencion)");
        }
        System.out.println("Valor despues del if: " + activo); // quedo modificada a true
    }

    /**
     * ERROR EN TIEMPO DE EJECUCION en matrices: indices de fila/columna
     * invertidos o mal calculados, provoca ArrayIndexOutOfBoundsException
     * si la matriz no es cuadrada.
     */
    public static void ejemploErrorArreglo2DIndiceInvertido() {
        System.out.println("=== ERROR: indices de matriz invertidos ===");
        int[][] matriz = {
            {1, 2, 3, 4},   // 3 filas x 4 columnas (NO cuadrada)
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        // BUG: se usa matriz.length (numero de FILAS = 3) como limite
        // de columnas tambien, pero hay 4 columnas por fila.
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz.length; columna++) { // deberia ser matriz[fila].length
                System.out.print(matriz[fila][columna] + " ");
            }
            System.out.println();
        }
    }

    /**
     * ERROR CLASICO: ciclo infinito por olvidar actualizar la variable
     * de control. CUIDADO al correrlo: usa el boton Stop (cuadrado rojo)
     * de NetBeans para detenerlo, o Ctrl+C en consola.
     */
    public static void ejemploErrorCicloInfinito() {
        System.out.println("=== ERROR: ciclo infinito (cuidado) ===");
        int contador = 0;
        while (contador < 5) {
            System.out.println("contador = " + contador);
            // Falta contador++; por eso nunca llega a 5
        }
    }
}