package br.com.quaddro.emprestes.qandroid100.domain.imposto;

public class IPVA implements Imposto {

    @Override
    public double calcular(String valor) {
        return calcular(Double.valueOf(valor));
    }

    @Override
    public double calcular(double valor) {
        return valor + 10;
    }
}
