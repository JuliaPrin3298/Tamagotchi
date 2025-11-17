package tama;

public class Corvo extends Animal {
    private String raca;
    public String cor;
    public int inteligencia;

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getRaca() {
        return raca;
    }

    @Override
    public String emitirSom() {
        return "Cuáááá";
    }

    public void passear() {
        this.felicidade += 15;
        System.out.println(nome + " está sobrevoando o parque!");
    }

     public void Sobrevoar(){
        System.out.println("Sobrevoando o local");
    }
}
