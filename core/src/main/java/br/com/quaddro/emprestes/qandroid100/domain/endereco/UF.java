package br.com.quaddro.emprestes.qandroid100.domain.endereco;

public class UF extends Entity<Short> {

    String sigla;

    @Override
    protected void finalize() throws Throwable {
        this.sigla = null;
        super.finalize();
    }
}
