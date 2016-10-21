package br.com.quaddro.emprestes.qandroid100.domain.concurrency;

import org.junit.Test;

public class CarroThreadTest {

    @Test
    public void test() {
        CarroTread tread;

        for (int i = 0; i < 1000; i++) {
            tread = new CarroTread(new CarroService(new Carro("Carro" + i)));

            tread.setName("T" + i);
            tread.setPriority(Thread.MIN_PRIORITY);
            System.out.println("test rodando...");
            tread.start();
        }
    }
}
