// Classe Pedido

package Classes;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String nomeCliente;
    private List<Prato> itensConsumidos;
    private double taxaServico;

    public Pedido(String nomeCliente, double taxaServico){
        this.nomeCliente = nomeCliente;
        this.taxaServico = taxaServico;
        this.itensConsumidos = new ArrayList<>();
    }

    public void adicionarItem (Prato item) {
        itensConsumidos.add(item);
    }

    public double calcularTotal() {
        double totalItens = 0;
        for (Prato item : itensConsumidos){
            totalItens += item.getPrecoVenda();
        }
        return totalItens + taxaServico;
    }

    public void mostrarFatura() {
        System.out.println("\n---------------------------//---------------------------"); 
        System.out.println(" NOTA FISCAL DE SERVIÇOS ELETRÔNICA - QUASE TRÊS LANCHES");
        System.out.println("---------------------------//---------------------------");
        System.out.println(" Nome do Cliente: " + nomeCliente);
        System.out.println("----------------------------------------");
        System.out.println(" Itens Consumidos pelo Cliente:");
        
        for (Prato item : itensConsumidos) {
            // Nessa parte é utilizado o polimorfismo, sendo assim cada objeto vai conseguir imprimir seus próprios detalhes
            item.exibirDetalhes(); 
        }
        
        System.out.println("----------------------------------------");
        System.out.printf(" Taxa de Serviço: R$ %.2f\n", taxaServico);
        System.out.printf(" TOTAL DO PEDIDO: R$ %.2f\n", calcularTotal());
        System.out.println("---------------------------//---------------------------");
    }

        // Função de Troco

        public void calcularTroco(double valorPago){
            double total = calcularTotal();
            System.out.printf("\n Valor Recebido: R$ %.2f\n", valorPago);

            if (valorPago >= total){
                double troco = valorPago - total;
                System.out.printf(" Troco a devolver: R$ %.2f\n", troco);
            }else {
            double valorFaltante = total - valorPago;
            System.out.printf(" ALERTA: O Pagamento foi insuficiente! Faltam R$ %.2f\n", valorFaltante);
        }
            System.out.println("---------------------------//---------------------------");
        }
}

