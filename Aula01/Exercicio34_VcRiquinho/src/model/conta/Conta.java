package model.conta;

import model.cliente.Cliente;
import util.IdGenerator;

public abstract class Conta {

    private final int id;
    protected double saldo;
    protected Cliente cliente;

    protected Conta(Cliente cliente, double saldoInicial) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");

        if (saldoInicial < 0)
            throw new IllegalArgumentException("Saldo inicial inválido.");

        this.id = IdGenerator.generate(Conta.class);
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    public int getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido.");

        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido.");

        if (valor > saldo)
            throw new IllegalArgumentException("Saldo insuficiente.");

        saldo -= valor;
    }

    public abstract double calcularRendimento(int dias);

    public abstract String getTipoConta();
}