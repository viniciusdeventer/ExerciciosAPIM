package app.view;

import java.util.List;
import java.util.Scanner;

import model.cliente.Cliente;
import model.cliente.PessoaFisica;
import model.cliente.PessoaJuridica;
import service.ClienteService;
import service.ContaService;

public class ClienteView {

    private final Scanner scanner = new Scanner(System.in);
    private final ClienteService service;
    private final ContaService contaService;
    private final ContaView contaView;

    public ClienteView(ClienteService service, ContaService contaService) {
        this.service = service;
        this.contaService = contaService;
        this.contaView = new ContaView(service, contaService);
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
                case 5 -> contaView.iniciar();
                case 0 -> System.out.println("\nVoltando ao menu principal...\n");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n┌─────────────── MENU CLIENTES ────────────────┐");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("   1  →  Cadastrar Cliente                      ");
        System.out.println("   2  →  Listar Clientes                        ");
        System.out.println("   3  →  Atualizar Cliente                      ");
        System.out.println("   4  →  Remover Cliente                        ");
        System.out.println("   5  →  Gerenciar Contas                       ");
        System.out.println("                                                ");
        System.out.println("   0  →  Voltar                                 ");
        System.out.println("└──────────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
        System.out.flush();
    }

    private void cadastrar() {
        try {
            System.out.println("\n┌──────────── CADASTRAR CLIENTE ──────────────┐");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("   1 → Pessoa Física");
            System.out.println("   2 → Pessoa Jurídica");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("Escolha a opção desejada: ");
            
            int tipo = lerInteiro();
            
            if (tipo == 1) 
            	System.out.print("\n     CPF: ");
            else 
            	System.out.print("\n    CNPJ: ");
            
            String documento = scanner.nextLine();

            System.out.print("    Nome: ");
            String nome = scanner.nextLine();

            System.out.print("   Email: ");
            String email = scanner.nextLine();

            Cliente cliente;

            if (tipo == 1) {
                cliente = new PessoaFisica(nome, email, documento);
            } else if (tipo == 2) {
                cliente = new PessoaJuridica(nome, email, documento);
            } else {
                System.out.println("\nTipo inválido.");
                return;
            }

            service.cadastrar(cliente);
            System.out.println("\n✔ Cliente cadastrado com sucesso.");

            cadastrarConta(documento);

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao cadastrar: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n┌─────────────────────── LISTA DE CLIENTES ────────────────────────┐");

        List<Cliente> clientes = service.listar();

        System.out.println("├────┬──────────────────┬──────────────────────────┬────────────────┤");
        System.out.printf("│ %-2s │ %-16s │ %-25s│ %-14s │%n", "ID", "NOME", "EMAIL", "DOCUMENTO");
        System.out.println("├────┼──────────────────┼──────────────────────────┼────────────────┤");

        for (Cliente cliente : clientes) {
            System.out.printf("│ %-2d │ %-16s │ %-25s│ %-14s  │%n",
                    cliente.getId(),
                    limitar(cliente.getNome(), 16),
                    limitar(cliente.getEmail(), 25),
                    limitar(cliente.getDocumento(), 14));
        }

        System.out.println("└────┴──────────────────┴──────────────────────────┴────────────────┘");
    }

    private String limitar(String texto, int tamanho) {
        if (texto == null) return "";
        return texto.length() > tamanho
                ? texto.substring(0, tamanho - 3) + "..."
                : texto;
    }
    
    private void atualizar() {
        try {
            System.out.println("\n┌──────────── ATUALIZAR CLIENTE ──────────────┐");
            System.out.println("├─────────────────────────────────────────────┤");

            System.out.print("          ID: ");
            int id = lerInteiro();

            System.out.print("        Nome: ");
            String nome = scanner.nextLine();

            System.out.print("      E-mail: ");
            String email = scanner.nextLine();

            System.out.print("   Documento: ");
            String documento = scanner.nextLine();

            System.out.println("\n└─────────────────────────────────────────────┘");

            boolean atualizado = service.atualizar(id, nome, email, documento);

            if (atualizado)
                System.out.println("\n✔ Cliente atualizado com sucesso.");
            else
                System.out.println("\nCliente não encontrado.");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao atualizar: " + e.getMessage());
        }
    }

    private void remover() {
        System.out.println("\n┌───────────── REMOVER CLIENTE ───────────────┐");
        System.out.println("├─────────────────────────────────────────────┤");

        System.out.print("   Digite o ID do cliente: ");
        int id = lerInteiro();

        boolean removido = service.remover(id);
        
        System.out.println("└─────────────────────────────────────────────┘");

        if (removido)
            System.out.println("\n✔ Cliente removido com sucesso.");
        else
            System.out.println("\nCliente não encontrado.");
    }

    private void cadastrarConta(String documento) {
        try {
            Cliente cliente = service.buscarPorDoc(documento);

            if (cliente == null) {
                System.out.println("\nCliente não encontrado.");
                return;
            }

            System.out.println("\n┌──────────── CRIAR CONTA ─────────────┐");
            System.out.println("├──────────────────────────────────────┤");
            System.out.println("   1  →  Conta Corrente                 ");
            System.out.println("   2  →  Conta CDI                      ");
            System.out.println("   3  →  Investimento Automático        ");
            System.out.println("└──────────────────────────────────────┘");
            System.out.print("Escolha o tipo de conta: ");

            int tipo = lerInteiro();

            System.out.print("\nInforme o saldo inicial: ");
            double saldoInicial = Double.parseDouble(scanner.nextLine());

            switch (tipo) {
                case 1 -> contaService.criarContaCorrente(cliente, saldoInicial);
                case 2 -> contaService.criarContaCDI(cliente, saldoInicial);
                case 3 -> contaService.criarContaInvestimentoAutomatico(cliente, saldoInicial);
                default -> {
                    System.out.println("\nTipo inválido.");
                    return;
                }
            }

            System.out.println("\n✔ Conta criada com sucesso.");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao criar conta: " + e.getMessage());
        }
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}