package model.conta;

import java.util.ArrayList;
import java.util.List;

import model.cliente.Cliente;
import model.produto.Produto;
import model.produto.RendaFixa;

public class InvestimentoAutomatico extends Conta {

    private final List<Produto> produtos = new ArrayList<>();

    public InvestimentoAutomatico(Cliente cliente, double saldoInicial) {
        super(cliente, saldoInicial);
    }

    public void adicionarProduto(Produto produto) {
        if (produto == null)
            throw new IllegalArgumentException("Produto não pode ser nulo.");

        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return List.copyOf(produtos);
    }

    @Override
    public double calcularRendimento(int dias) {
        if (produtos.isEmpty())
            return 0;

        double valorPorProduto = saldo / produtos.size();
        double rendimentoTotal = 0;

        for (Produto produto : produtos) {

            if (produto instanceof RendaFixa rf && rf.possuiCarencia(dias)) {
                continue;
            }

            rendimentoTotal += produto.calcularRendimento(valorPorProduto, dias);
        }

        double taxa = cliente.getTaxaServicoInvestimentoAutomatico();
        double valorTaxa = rendimentoTotal * taxa;

        return rendimentoTotal - valorTaxa;
    }

    @Override
    public String getTipoConta() {
        return "INVESTIMENTO_AUTOMATICO";
    }
}