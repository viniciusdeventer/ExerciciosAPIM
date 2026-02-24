// Exercicio17.java
import java.util.Set;
import java.util.TreeSet;

class Produto implements Comparable<Produto> {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public int compareTo(Produto outro) {
        // Ordena do menor para o maior preço
        return Double.compare(this.preco, outro.preco);
    }

    @Override
    public String toString() {
        return nome + " - R$" + preco;
    }
}

public class Exercicio17 {
    public static void main(String[] args) {
        Set<Produto> produtos = new TreeSet<>();
        
        produtos.add(new Produto("Notebook", 4500.00));
        produtos.add(new Produto("Mouse", 150.00));
        produtos.add(new Produto("Teclado Mecânico", 300.00));

        System.out.println("\n Produtos ordenados por preço (Comparable):");
        for (Produto p : produtos) {
            System.out.println(" - " + p.toString());
        }
    }
}