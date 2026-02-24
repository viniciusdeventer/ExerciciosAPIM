// Exercicio13.java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercicio13 {
    public static void main(String[] args) {
        List<Integer> listaComDuplicatas = new ArrayList<>();
        listaComDuplicatas.add(10);
        listaComDuplicatas.add(20);
        listaComDuplicatas.add(10); // Duplicado
        listaComDuplicatas.add(30);
        listaComDuplicatas.add(20); // Duplicado

        System.out.println("\n Lista original: " + listaComDuplicatas);

        // Convertendo para Set para remover duplicatas magicamente
        Set<Integer> conjuntoSemDuplicatas = new HashSet<>(listaComDuplicatas);

        System.out.println("\n Coleção sem duplicatas: " + conjuntoSemDuplicatas);
    }
}