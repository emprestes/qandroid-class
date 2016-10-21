package br.com.quaddro.emprestes.qandroid100.domain.concurrency;

public class Carro {

    private String nome;

    public Carro(String nome) {
        super();

        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
