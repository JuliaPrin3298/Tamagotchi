package tama;

public class Panda extends Animal {
    public String raca;
    public String cor;
    public String pelagem;

    @Override
    public String emitirSom() {
        return "GRRR GRR";
    }

    public void passear() {
        this.felicidade += 13;
        System.out.println(nome + " está passeando pela floresta de bambu!");
    }

    public void subirArvore() {
        System.out.println(nome + " está subindo na árvore!");
    }
}
