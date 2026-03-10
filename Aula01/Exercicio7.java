// Exercicio7.java
class Veiculo {
    protected String marca;
    protected String modelo;

    public Veiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo;
    }
}

class CarroPasseio extends Veiculo {
    private int numeroDePortas;

    public CarroPasseio(String marca, String modelo, int numeroDePortas) {
        super(marca, modelo);
        this.numeroDePortas = numeroDePortas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Portas: " + numeroDePortas;
    }
}

class Moto extends Veiculo {
    private int cilindradas;

    public Moto(String marca, String modelo, int cilindradas) {
        super(marca, modelo);
        this.cilindradas = cilindradas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Cilindradas: " + cilindradas + "cc";
    }
}

public class Exercicio7 {
    public static void main(String[] args) {
        CarroPasseio carro = new CarroPasseio("Fiat", "Argo", 4);
        Moto moto = new Moto("Yamaha", "MT-07", 689);

        System.out.println("\n Detalhes do Carro: " + carro.toString());
        System.out.println("\n Detalhes da Moto: " + moto.toString());
    }
}