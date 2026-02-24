// Lanchonete

package lanchonete;

import Classes.Lanche;
import Classes.Pedido;
import Classes.Pizza;
import Classes.Salgadinho;
import java.util.Scanner;

public class Lanchonete {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    // Aqui é feita a criação do Pedido
    System.out.println("\n -/- Sistema Lanchonete Quase Três Lanches -/-");
    System.out.print("\n Nome do Cliente:");
    String nome = scanner.nextLine();

    System.out.print("\n Será cobrada uma taxa para o serviço de entrega (Ex: 4,24). A taxa é de: ");
    double taxa = scanner.nextDouble();

    Pedido pedido = new Pedido(nome, taxa);
    

    // Instanciando a Pizza (precoVenda, dataValidade, peso, molho, recheio, borda)
    Pizza pizzaEspecial = new Pizza(65.90, "25/02/2026", 1.2, 
        "Molho de Tomate Rústico com Manjericão", 
        "Frango desfiado com Catupiry e Bacon", 
        "Recheada com Cheddar");

    // Instanciando o Lanche (precoVenda, dataValidade, peso, pao, recheio, molho)
    Lanche lancheCasa = new Lanche(38.50, "24/02/2026", 0.45, 
        "Pão Australiano", 
        "Duplo blend de costela, queijo prato e cebola caramelizada", 
        "Maionese defumada");

    // Instanciando o Salgadinho (precoVenda, dataValidade, peso, tipo, massa, recheio)
    Salgadinho salgadoGourmet = new Salgadinho(12.00, "26/02/2026", 0.18, 
        "Frito", 
        "Massa de Batata com ervas", 
        "Costela bovina com muçarela");

    // Adicionando os itens ao pedido

    pedido.adicionarItem(pizzaEspecial);
    pedido.adicionarItem(lancheCasa);
    pedido.adicionarItem(salgadoGourmet);

    // Gerando e exibindo a Nota Fiscal do Pedido
    pedido.mostrarFatura();

    // Calculando o Troco

    System.out.println("\n Digite o valor pago em dinheiro pelo cliente: R$");
    double valorRecebido = scanner.nextDouble();

    pedido.calcularTroco(valorRecebido);

    scanner.close();
    }
}