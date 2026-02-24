// Exercicio23.java
import java.util.HashMap;
import java.util.Map;

public class Exercicio23 {
    public static void main(String[] args) {
        Map<String, String> agenda = new HashMap<>();
        agenda.put("João", "9999-1111");
        agenda.put("Maria", "9888-2222");

        String nomeBusca = "João";
        String telefoneBusca = "9888-2222";

        System.out.println("\n Verificando existência de dados...");

        if (agenda.containsKey(nomeBusca)) {
            System.out.println(" A chave (nome) '" + nomeBusca + "' existe.");
        }

        if (agenda.containsValue(telefoneBusca)) {
            System.out.println(" O valor (telefone) '" + telefoneBusca + "' existe.");
        }
    }
}