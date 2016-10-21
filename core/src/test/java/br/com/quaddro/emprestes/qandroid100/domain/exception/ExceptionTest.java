package br.com.quaddro.emprestes.qandroid100.domain.exception;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public void cpfTest() {
        CPF model = new CPF();

        try {
            model.validar("99999999999");
            System.out.println("CPF válido");
        } catch (CPFInvalidoException cause) {
            cause.printStackTrace();
        } catch (Exception cause) {
            System.out.println("Eita");
            cause.printStackTrace();
        }
    }
}
