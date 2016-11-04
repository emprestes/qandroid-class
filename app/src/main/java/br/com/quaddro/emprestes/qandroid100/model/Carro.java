package br.com.quaddro.emprestes.qandroid100.model;

import android.provider.BaseColumns;

import java.io.Serializable;

public class Carro implements Serializable {

    private CharSequence nome;
    private CharSequence fabricante;
    private CharSequence descricao;

    private Carro(CharSequence nome, CharSequence fabricante, CharSequence descricao) {
        super();

        this.nome = nome;
        this.fabricante = fabricante;
        this.descricao = descricao;
    }

    public static Carro criar(String[] campos) {
        return criar(campos[0], campos[1], campos[2]);
    }

    public static Carro criar(CharSequence nome, CharSequence fabricante) {
        return criar(nome, fabricante, "");
    }

    public static Carro criar(CharSequence nome, CharSequence fabricante, CharSequence descricao) {
        return new Carro(nome, fabricante, descricao);
    }

    public CharSequence getNome() {
        return nome;
    }

    public void setNome(CharSequence nome) {
        this.nome = nome;
    }

    public CharSequence getFabricante() {
        return fabricante;
    }

    public void setFabricante(CharSequence fabricante) {
        this.fabricante = fabricante;
    }

    public CharSequence getDescricao() {
        return descricao;
    }

    public void setDescricao(CharSequence descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carro)) return false;

        Carro carro = (Carro) o;

        if (getNome() != null ? !getNome().equals(carro.getNome()) : carro.getNome() != null)
            return false;
        return getFabricante() != null ? getFabricante().equals(carro.getFabricante()) : carro.getFabricante() == null;
    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (getFabricante() != null ? getFabricante().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s / %s", nome, fabricante);
    }

    public String toCSV() {
        return String.format("%s;%s;%s", nome, fabricante, descricao);
    }
}
