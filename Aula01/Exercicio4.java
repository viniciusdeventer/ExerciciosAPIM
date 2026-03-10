public class Exercicio4 {
    public static void main(String[] args) {
        int[] numeros = {10, 15, 22, 33, 40, 51, 60};
        int somaImpares = 0;

        for (int numero : numeros) {
            if (numero % 2 != 0) {
                somaImpares += numero;
            }
        }

        System.out.println("\n A soma de todos os números ímpares do array é: " + somaImpares);
    }
}
    

