package br.com.quaddro.emprestes.qandroid100.domain.concurrency;

import org.junit.Test;

public class PessoaThreadTest {

    @Test
    public void test() {
        Thread t;
        PessoaService s;

        s = new PessoaService();

        for (int i = 0; i < 1000; i++) {
            s.setModel(new Pessoa("Pessoa" + i));
            t = new Thread(s);

            t.setPriority(Thread.MIN_PRIORITY);
            System.out.println("Executando o test...");
            t.start();
        }
    }
}
