package br.com.quaddro.emprestes.qandroid100.domain.animal;

public class Tigre extends Felino {

    public Tigre() {
        super();
    }

    public Tigre(String nome, Integer idade) {
        super(nome, idade);
    }

    @Override
    public String comer(Object o) {
        return "Tigre comendo...";
    }

    @Override
    public String ronronar() {
        return "Tigre ronronando....";
    }

    @Override
    public String toString() {
        return "Tigre [" + super.toString() + "]";
    }
}