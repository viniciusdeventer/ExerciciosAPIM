// Exercicio26.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Renomeamos para evitar conflito com a classe Produto de outros exercícios
class ProdutoCatalogo {
    String nome;
    
    public ProdutoCatalogo(String nome) { 
        this.nome = nome; 
    }
    
    @Override 
    public String toString() { 
        return nome; 
    }
}

public class Exercicio26 {
    public static void main(String[] args) {
        // Atualizamos o tipo genérico da List para a nossa nova classe
        Map<String, List<ProdutoCatalogo>> catalogo = new HashMap<>();

        // Inicializando as listas para cada categoria
        catalogo.put("Eletrônicos", new ArrayList<>());
        catalogo.put("Roupas", new ArrayList<>());

        // Adicionando produtos usando o novo nome da classe
        catalogo.get("Eletrônicos").add(new ProdutoCatalogo("Smartphone"));
        catalogo.get("Eletrônicos").add(new ProdutoCatalogo("Tablet"));
        catalogo.get("Roupas").add(new ProdutoCatalogo("Camiseta"));

        System.out.println("\n Listando produtos da categoria 'Eletrônicos':");
        List<ProdutoCatalogo> eletronicos = catalogo.get("Eletrônicos");
        
        for (ProdutoCatalogo p : eletronicos) {
            System.out.println(" - " + p);
        }
    }
}