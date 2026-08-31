package ejemplo2;

public class Main {
    
    public static void main(String[] args) {
        int edad = 20;
        int minumero = 0;
        minumero = 50/2 + 30 -15 %2 + sumarle100(10) + sumarle100(10);
        System.out.println("minumero es: " + minumero);
        System.out.printf("mi numero es: %d", minumero);
        
        CicloInfinitoFor();
    }

    public static void CicloInfinitoFor() {
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Valor: [" + i + "]");
            i--;
        }
    }
    
    public static int sumarle100(int numero){
        return numero+100;
    }
}