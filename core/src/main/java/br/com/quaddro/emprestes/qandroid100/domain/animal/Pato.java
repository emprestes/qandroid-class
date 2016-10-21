package br.com.quaddro.emprestes.qandroid100.domain.animal;

public class Pato extends Animal implements Domestico {

    @Override
    public String comer(Object o) {
        return "Pato comendo...";
    }

    @Override
    public String brincar() {
        return "Pato brincando,...";
    }
}
