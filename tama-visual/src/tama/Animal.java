package tama;

public abstract class Animal {
    public int id;
    public String nome;
    public int energia;
    public int fome;
    public int sede;
    public int felicidade;
    public int saude;

    // Construtor padrão
    public Animal() {}

    // Construtor completo
    public Animal(int id, String nome, int energia, int fome, int sede, int felicidade, int saude) {
        this.id = id;
        this.nome = nome;
        this.energia = energia;
        this.fome = fome;
        this.sede = sede;
        this.felicidade = felicidade;
        this.saude = saude;
    }

    // Métodos comuns que as subclasses podem usar
    public void alimentar() {
        this.fome += 5;
        System.out.println(nome + " foi alimentado! Fome atual: " + fome);
    }

    public void dormir() {
        this.energia += 100;
        System.out.println(nome + " está dormindo. Energia atual: " + energia);
    }

    public void brincar() {
        this.felicidade += 10;
        System.out.println(nome + " está brincando! Felicidade atual: " + felicidade);
    }

    public void irVeterinario() {
        this.saude += 100;
        System.out.println(nome + " foi ao veterinário! Saúde atual: " + saude);
    }

    public void Passear(){
		this.felicidade +=10;

		System.out.println("Passeando!");

	}

    // Método abstrato que obriga cada animal a implementar seu som
    public abstract String emitirSom();

    // Mostrar status completo do animal
    public void mostrarStatus() {
        System.out.println("=== Status de " + nome + " ===");
        System.out.println("Energia: " + energia);
        System.out.println("Fome: " + fome);
        System.out.println("Sede: " + sede);
        System.out.println("Felicidade: " + felicidade);
        System.out.println("Saúde: " + saude);
    }
}
