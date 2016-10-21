package br.com.quaddro.emprestes.qandroid100.domain.animal;

public abstract class Felino extends Animal {

    public Felino() {
        super();
    }

    public Felino(String nome, Integer idade) {
        super(nome, idade);
    }

    public abstract String ronronar();
}
