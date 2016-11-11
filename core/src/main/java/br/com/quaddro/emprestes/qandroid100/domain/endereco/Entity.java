package br.com.quaddro.emprestes.qandroid100.domain.endereco;

public class Entity<T extends Number> {

    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
