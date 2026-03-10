// Exercicio20.java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercicio20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> agenda = new HashMap<>();
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n --- Agenda Telefônica ---");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Buscar Telefone");
            System.out.println("3. Listar Todos");
            System.out.println("4. Sair");
            System.out.print(" Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1) {
                System.out.print("\n Nome: ");
                String nome = scanner.nextLine();
                System.out.print(" Telefone: ");
                String telefone = scanner.nextLine();
                agenda.put(nome, telefone);
                System.out.println(" Contato salvo!");
            } else if (opcao == 2) {
                System.out.print("\n Digite o nome para buscar: ");
                String busca = scanner.nextLine();
                if (agenda.containsKey(busca)) {
                    System.out.println(" Telefone de " + busca + ": " + agenda.get(busca));
                } else {
                    System.out.println(" Contato não encontrado.");
                }
            } else if (opcao == 3) {
                System.out.println("\n Lista de Contatos:");
                for (String key : agenda.keySet()) {
                    System.out.println(" Nome: " + key + " | Tel: " + agenda.get(key));
                }
            }
        }
        scanner.close();
    }
}