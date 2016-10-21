package br.com.quaddro.emprestes.qandroid100.domain.concurrency;

public class CarroTread extends Thread {

    private CarroService service;

    public CarroTread(CarroService service) {
        super();

        this.service = service;
    }

    @Override
    public void run() {
        service.cadastrar();
    }
}
