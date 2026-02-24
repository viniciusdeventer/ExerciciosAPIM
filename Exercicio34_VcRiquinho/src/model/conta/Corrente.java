package model.conta;

import model.cliente.Cliente;

public class Corrente extends Conta {

    public Corrente(Cliente cliente, double saldoInicial) {
        super(cliente, saldoInicial);
    }

    @Override
    public double calcularRendimento(int dias) {
        return 0;
    }

    @Override
    public String getTipoConta() {
        return "CORRENTE";
    }
}