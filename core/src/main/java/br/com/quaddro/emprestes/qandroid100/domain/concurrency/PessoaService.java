package br.com.quaddro.emprestes.qandroid100.domain.concurrency;

public class PessoaService implements Runnable {

    private Pessoa model;

    public void setModel(Pessoa model) {
        this.model = model;
    }

    public void cadastrar() {
        synchronized (System.out) {
            System.out.print("Cadastrando pessoa: ");
            System.out.println(model);
        }
    }

    @Override
    public void run() {
        cadastrar();
    }
}