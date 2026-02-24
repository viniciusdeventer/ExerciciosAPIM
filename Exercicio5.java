// Exercicio5.java
class Carro {
    private String marca;
    private String modelo;
    private int ano;

    public Carro(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }

    public void exibirInfo() {
        System.out.println("\n Informações do Veículo:");
        System.out.println(" Marca: " + marca + " | Modelo: " + modelo + " | Ano: " + ano);
    }
}

public class Exercicio5 {
    public static void main(String[] args) {
        Carro meuCarro = new Carro("Honda", "Civic", 2024);
        meuCarro.exibirInfo();
    }
}