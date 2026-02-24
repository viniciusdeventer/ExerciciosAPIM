import java.util.Scanner;

public class Exercicio1 {
    public static void main (String[] args){ 
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Entre com a primeira nota: ");
        double nota1 = scanner.nextDouble();

        System.out.println("\n Entre com a segunda nota: ");
        double nota2 = scanner.nextDouble();

        System.out.println("\n Entre com a terceira nota: ");
        double nota3 = scanner.nextDouble();

        double media = (nota1 + nota2 + nota3)/3;
        
        System.out.println("A média do aluno foi de: " + media);

        if(media >= 7){
            System.out.println("O Aluno foi aprovado!");
        } else if (media >= 5){
            System.out.println("O Aluno está de recuperação!");
        } else {
            System.out.println("O Aluno foi reprovado!");
        }

        scanner.close();
    }   
}