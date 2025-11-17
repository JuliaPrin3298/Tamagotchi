package tama;

public class Jogo {
    public boolean ativo;
    public int tempoDeJogo; // em segundos
    public Animal animalAtual;

    private Thread contadorThread;

    // Inicia o jogo com um animal
    public void iniciar(Animal animal) {
        this.animalAtual = animal;
        ativo = true;
        tempoDeJogo = 0;
        System.out.println("Inicializando Tamagotchi com " + animalAtual.nome + "...");

        contadorThread = new Thread(() -> {
            while (ativo) {
                try {
                    Thread.sleep(1000); // espera 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tempoDeJogo++;
            }
        });
        contadorThread.start();
    }

    // Desliga o jogo
    public void desligar() {
        ativo = false;
        System.out.println("Jogo desligado. Tempo total de uso: " + tempoDeJogo + " segundos.");
    }

    // Mostra status do animal atual
    public void mostrarAnimal() {
        if (animalAtual != null) {
            System.out.println("=== Status do Animal Atual ===");
            animalAtual.mostrarStatus();
            System.out.println("Emitindo som: " + animalAtual.emitirSom());
        } else {
            System.out.println("Nenhum animal selecionado.");
        }
    }

    // Para testes rápidos
    public static void main(String[] args) throws InterruptedException {
        Cachorro rex = new Cachorro();
        rex.nome = "Rex";
        rex.energia = 50;
        rex.fome = 50;
        rex.felicidade = 50;
        rex.saude = 50;

        Gato mimi = new Gato();
        mimi.nome = "Mimi";
        mimi.energia = 50;
        mimi.fome = 50;
        mimi.felicidade = 50;
        mimi.saude = 50;

        Panda pelin = new Panda();
        pelin.nome = "Pelin";
        pelin.energia = 50;
        pelin.fome = 50;
        pelin.felicidade = 50;
        pelin.saude = 50;

        Corvo coro = new Corvo();
        coro.nome = "Coro";
        coro.energia = 50;
        coro.fome = 50;
        coro.felicidade = 50;
        coro.saude = 50;

        Jogo jogo = new Jogo();
        jogo.iniciar(rex);

        Thread.sleep(3000); // espera 3 segundos
        rex.brincar();
        rex.alimentar();
        jogo.mostrarAnimal();

        Thread.sleep(2000);
        jogo.desligar();
    }
}
