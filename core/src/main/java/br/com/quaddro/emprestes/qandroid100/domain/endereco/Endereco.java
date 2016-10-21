package br.com.quaddro.emprestes.qandroid100.domain.endereco;

public class Endereco {

    private String numero;
    private Logradouro logradouro;

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEndereco() {
        return logradouro.toString() + ", " + numero;
    }
}
