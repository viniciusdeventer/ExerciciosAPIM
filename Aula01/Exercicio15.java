// Exercicio15.java
import java.util.LinkedHashSet;
import java.util.Set;

public class Exercicio15 {
    public static void main(String[] args) {
        Set<String> dias = new LinkedHashSet<>();
        
        dias.add("Quarta");
        dias.add("Segunda");
        dias.add("Sexta");

        System.out.println("\n Ordem de Inserção mantida pelo LinkedHashSet:");
        for (String dia : dias) {
            System.out.println(" - " + dia);
        }
    }
}