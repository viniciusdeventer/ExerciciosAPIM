// Exercicio11.java
import java.util.LinkedList;

public class Exercicio11 {
    public static void main(String[] args) {
        LinkedList<String> fila = new LinkedList<>();
        
        // Adicionando no final da fila
        fila.add("Carlos");
        fila.add("Ana");
        fila.add("Pedro");
        fila.add("Maria");
        fila.add("João");

        System.out.println("\n Fila inicial: " + fila);

        // Atendendo os 2 primeiros
        fila.removeFirst();
        fila.removeFirst();

        System.out.println("\n Fila após atender 2 pessoas: " + fila);

        // Adicionando prioritários no início
        fila.addFirst("Cliente Prioridade 2");
        fila.addFirst("Cliente Prioridade 1");

        System.out.println("\n Ordem final da fila: " + fila);
    }
}