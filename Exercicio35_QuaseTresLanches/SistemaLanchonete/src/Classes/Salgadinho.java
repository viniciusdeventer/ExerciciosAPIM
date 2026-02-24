// Classe Salgadinho

package Classes;

public class Salgadinho extends Prato {
    private String tipo; // No caso seria Frito ou Assado
    private String massa;
    private String recheio;


    public Salgadinho(double precoVenda, String dataValidade, double peso, String tipo, String massa, String recheio) {
        super(precoVenda, dataValidade, peso);
        this.tipo = tipo;
        this.massa = massa;
        this.recheio = recheio;
    }

@Override
    public void exibirDetalhes() {
        System.out.println("- Salgadinho " + tipo + " [Massa: " + massa + " | Recheio: " + recheio + "]");
        System.out.printf(" Data de Validade: %s | Peso: %.2fkg | Preço: R$ %.2f\n", dataValidade, peso, precoVenda);
    }

}