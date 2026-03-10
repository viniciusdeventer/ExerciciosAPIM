// Exercicio32.java
import java.lang.reflect.Field;

class Configuracao {
    // Atributo privado, inacessível pelos meios tradicionais fora da classe
    private String urlConexao = "localhost:5432";
    
    // Método auxiliar apenas para mostrar o valor sem usar reflection
    public void imprimirUrl() {
        System.out.println(" URL de Conexão atual: " + urlConexao);
    }
}

public class Exercicio32 {
    public static void main(String[] args) {
        Configuracao config = new Configuracao();

        System.out.println("\n --- Antes da modificação com Reflection ---");
        config.imprimirUrl();

        try {
            // 1. Obtém a referência do campo (Field) 'urlConexao' da classe Configuracao
            Field campoUrl = config.getClass().getDeclaredField("urlConexao");

            // 2. Quebra o encapsulamento, tornando o atributo privado acessível
            campoUrl.setAccessible(true);

            // 3. Altera o valor do campo na instância 'config'
            campoUrl.set(config, "db.producao.com:5432");

            System.out.println("\n --- Depois da modificação com Reflection ---");
            String novoValor = (String) campoUrl.get(config);
            System.out.println(" Novo valor lido via Reflection: " + novoValor);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("\n Erro ao manipular o atributo com Reflection: " + e.getMessage());
        }
    }
}