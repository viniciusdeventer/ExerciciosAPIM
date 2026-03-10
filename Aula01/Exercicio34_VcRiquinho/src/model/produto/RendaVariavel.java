package model.produto;

public class RendaVariavel extends Produto {

    private double rendimentoMensalVariavel;

    public RendaVariavel(String nome, String descricao, double rendimentoMensalVariavel) {
        super(nome, descricao);
        setRendimentoMensalVariavel(rendimentoMensalVariavel);

        if (rendimentoMensalVariavel <= 0)
            throw new IllegalArgumentException("Rendimento mensal esperado inválido.");

        this.rendimentoMensalVariavel = rendimentoMensalVariavel;
    }

    public double getRendimentoMensalVariavel() {
        return rendimentoMensalVariavel;
    }
    
    public void setRendimentoMensalVariavel(double rendimentoMensalVariavel) {
        if (rendimentoMensalVariavel <= 0)
            throw new IllegalArgumentException("Rendimento mensal esperado inválido.");

        this.rendimentoMensalVariavel = rendimentoMensalVariavel;
    }

    @Override
    public String getTipoProduto() {
        return "RendaVariavel";
    }
    
    @Override
    public double calcularRendimento(double valorAplicado, int dias) {
        int meses = dias / 30;
        return valorAplicado * rendimentoMensalVariavel * meses;
    }
}