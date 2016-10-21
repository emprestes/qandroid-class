package br.com.quaddro.emprestes.qandroid100.domain.animal;

public class Leao extends Felino {

    public Leao() {
        super();
    }

    public Leao(String nome, Integer idade) {
        super(nome, idade);
    }

    @Override
    public String comer(Object o) {
        return "Leão comendo!!!";
    }

    @Override
    public String ronronar() {
        return "Leão ronronando...";
    }

    @Override
    public String toString() {
        return "Leao [" + super.toString() + "]";
    }
}
