package app.view;

import java.util.List;
import java.util.Scanner;

import model.produto.Produto;
import model.produto.RendaFixa;
import model.produto.RendaVariavel;
import service.ProdutoService;

public class ProdutoView {

    private final Scanner scanner = new Scanner(System.in);
    private final ProdutoService service;

    public ProdutoView(ProdutoService service) {
        this.service = service;
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> remover();
                case 0 -> System.out.println("\nVoltando ao menu principal...\n");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n┌─────────────── MENU PRODUTOS ──────────────┐");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("   1  →  Cadastrar Produto                     ");
        System.out.println("   2  →  Listar Produtos                       ");
        System.out.println("   3  →  Atualizar Produto                     ");
        System.out.println("   4  →  Remover Produto                       ");
        System.out.println("                                                ");
        System.out.println("   0  →  Voltar                                 ");
        System.out.println("└──────────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
        System.out.flush();
    }

    private void cadastrar() {
        try {

            System.out.println("\n┌──────────── CADASTRAR PRODUTO ──────────────┐");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("   1 → Renda Fixa");
            System.out.println("   2 → Renda Variável");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("Escolha a opção desejada: ");

            int tipo = lerInteiro();

            System.out.print("\n        Nome: ");
            String nome = scanner.nextLine();

            System.out.print("   Descrição: ");
            String descricao = scanner.nextLine();

            Produto produto;

            switch (tipo) {

                case 1 -> {
                    System.out.print("  Rendimento: ");
                    double rendimento = Double.parseDouble(scanner.nextLine());

                    System.out.print("    Carência: ");
                    int carencia = lerInteiro();

                    produto = new RendaFixa(nome, descricao, rendimento, carencia);
                }

                case 2 -> {
                    System.out.print("  Rendimento: ");
                    double rendimento = Double.parseDouble(scanner.nextLine());

                    produto = new RendaVariavel(nome, descricao, rendimento);
                }

                default -> {
                    System.out.println("\nTipo inválido.");
                    return;
                }
            }

            service.cadastrar(produto);
            System.out.println("\n✔ Produto cadastrado com sucesso.");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao cadastrar: " + e.getMessage());
        }
    }

    private void listar() {

        List<Produto> produtos = service.listar();

        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado.");
            return;
        }

        System.out.println("\n┌──────────────────────── LISTA DE PRODUTOS ────────────────────────┐");
        System.out.println("├────┬──────────────────┬──────────────────────────┬────────────────┤");
        System.out.printf("│ %-2s │ %-16s │ %-25s│ %-14s │%n",
                "ID", "NOME", "DESCRIÇÃO", "TIPO");
        System.out.println("├────┼──────────────────┼──────────────────────────┼────────────────┤");

        for (Produto produto : produtos) {
            System.out.printf("│ %-2d │ %-16s │ %-25s│ %-14s │%n",
                    produto.getId(),
                    limitar(produto.getNome(), 16),
                    limitar(produto.getDescricao(), 25),
                    limitar(produto.getTipoProduto(), 14));
        }

        System.out.println("└────┴──────────────────┴──────────────────────────┴────────────────┘");
    }

    private void atualizar() {
        try {

            System.out.println("\n┌──────────── ATUALIZAR PRODUTO ──────────────┐");
            System.out.println("├─────────────────────────────────────────────┤");

            System.out.print("           ID: ");
            int id = lerInteiro();

            Produto produto = service.buscarPorId(id);

            if (produto == null) {
                System.out.println("\nProduto não encontrado.");
                return;
            }

            System.out.print("         Nome: ");
            String nome = scanner.nextLine();

            System.out.print("    Descrição: ");
            String descricao = scanner.nextLine();

            if (produto instanceof RendaFixa) {

                System.out.print("   Rendimento: ");
                double rendimento = Double.parseDouble(scanner.nextLine());

                System.out.print("     Carência: ");
                int carencia = lerInteiro();

                service.atualizarRendaFixa(id, nome, descricao, rendimento, carencia);

            } else if (produto instanceof RendaVariavel) {

                System.out.print("   Rendimento: ");
                double rendimento = Double.parseDouble(scanner.nextLine());

                service.atualizarRendaVariavel(id, nome, descricao, rendimento);
            }

            System.out.println("\n└─────────────────────────────────────────────┘");
            System.out.println("\n✔ Produto atualizado com sucesso.");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao atualizar: " + e.getMessage());
        }
    }

    private void remover() {

        System.out.println("\n┌───────────── REMOVER PRODUTO ───────────────┐");
        System.out.println("├─────────────────────────────────────────────┤");

        System.out.print("          ID: ");
        int id = lerInteiro();

        System.out.println("└─────────────────────────────────────────────┘");

        boolean removido = service.remover(id);

        if (removido)
            System.out.println("\n✔ Produto removido com sucesso.");
        else
            System.out.println("\nProduto não encontrado.");
    }

    private String limitar(String texto, int tamanho) {
        if (texto == null) return "";
        return texto.length() > tamanho
                ? texto.substring(0, tamanho - 3) + "..."
                : texto;
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}