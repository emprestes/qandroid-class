package br.com.quaddro.emprestes.qandroid100.domain.animal;

public class Gato extends Felino implements Domestico { // Classe concreta.

    public Gato() {
        super();
    }

    public Gato(String nome, Integer idade) {
        super(nome, idade);
    }

    public String brincar() {
        return "Brincando com o bixano!!!!";
    }

    @Override
    public String ronronar() {
        return "RRRRRRRRRRRRRRRRR";
    }

    @Override
    public String comer(Object o) {
        return "Gato comendo " + o;
    }

    @Override
    public String toString() {
        return "Gato [" + super.toString() + "]";
    }
}
