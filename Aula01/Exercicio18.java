// Exercicio18.java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercicio18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> dicionario = new HashMap<>();

        // Populando o dicionário
        dicionario.put("cachorro", "dog");
        dicionario.put("gato", "cat");
        dicionario.put("mesa", "table");
        dicionario.put("livro", "book");
        dicionario.put("janela", "window");

        System.out.println("\n Digite uma palavra em português para traduzir: ");
        String palavra = scanner.nextLine().toLowerCase();

        if (dicionario.containsKey(palavra)) {
            System.out.println("\n A tradução é: " + dicionario.get(palavra));
        } else {
            System.out.println("\n Palavra não encontrada no dicionário.");
        }

        scanner.close();
    }
}