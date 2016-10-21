package br.com.quaddro.emprestes.qandroid100.domain.animal;

import org.junit.Test;

import br.com.quaddro.emprestes.qandroid100.domain.factory.AnimalFactory;

public class AnimalTest {

    // Estado
    // Construtores

    // Comportamentos
    @Test
    public void animalTest() {
        Animal[] animais = AnimalFactory.createList();
        FelinoTest ft = new FelinoTest();
        CaninoTest ct = new CaninoTest();
        DomesticoTest dt = new DomesticoTest();

        for (Animal a : animais) { // forEach
            System.out.println(a);
            System.out.println(a.comer(new Object())); // Comportamento polimorficos

            ft.test(a);
            ct.test(a);
            dt.test(a);
        }
    }

    // classes internas (Inner classes)
    private class FelinoTest {

        void test(Animal a) {
            if (a instanceof Felino) {
                System.out.println(((Felino) a).ronronar()); // Comportamento polimorficos
            }
        }
    }

    private class CaninoTest {

        void test(Animal a) {
            if (a instanceof Canino) {
                Canino c = (Canino) a;
                System.out.println(c.latir()); // Comportamento polimorficos
                System.out.println(c.uivar()); // Comportamento polimorficos
            }
        }
    }

    private class DomesticoTest {

        void test(Animal a) {
            if (a instanceof Domestico) {
                System.out.println(((Domestico) a).brincar());
            }
        }
    }
}
