package tama;

public class Gato extends Animal {
    public String raca;
    public String cor;
    public String pelagem;

    @Override
    public String emitirSom() {
        return "Miau Miau";
    }

    public void passear() {
        this.felicidade += 10;
        System.out.println(nome + " está passeando pela rua!");
    }

    public void pegarRato() {
        System.out.println(nome + " está correndo atrás do rato!");
    }
}
