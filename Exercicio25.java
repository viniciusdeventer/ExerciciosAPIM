// Exercicio25.java
import java.util.ArrayDeque;
import java.util.Deque;

public class Exercicio25 {
    public static void main(String[] args) {
        // Deque usado como Stack segue o padrão LIFO (Last In, First Out)
        Deque<String> pilhaLivros = new ArrayDeque<>();

        System.out.println("\n Empilhando livros...");
        pilhaLivros.push("Dom Casmurro");
        pilhaLivros.push("Harry Potter");
        pilhaLivros.push("Senhor dos Anéis"); // Último a entrar

        System.out.println(" Topo atual (sem remover): " + pilhaLivros.peek());

        System.out.println("\n Removendo o livro do topo...");
        String livroRemovido = pilhaLivros.pop();
        System.out.println(" Livro removido: " + livroRemovido);

        System.out.println(" Novo topo da pilha: " + pilhaLivros.peek());
    }
}