package br.com.quaddro.emprestes.qandroid100.domain.animal;

public abstract class Canino extends Animal {

    public Canino() {
        super();
    }

    public Canino(String nome, Integer idade) {
        super(nome, idade);
    }

    public abstract String uivar();

    abstract public String latir();
}
