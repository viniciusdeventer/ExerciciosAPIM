// Exercicio31.java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Renomeamos para ProdutoReflexao para evitar o conflito no pacote
class ProdutoReflexao {
    private int codigo;
    public String nome;
    protected double preco;

    public ProdutoReflexao(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    private double calcularImposto() {
        return preco * 0.1;
    }
}

class AnalisadorDeClasse {
    
    public static void inspecionar(Object obj) {
        Class<?> clazz = obj.getClass();

        System.out.println("\n --- Inspeção Dinâmica de Objeto ---");
        System.out.println(" Nome completo da Classe: " + clazz.getName());

        System.out.println("\n Atributos (Campos) declarados:");
        Field[] campos = clazz.getDeclaredFields();
        for (Field campo : campos) {
            System.out.println(" - " + campo.getName() + " [Tipo: " + campo.getType().getSimpleName() + "]");
        }

        System.out.println("\n Métodos declarados:");
        Method[] metodos = clazz.getDeclaredMethods();
        for (Method metodo : metodos) {
            System.out.println(" - " + metodo.getName() + " [Retorno: " + metodo.getReturnType().getSimpleName() + "]");
        }
        System.out.println(" -----------------------------------");
    }
}

public class Exercicio31 {
    public static void main(String[] args) {
        System.out.println("\n Instanciando o objeto ProdutoReflexao...");
        
        // Instanciamos usando o novo nome da classe
        ProdutoReflexao p = new ProdutoReflexao(101, "Notebook Gamer", 8500.0);

        AnalisadorDeClasse.inspecionar(p);
    }
}