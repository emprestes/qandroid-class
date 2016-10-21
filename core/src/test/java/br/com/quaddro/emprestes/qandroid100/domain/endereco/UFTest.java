package br.com.quaddro.emprestes.qandroid100.domain.endereco;

import org.junit.Test;

public class UFTest {

    @Test
    public void ufTest() {
        UF model; // variável local para ligação com UF.

        model = new UF(); // objeto

        model.sigla = "SP";
        System.out.println(model);

        model = new UF();
        System.out.println(model);
    }
}
