// Exercicio14.java
import java.util.HashSet;
import java.util.Set;

public class Exercicio14 {
    public static void main(String[] args) {
        Set<String> emails = new HashSet<>();

        emails.add("dev@empresa.com");
        emails.add("admin@empresa.com");
        emails.add("suporte@empresa.com");
        
        // Tentativa de adicionar duplicado
        boolean adicionouDuplicado = emails.add("dev@empresa.com");

        System.out.println("\n O e-mail duplicado foi adicionado? " + adicionouDuplicado);
        System.out.println("\n Tamanho do Set de e-mails: " + emails.size());
    }
}