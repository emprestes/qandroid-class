package br.com.quaddro.emprestes.qandroid100.domain.exception;

public class CPF {

    public void validar(String valor) throws Exception {

        if (valor == null) {
            throw new Exception("CPF nulo!");
        }

        if (valor.isEmpty()) {
            throw new CPFInvalidoException("CPF vazio!");
        }

        if (valor.length() != 11) {
            throw new CPFInvalidoException("CPF inválido!");
        }
    }
}
