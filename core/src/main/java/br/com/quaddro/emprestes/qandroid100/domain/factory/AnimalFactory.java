package br.com.quaddro.emprestes.qandroid100.domain.factory;

import br.com.quaddro.emprestes.qandroid100.domain.animal.Animal;
import br.com.quaddro.emprestes.qandroid100.domain.animal.Cao;
import br.com.quaddro.emprestes.qandroid100.domain.animal.Gato;
import br.com.quaddro.emprestes.qandroid100.domain.animal.Leao;
import br.com.quaddro.emprestes.qandroid100.domain.animal.Lobo;
import br.com.quaddro.emprestes.qandroid100.domain.animal.Pato;
import br.com.quaddro.emprestes.qandroid100.domain.animal.Tigre;

public final class AnimalFactory {

    private AnimalFactory() {
        super();
    }

    public static Animal[] createList() {
        return new Animal[] {
                new Gato("Miau", 10),
                new Lobo(),
                new Pato(),
                new Leao("L", 5),
                new Cao("Bidu", 2), // encapsulamento
                new Tigre(),
                new Cao(),
                new Gato(),
                new Pato()
        };
    }
}
