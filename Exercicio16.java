// Exercicio16.java
import java.util.Set;
import java.util.TreeSet;

public class Exercicio16 {
    public static void main(String[] args) {
        Set<String> nomes = new TreeSet<>();
        
        nomes.add("Zeca");
        nomes.add("Ana");
        nomes.add("Carlos");
        nomes.add("Beatriz");
        nomes.add("Xuxa");

        System.out.println("\n Nomes ordenados naturalmente pelo TreeSet:");
        for (String nome : nomes) {
            System.out.println(" - " + nome);
        }
    }
}