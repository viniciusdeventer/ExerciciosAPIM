// Classe Prato

package Classes;

public abstract class Prato {
    protected double precoVenda;
    protected String dataValidade;
    protected double peso;


    public Prato(double precoVenda, String dataValidade, double peso) {
        this.precoVenda = precoVenda;
        this.dataValidade = dataValidade;
        this.peso = peso;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    // Aqui é usado o método abstrato para garantir o Polimorfismo nas futuras subclasses
    public abstract void exibirDetalhes();
}