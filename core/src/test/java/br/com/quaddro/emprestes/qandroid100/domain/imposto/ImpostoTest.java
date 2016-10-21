package br.com.quaddro.emprestes.qandroid100.domain.imposto;

import org.junit.Test;

public class ImpostoTest {

    public final synchronized void test() {

    }

    public final synchronized void test(String t) {

    }

    public final synchronized void test(String t, String d) {

    }

    public final synchronized void test(int e, int p) {

    }

    @Test
    public void impostoTest() {
        Imposto[] impostos = {
                new IPTU(),
                new IPVA(),
                new IR(),
                new Imposto() { // classe anônima

                    @Override
                    public double calcular(String valor) {
                        return calcular(Double.valueOf(valor));
                    }

                    @Override
                    public double calcular(double valor) {
                        return valor + 15;
                    }
                },
                new IPVA() { // objeto anônimo

                    @Override
                    public double calcular(double valor) {
                        return super.calcular(valor) + 30;
                    }
                },
                new IPVA() { // objeto anônimo

                    private int index = 3;

                    @Override
                    public double calcular(double valor) {
                        class A {
                            private int m = 45;

                            public int getM() {
                                return m;
                            }
                        }

                        A a = new A();

                        return super.calcular(valor) * index * a.getM();
                    }
                }
        };

        String valor = "12.9";

        for (int i = 0; i < impostos.length; i++) {
            System.out.println(impostos[i].calcular(valor));
        }
    }
}
