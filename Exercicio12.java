// Exercicio12.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> cidades = new ArrayList<>();
        cidades.add("São Paulo");
        cidades.add("Rio de Janeiro");
        cidades.add("Curitiba");
        cidades.add("Belo Horizonte");

        System.out.println("\n Digite o nome da cidade que deseja buscar: ");
        String busca = scanner.nextLine();

        if (cidades.contains(busca)) {
            System.out.println("\n A cidade está na lista! Índice: " + cidades.indexOf(busca));
        } else {
            System.out.println("\n A cidade não foi encontrada na lista.");
        }

        scanner.close();
    }
}