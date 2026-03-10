// Exercicio28.java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Exercicio28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> pilhaPalavras = new ArrayDeque<>();

        System.out.println("\n Digite uma frase: ");
        String frase = scanner.nextLine();
        
        // Quebra a frase em palavras e empilha
        for (String palavra : frase.split(" ")) {
            pilhaPalavras.push(palavra);
        }

        System.out.print("\n Frase invertida: ");
        while (!pilhaPalavras.isEmpty()) {
            System.out.print(pilhaPalavras.pop() + " ");
        }
        System.out.println(); // Pular linha ao final

        scanner.close();
    }
}