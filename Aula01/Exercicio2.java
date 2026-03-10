import java.util.Scanner;

public class Exercicio2 {
    public static void main (String[] args){

    int valorUsuario;
    int multiplicador = 0;
    int total = 0;

    Scanner scanner = new Scanner(System.in);

    System.out.println("\n Informe um número inteiro: ");
    valorUsuario = scanner.nextInt();

    for(int x = 0; x < 10; x++){
        total = valorUsuario * ++multiplicador;
        System.out.println(valorUsuario + " x " + multiplicador + " = " + total);
    }
    
    scanner.close();
    }
}
