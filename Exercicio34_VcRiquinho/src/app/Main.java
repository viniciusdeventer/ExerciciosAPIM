package app;

import java.util.Scanner;

import app.view.ClienteView;
import app.view.ProdutoView;
import service.ClienteService;
import service.ContaService;
import service.ProdutoService;

public class Main {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ClienteService clienteService = new ClienteService();
        ContaService contaService = new ContaService();
        ProdutoService produtoService = new ProdutoService();

        ClienteView clienteView = new ClienteView(clienteService, contaService);
        ProdutoView produtoView = new ProdutoView(produtoService);

        int opcao;
        
        exibirHeader(scanner);

        do {
            exibirMenuPrincipal();
            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1 -> {
                    clienteView.iniciar();
                }
                case 2 -> {
                    produtoView.iniciar();
                }
                case 0 -> System.out.println("\nSaindo...");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirHeader(Scanner scanner) {
    	System.out.println(CYAN);
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║  _    __     ____  _             _       __         ║");
        System.out.println("║ | |  / /____/ __ \\(_)___ ___  __(_)___  / /_  ____  ║");
        System.out.println("║ | | / / ___/ /_/ / / __ `/ / / / / __ \\/ __ \\/ __ \\ ║");
        System.out.println("║ | |/ / /__/ _, _/ / /_/ / /_/ / / / / / / / / /_/ / ║");
        System.out.println("║ |___/\\___/_/ |_/_/\\__, /\\__,_/_/_/ /_/_/ /_/\\____/  ║");
        System.out.println("║                     /_/                             ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.println("Every single day is a day to become a little richer!");
        System.out.println(RESET);
        System.out.println("Pressione enter para continuar...");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("┌─────────────── MENU PRINCIPAL ───────────────┐");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("    1  →  Gerenciamento de Clientes            ");
        System.out.println("    2  →  Gerenciar Produtos                   ");
        System.out.println("                                               ");
        System.out.println("    0  →  Sair                                 ");
        System.out.println("└──────────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
        System.out.flush();
    }

    private static int lerInteiro(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}