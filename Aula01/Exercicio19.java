// Exercicio19.java
import java.util.HashMap;
import java.util.Map;

public class Exercicio19 {
    public static void main(String[] args) {
        String texto = "java é legal java é poderoso eu gosto de java e gosto de programar";
        String[] palavras = texto.split(" ");
        
        Map<String, Integer> contagemPalavras = new HashMap<>();

        for (String palavra : palavras) {
            // Se a palavra já existe, pega o valor atual e soma 1. Se não, começa com 0 e soma 1.
            contagemPalavras.put(palavra, contagemPalavras.getOrDefault(palavra, 0) + 1);
        }

        System.out.println("\n Frequência das palavras:");
        for (Map.Entry<String, Integer> entry : contagemPalavras.entrySet()) {
            System.out.println(" " + entry.getKey() + ": " + entry.getValue());
        }
    }
}