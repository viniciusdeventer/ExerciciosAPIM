// Exercicio22.java
import java.util.Map;
import java.util.TreeMap;

public class Exercicio22 {
    public static void main(String[] args) {
        // TreeMap ordena automaticamente pela chave (neste caso, String/Nome)
        Map<String, Double> notas = new TreeMap<>();

        notas.put("Bruno", 7.5);
        notas.put("Ana", 9.0);
        notas.put("Carlos", 6.8);
        notas.put("Daniela", 8.2);
        notas.put("Eduardo", 5.5);

        System.out.println("\n Notas ordenadas por nome do aluno (TreeMap):");
        for (Map.Entry<String, Double> entry : notas.entrySet()) {
            System.out.println(" Aluno: " + entry.getKey() + " | Nota: " + entry.getValue());
        }
    }
}