// Exercicio27.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercicio27 {
    public static void main(String[] args) {
        List<String> participantes = new ArrayList<>();
        Collections.addAll(participantes, "Ana", "Bruno", "Ana", "Carlos", "Bruno", "Daniel", "Eduardo", "Fernanda");

        // Set remove duplicatas automaticamente
        Set<String> unicos = new HashSet<>(participantes);
        
        // Convertemos de volta para List para poder embaralhar
        List<String> sorteio = new ArrayList<>(unicos);
        Collections.shuffle(sorteio);

        System.out.println("\n Ganhadores do Sorteio:");
        for (int i = 0; i < 3 && i < sorteio.size(); i++) {
            System.out.println(" " + (i + 1) + "º Lugar: " + sorteio.get(i));
        }
    }
}