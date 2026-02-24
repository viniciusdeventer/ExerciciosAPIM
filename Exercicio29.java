// Exercicio29.java
import java.util.ArrayList;
import java.util.List;

class Navegador {
    private List<String> historico = new ArrayList<>();
    private int indiceAtual = -1;

    public void visitar(String url) {
        // Se visitamos uma nova página e não estamos no final, apagamos o "futuro"
        while (historico.size() > indiceAtual + 1) {
            historico.remove(historico.size() - 1);
        }
        historico.add(url);
        indiceAtual++;
        System.out.println(" Visitando: " + url);
    }

    public void voltar() {
        if (indiceAtual > 0) {
            indiceAtual--;
            System.out.println(" Voltando para: " + historico.get(indiceAtual));
        } else {
            System.out.println(" Não há páginas anteriores.");
        }
    }

    public void avancar() {
        if (indiceAtual < historico.size() - 1) {
            indiceAtual++;
            System.out.println(" Avançando para: " + historico.get(indiceAtual));
        } else {
            System.out.println(" Não há páginas para avançar.");
        }
    }
}

public class Exercicio29 {
    public static void main(String[] args) {
        Navegador browser = new Navegador();
        System.out.println("\n --- Histórico de Navegação ---");
        
        browser.visitar("google.com");
        browser.visitar("stackoverflow.com");
        browser.visitar("github.com");
        
        browser.voltar(); // Volta para StackOverflow
        browser.voltar(); // Volta para Google
        browser.avancar(); // Avança para StackOverflow
        
        browser.visitar("alura.com.br"); // Novo histórico a partir daqui
    }
}