// Exercicio24.java
import java.util.LinkedList;
import java.util.Queue;

public class Exercicio24 {
    public static void main(String[] args) {
        // Queue segue o padrão FIFO (First In, First Out)
        Queue<String> filaImpressao = new LinkedList<>();

        filaImpressao.add("Documento1.pdf");
        filaImpressao.add("Foto_Ferias.png");
        filaImpressao.add("Relatorio_Financeiro.xls");
        filaImpressao.add("TCC_Final.docx");
        filaImpressao.add("Boleto.pdf");

        System.out.println("\n Iniciando processo de impressão...");

        while (!filaImpressao.isEmpty()) {
            // poll() recupera e remove o elemento da cabeça da fila
            String documento = filaImpressao.poll();
            System.out.println(" Imprimindo: " + documento);
        }
    }
}