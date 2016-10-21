package br.com.quaddro.emprestes.qandroid100.domain.concurrency;

public class CarroService {

    private Carro model;

    public CarroService(Carro model) {
        this.model = model;
    }

    public void cadastrar() {
        System.out.println("Cadastrando carro: " + model);
    }
}
