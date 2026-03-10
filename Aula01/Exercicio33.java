// Exercicio33.java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// 1. Definição da Anotação
@Retention(RetentionPolicy.RUNTIME) // Essencial para que a anotação esteja disponível em tempo de execução
@Target(ElementType.METHOD) // A anotação só pode ser aplicada a métodos
@interface Teste {
}

// 2. Classe de Teste com métodos anotados e não anotados
class MinhaClasseDeTeste {
    @Teste
    public void testeSoma() {
        System.out.println(" Executando testeSoma: SUCESSO");
    }

    public void metodoComum() {
        System.out.println(" Este não é um teste.");
    }

    @Teste
    public void testeLogin() {
        System.out.println(" Executando testeLogin: SUCESSO");
    }
}

// 3. O "Framework" executor de testes
class ExecutorDeTestes {
    public static void executarTestes(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] metodos = clazz.getDeclaredMethods();

        System.out.println("\n Iniciando execução de testes na classe: " + clazz.getSimpleName() + "\n");

        int testesExecutados = 0;

        for (Method metodo : metodos) {
            // Verifica se o método possui a anotação @Teste
            if (metodo.isAnnotationPresent(Teste.class)) {
                try {
                    // Invoca o método dinamicamente na instância (obj)
                    metodo.invoke(obj);
                    testesExecutados++;
                } catch (Exception e) {
                    System.out.println(" Falha ao executar o teste: " + metodo.getName());
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\n Resumo: " + testesExecutados + " teste(s) executado(s).");
    }
}

// 4. Classe principal para rodar o programa
public class Exercicio33 {
    public static void main(String[] args) {
        MinhaClasseDeTeste classeTeste = new MinhaClasseDeTeste();
        
        // Passamos o objeto para o nosso "framework" rodar os testes
        ExecutorDeTestes.executarTestes(classeTeste);
    }
}