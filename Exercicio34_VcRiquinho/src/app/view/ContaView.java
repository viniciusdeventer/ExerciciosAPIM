package app.view;

import java.util.List;
import java.util.Scanner;

import model.cliente.Cliente;
import model.conta.Conta;
import service.ClienteService;
import service.ContaService;

public class ContaView {

    private final Scanner scanner = new Scanner(System.in);
    private final ClienteService clienteService;
    private final ContaService contaService;

    public ContaView(ClienteService clienteService, ContaService contaService) {
        this.clienteService = clienteService;
        this.contaService = contaService;
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> listarContas();
                case 2 -> cadastrarConta();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> removerConta();
                case 6 -> simular();
                case 0 -> System.out.println("\nVoltando ao menu anterior...\n");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n┌─────────────── MENU CONTAS ────────────────┐");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("   1  →  Listar Contas                        ");
        System.out.println("   2  →  Cadastrar Conta                      ");
        System.out.println("   3  →  Depositar                            ");
        System.out.println("   4  →  Sacar                                ");
        System.out.println("   5  →  Remover Conta                        ");
        System.out.println("   6  →  Simular Rendimento                   ");
        System.out.println("                                              ");
        System.out.println("   0  →  Voltar                               ");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
        System.out.flush();
    }

    private Cliente buscarClientePorDocumento() {
        System.out.print("\n → Documento do Cliente: ");
        String documento = scanner.nextLine();

        Cliente cliente = clienteService.buscarPorDoc(documento);

        if (cliente == null)
            System.out.println("\nCliente não encontrado.");

        return cliente;
    }

    private void listarContas() {

        Cliente cliente = buscarClientePorDocumento();
        if (cliente == null) return;

        List<Conta> contas = cliente.getContas();

        if (contas.isEmpty()) {
            System.out.println("\nCliente não possui contas.");
            return;
        }

        System.out.println("\n┌──────────────────────── LISTA DE CONTAS ───────────────────────┐");
        System.out.println("├────┬──────────────────────┬────────────────────────────────────┤");
        System.out.printf("│ %-2s │ %-20s │ %-34s │%n", "ID", "TIPO", "SALDO");
        System.out.println("├────┼──────────────────────┼────────────────────────────────────┤");

        for (Conta conta : contas) {
            System.out.printf("│ %-2d │ %-20s │ R$ %-30.2f  │%n",
                    conta.getId(),
                    limitar(conta.getTipoConta(), 20),
                    conta.getSaldo());
        }

        System.out.println("└────┴──────────────────────┴────────────────────────────────────┘");
    }

    private void cadastrarConta() {
        try {

            Cliente cliente = buscarClientePorDocumento();
            if (cliente == null) return;

            System.out.println("\n┌──────────── CRIAR CONTA ─────────────┐");
            System.out.println("├──────────────────────────────────────┤");
            System.out.println("   1  →  Conta Corrente                 ");
            System.out.println("   2  →  Conta CDI                      ");
            System.out.println("   3  →  Investimento Automático        ");
            System.out.println("└──────────────────────────────────────┘");
            System.out.print("Escolha o tipo de conta: ");

            int tipo = lerInteiro();

            System.out.print("\nInforme o saldo inicial: ");
            double saldoInicial = lerDouble();

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

    private void removerConta() {

        Cliente cliente = buscarClientePorDocumento();
        if (cliente == null) return;

        System.out.println("\n┌───────────── REMOVER CONTA ───────────────┐");
        System.out.println("├───────────────────────────────────────────┤");

        System.out.print("          ID: ");
        int id = lerInteiro();

        System.out.println("└───────────────────────────────────────────┘");

        boolean removida = contaService.removerConta(cliente, id);

        if (removida)
            System.out.println("\n✔ Conta removida com sucesso.");
        else
            System.out.println("\nConta não encontrada.");
    }

    private void depositar() {

        Cliente cliente = buscarClientePorDocumento();
        if (cliente == null) return;

        System.out.println("\n┌────────────── DEPOSITAR ───────────────┐");
        System.out.println("├────────────────────────────────────────┤");

        System.out.print("          ID: ");
        int id = lerInteiro();

        System.out.print("       Valor: ");
        double valor = lerDouble();

        System.out.println("└────────────────────────────────────────┘");

        boolean sucesso = contaService.depositar(cliente, id, valor);

        if (sucesso)
            System.out.println("\n✔ Depósito realizado com sucesso.");
        else
            System.out.println("\nConta não encontrada.");
    }

    private void sacar() {

        Cliente cliente = buscarClientePorDocumento();
        if (cliente == null) return;

        try {

            System.out.println("\n┌──────────────── SACAR ────────────────┐");
            System.out.println("├────────────────────────────────────────┤");

            System.out.print("          ID: ");
            int id = lerInteiro();

            System.out.print("       Valor: ");
            double valor = lerDouble();

            System.out.println("└────────────────────────────────────────┘");

            boolean sucesso = contaService.sacar(cliente, id, valor);

            if (sucesso)
                System.out.println("\n✔ Saque realizado com sucesso.");
            else
                System.out.println("\nConta não encontrada.");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
    
    private void simular() {

        try {
            Cliente cliente = buscarClientePorDocumento();
            if (cliente == null) return;

            List<Conta> contas = cliente.getContas();

            if (contas.isEmpty()) {
                System.out.println("\nCliente não possui contas.");
                return;
            }

            listarContas();

            System.out.println("\n┌──────────── SIMULAR RENDIMENTO ────────────┐");
            System.out.println("├─────────────────────────────────────────────┤");

            System.out.print("          ID: ");
            int id = lerInteiro();

            System.out.print("       Período: ");
            int dias = lerInteiro();

            System.out.println("└─────────────────────────────────────────────┘");

            Conta conta = contas.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (conta == null) {
                System.out.println("\nConta não encontrada.");
                return;
            }

            double rendimento = contaService.simular(conta, dias);

            System.out.println("\n┌──────────── RESULTADO ─────────────┐");
            System.out.println("├─────────────────────────────────────┤");
            System.out.printf("   Rendimento líquido: R$ %.2f%n", rendimento);
            System.out.println("└─────────────────────────────────────┘");

        } catch (IllegalArgumentException e) {
            System.out.println("\nErro na simulação: " + e.getMessage());
        }
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

    private double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}