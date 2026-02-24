package model.conta;

import model.cliente.Cliente;

public class CDI extends Conta {

    private static final double CDI_MENSAL = 0.01; 
    private static final double TAXA_SERVICO = 0.0007; 

    public CDI(Cliente cliente, double saldoInicial) {
        super(cliente, saldoInicial);
    }

    @Override
    public double calcularRendimento(int dias) {
        double cdiDiario = CDI_MENSAL / 30;
        double rendimentoBruto = saldo * cdiDiario * dias;

        double taxa = rendimentoBruto * TAXA_SERVICO;

        return rendimentoBruto - taxa;
    }

    @Override
    public String getTipoConta() {
        return "CDI";
    }
}