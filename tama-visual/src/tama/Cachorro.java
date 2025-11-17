package tama;

public class Cachorro extends Animal {
    public String raca;
    public String cor;
    public String pelagem;

    @Override
    public String emitirSom() {
        return "AuAuAu";
    }

    public void passear() {
        this.felicidade += 20;
        System.out.println(nome + " está passeando pela rua!");
    }

    public void roer() {
        this.fome += 10;
        System.out.println(nome + " está roendo um osso!");
    }
}
