// Exercicio8.java
class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}

class ContaBancaria {
    private double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Tentativa de saque de R$" + valor + " negada. Saldo disponível: R$" + saldo);
        }
        saldo -= valor;
        System.out.println("\n Saque de R$" + valor + " realizado com sucesso!");
    }
}

public class Exercicio8 {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(100.0);

        try {
            conta.sacar(150.0); // Isso forçará a exceção
        } catch (SaldoInsuficienteException e) {
            System.out.println("\n Erro na transação: " + e.getMessage());
        }
    }
}