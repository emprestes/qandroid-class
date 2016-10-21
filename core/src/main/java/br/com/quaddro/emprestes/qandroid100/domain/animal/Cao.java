package br.com.quaddro.emprestes.qandroid100.domain.animal;

public class Cao extends Canino implements Domestico {

    public Cao() {
        super();
    }

    public Cao(String nome, Integer idade) {
        super(nome, idade);
    }

    public String brincar() {
        return "Brincando com o cão!";
    }

    @Override
    public String comer(Object o) {
        return "Cão comendo... " + o;
    }

    @Override
    public String uivar() {
        return "Cão uivando...";
    }

    @Override
    public String latir() {
        return "AU AU AU AU";
    }

    @Override
    public String toString() {
        return "Cao [" + super.toString() + "]";
    }
}
