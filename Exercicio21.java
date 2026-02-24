// Exercicio21.java
import java.util.LinkedHashMap;
import java.util.Map;

public class Exercicio21 {
    public static void main(String[] args) {
        // LinkedHashMap mantém a ordem de inserção
        Map<Integer, String> produtos = new LinkedHashMap<>();

        produtos.put(101, "Monitor");
        produtos.put(105, "Teclado");
        produtos.put(102, "Mouse");
        produtos.put(104, "Headset");
        produtos.put(103, "Cadeira Gamer");

        System.out.println("\n Produtos na ordem de cadastro (LinkedHashMap):");
        for (Map.Entry<Integer, String> entry : produtos.entrySet()) {
            System.out.println(" Código: " + entry.getKey() + " - " + entry.getValue());
        }
    }
}