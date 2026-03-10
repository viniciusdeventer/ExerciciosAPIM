// Classe Pizza

package Classes;

public class Pizza extends Prato {
    private String molho;
    private String recheio;
    private String borda;


    public Pizza(double precoVenda, String dataValidade, double peso, String molho, String recheio, String borda) {
        super(precoVenda, dataValidade, peso);
        this.molho = molho;
        this.recheio = recheio;
        this.borda = borda;
    }

    @Override
    public void exibirDetalhes(){
        System.out.println("- Pizza [Recheio: " + recheio + " | Borda: " + borda + " | Molho: " + molho + "]");
        System.out.printf(" Data de Validade: %s | Peso: %.2fkg | Preço: R$ %.2f\n", dataValidade, peso, precoVenda);
    }
}