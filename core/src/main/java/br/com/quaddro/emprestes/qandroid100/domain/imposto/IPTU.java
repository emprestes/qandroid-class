package br.com.quaddro.emprestes.qandroid100.domain.imposto;

public final class IPTU implements Imposto {

    @Override
    public double calcular(String valor) {
        return calcular(Double.valueOf(valor));
    }

    @Override
    public double calcular(double valor) {
        return valor * 2;
    }
}
