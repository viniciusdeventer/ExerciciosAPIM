import java.util.Scanner;
import java.util.Random;


public class Exercicio3 {
    public static void main (String[] args){

    Scanner scanner = new Scanner(System.in);

    Random random = new Random();

    // Gerar um número entre 1 e 100
    int numero = random.nextInt(100) + 1;
    int tentativas = 0;
    int palpite = 0;
    int numerotentativas = 5;

    System.out.println("\n Tente adivinhar o número entre 1 e 100 em: " + numerotentativas + " tentativas.");

    while (tentativas < numerotentativas) {
        System.out.println("\n Digite o seu palpite: " );
        palpite = scanner.nextInt();
        tentativas++;
        
        if(palpite == numero) {
            System.out.println("\n Parabéns você acertou o número secreto em:" + numerotentativas + " tentativas.");
            scanner.close();

            return;
        }
        else if (palpite < numero){
            System.out.println("\n O número secreto é maior que: " + palpite);
        }
        else {
            System.out.println("\n O número secreto é menor que: " + palpite);
        }

        if (tentativas == numerotentativas){
            System.out.println("\n Acabaram o número de tentativas! O número secreto era: " + numero);
        }
    }
        scanner.close();
    }

    
}
