package br.com.quaddro.emprestes.qandroid100.domain.endereco;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoTest {

    private Endereco model = new Endereco();

    @Mock
    private Logradouro logradouro;

    @Test
    public void getEnderecoOKTest() {
        String e;
        model.setLogradouro(logradouro);
        model.setNumero("13");

        Mockito.when(logradouro.toString()).thenReturn("Avenida Paulista");

        e = model.getEndereco();
        Assert.assertNotNull(e);
        Assert.assertEquals("Avenida Paulista, 13", e);
    }
}
