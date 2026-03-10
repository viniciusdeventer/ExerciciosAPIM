// Exercicio6.java
import java.util.Scanner;

class Circulo {
    private double raio;

    public Circulo(double raio) {
        setRaio(raio);
    }

    public double getRaio() { return raio; }

    public void setRaio(double raio) {
        if (raio <= 0) {
            throw new IllegalArgumentException("O raio não pode ser negativo ou zero.");
        }
        this.raio = raio;
    }

    public double calcularArea() {
        return Math.PI * Math.pow(raio, 2);
    }
}

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n Entre com o valor do raio do círculo: ");
        double raioDigitado = scanner.nextDouble();

        try {
            Circulo circulo = new Circulo(raioDigitado);
            System.out.println("\n A área do círculo é: " + circulo.calcularArea());
        } catch (IllegalArgumentException e) {
            System.out.println("\n Erro de Validação: " + e.getMessage());
        }

        scanner.close();
    }
}