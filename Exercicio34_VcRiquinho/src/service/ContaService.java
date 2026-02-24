package service;

import java.util.List;

import model.cliente.Cliente;
import model.conta.CDI;
import model.conta.Conta;
import model.conta.Corrente;
import model.conta.InvestimentoAutomatico;

public class ContaService {

    public Conta criarContaCorrente(Cliente cliente, double saldoInicial) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");

        Conta conta = new Corrente(cliente, saldoInicial);
        cliente.adicionarConta(conta);

        return conta;
    }
    
    public Conta criarContaCDI(Cliente cliente, double saldoInicial) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");

        Conta conta = new CDI(cliente, saldoInicial);
        cliente.adicionarConta(conta);

        return conta;
    }
    
    public Conta criarContaInvestimentoAutomatico(Cliente cliente, double saldoInicial) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");

        Conta conta = new InvestimentoAutomatico(cliente, saldoInicial);
        cliente.adicionarConta(conta);

        return conta;
    }

    public Conta buscarPorId(Cliente cliente, int id) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");

        return cliente.getContas()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Conta> listarContas(Cliente cliente) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");

        return cliente.getContas();
    }

    public boolean removerConta(Cliente cliente, int id) {
        Conta conta = buscarPorId(cliente, id);

        if (conta == null)
            return false;

        cliente.removerConta(conta);
        return true;
    }

    public boolean depositar(Cliente cliente, int id, double valor) {
        Conta conta = buscarPorId(cliente, id);

        if (conta == null)
            return false;

        conta.depositar(valor);
        return true;
    }

    public boolean sacar(Cliente cliente, int id, double valor) {
        Conta conta = buscarPorId(cliente, id);

        if (conta == null)
            return false;

        conta.sacar(valor);
        return true;
    }
    
    public double simular(Conta conta, int dias) {

        if (conta == null)
            throw new IllegalArgumentException("Conta não pode ser nula.");
        
        if (dias != 30 && dias != 60 && dias != 90 && dias != 180) {
        	throw new IllegalArgumentException("Período inválido. Selecione um período de 30, 60, 90 ou 180 idas");
        }

        return conta.calcularRendimento(dias);
    }

}