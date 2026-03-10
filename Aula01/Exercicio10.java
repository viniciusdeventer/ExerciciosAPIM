// Exercicio10.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercicio10 {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        Collections.addAll(numeros, 45, 12, 89, 2, 14, 7, 99, 33, 21, 5);

        System.out.println("\n Lista original: " + numeros);
        
        Collections.sort(numeros);
        
        System.out.println("\n Lista ordenada: " + numeros);
    }
}