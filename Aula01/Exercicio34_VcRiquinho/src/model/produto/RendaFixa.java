package model.produto;

public class RendaFixa extends Produto {

    private double rendimentoMensalFixo;
    private int periodoCarencia;

    public RendaFixa(String nome, String descricao, double rendimentoMensalFixo, int periodoCarencia) {
        super(nome, descricao);
        setRendimentoMensalFixo(rendimentoMensalFixo);

        if (rendimentoMensalFixo <= 0)
            throw new IllegalArgumentException("Rendimento mensal inválido.");

        if (periodoCarencia < 0)
            throw new IllegalArgumentException("Carência inválida.");

        this.rendimentoMensalFixo = rendimentoMensalFixo;
        this.periodoCarencia = periodoCarencia;
    }

    public double getRendimentoMensalFixo() {
        return rendimentoMensalFixo;
    }
    
    public void setRendimentoMensalFixo(double rendimentoMensalFixo) {
        if (rendimentoMensalFixo <= 0)
            throw new IllegalArgumentException("Rendimento mensal esperado inválido.");

        this.rendimentoMensalFixo = rendimentoMensalFixo;
    }

    public int getPeriodoCarencia() {
        return periodoCarencia;
    }
    
    public void setPeriodoCarencia(int periodoCarencia) {
    	if (periodoCarencia < 0)
    		throw new IllegalArgumentException("Período inválido");
    	
    	this.periodoCarencia = periodoCarencia;
    }

    public boolean possuiCarencia(int dias) {
        return dias < periodoCarencia;
    }

    @Override
    public double calcularRendimento(double valorAplicado, int dias) {
        int meses = dias / 30;
        return valorAplicado * rendimentoMensalFixo * meses;
    }

    @Override
    public String getTipoProduto() {
        return "RendaFixa";
    }
}