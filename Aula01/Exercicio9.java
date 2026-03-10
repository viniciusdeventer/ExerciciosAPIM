// Exercicio9.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tarefas = new ArrayList<>();
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n --- Gerenciador de Tarefas --- ");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Remover tarefa por índice");
            System.out.println("3. Listar tarefas");
            System.out.println("4. Sair");
            System.out.print("\n Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            if (opcao == 1) {
                System.out.println("\n Digite a nova tarefa: ");
                tarefas.add(scanner.nextLine());
                System.out.println(" Tarefa adicionada!");
            } else if (opcao == 2) {
                System.out.println("\n Digite o índice da tarefa para remover (0 a " + (tarefas.size() - 1) + "): ");
                int indice = scanner.nextInt();
                if (indice >= 0 && indice < tarefas.size()) {
                    tarefas.remove(indice);
                    System.out.println(" Tarefa removida!");
                } else {
                    System.out.println(" Índice inválido.");
                }
            } else if (opcao == 3) {
                System.out.println("\n --- Lista de Tarefas ---");
                for (int i = 0; i < tarefas.size(); i++) {
                    System.out.println(" [" + i + "] " + tarefas.get(i));
                }
            }
        }
        
        scanner.close();
    }
}