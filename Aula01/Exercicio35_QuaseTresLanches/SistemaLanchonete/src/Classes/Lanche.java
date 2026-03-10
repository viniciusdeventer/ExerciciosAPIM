// Classe Lanche

package Classes;

public class Lanche extends Prato {
    private String pao;
    private String recheio;
    private String molho;


    public Lanche(double precoVenda, String dataValidade, double peso, String pao, String recheio, String molho) {
        super(precoVenda, dataValidade, peso);
        this.pao = pao;
        this.recheio = recheio;
        this.molho = molho;
    }

    @Override
    public void exibirDetalhes(){
        System.out.println("- Lanche [Pao de: " + pao + " | Recheio de: " + recheio + " | Molho de: " + molho + "]");
        System.out.printf(" Data de Validade: %s | Peso: %.2fkg | Preço: R$ %.2f\n", dataValidade, peso, precoVenda);
    }
}