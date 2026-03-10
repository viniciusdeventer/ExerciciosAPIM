// Exercicio30.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Aluno {
    String nome;
    double nota;
    public Aluno(String nome, double nota) { this.nome = nome; this.nota = nota; }
    @Override public String toString() { return nome + " (" + nota + ")"; }
}

public class Exercicio30 {
    public static void main(String[] args) {
        List<Aluno> listaAlunos = new ArrayList<>();
        listaAlunos.add(new Aluno("João", 8.5));
        listaAlunos.add(new Aluno("Maria", 4.0));
        listaAlunos.add(new Aluno("Pedro", 6.5));
        listaAlunos.add(new Aluno("Ana", 9.0));
        listaAlunos.add(new Aluno("Lucas", 2.5));

        Map<String, List<Aluno>> grupos = new HashMap<>();
        grupos.put("Aprovados", new ArrayList<>());
        grupos.put("Recuperação", new ArrayList<>());
        grupos.put("Reprovados", new ArrayList<>());

        for (Aluno aluno : listaAlunos) {
            if (aluno.nota >= 7) {
                grupos.get("Aprovados").add(aluno);
            } else if (aluno.nota >= 5) {
                grupos.get("Recuperação").add(aluno);
            } else {
                grupos.get("Reprovados").add(aluno);
            }
        }

        System.out.println("\n --- Agrupamento por Notas ---");
        for (String status : grupos.keySet()) {
            System.out.println(" " + status + ": " + grupos.get(status));
        }
    }
}