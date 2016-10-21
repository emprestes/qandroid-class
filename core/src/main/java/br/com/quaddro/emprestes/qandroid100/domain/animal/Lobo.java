package br.com.quaddro.emprestes.qandroid100.domain.animal;

public class Lobo extends Canino { // é um!

    public Lobo() {
        super();
    }

    public Lobo(String nome, Integer idade) {
        super(nome, idade);
    }

    @Override // anotação
    public String comer(Object o) {
        return "Lobo comendo..." + o;
    }

    @Override
    public String uivar() {
        return "Lobo uivando....";
    }

    @Override
    public String latir() {
        return "Lobo latindo....";
    }

    @Override
    public String toString() {
        return "Lobo [" + super.toString() + "]";
    }
}
