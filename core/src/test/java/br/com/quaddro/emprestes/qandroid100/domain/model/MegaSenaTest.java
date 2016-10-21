package br.com.quaddro.emprestes.qandroid100.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MegaSenaTest {

    @Test
    public void sortearTest() {
        MegaSena model = new MegaSena();
        CharSequence sorteio;

        sorteio = model.sortear();

        Assert.assertNotNull(sorteio);
        //Assert.assertEquals(17, sorteio.length());
    }
}
