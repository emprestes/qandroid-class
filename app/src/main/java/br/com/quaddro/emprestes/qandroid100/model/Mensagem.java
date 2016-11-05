package br.com.quaddro.emprestes.qandroid100.model;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private Integer id;
    private CharSequence titulo;
    private CharSequence corpo;


    private Mensagem(Integer id, CharSequence titulo, CharSequence corpo) {
        this.id = id;
        this.titulo = titulo;
        this.corpo = corpo;
    }

    public static Mensagem criar(CharSequence titulo, CharSequence corpo) {
        return criar((Integer) null, titulo, corpo);
    }

    public static Mensagem criar(String id, CharSequence titulo, CharSequence corpo) {
        return criar(Integer.valueOf(id), titulo, corpo);
    }

    public static Mensagem criar(Integer id, CharSequence titulo, CharSequence corpo) {
        return new Mensagem(id, titulo, corpo);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CharSequence getTitulo() {
        return titulo;
    }

    public void setTitulo(CharSequence titulo) {
        this.titulo = titulo;
    }

    public CharSequence getCorpo() {
        return corpo;
    }

    public void setCorpo(CharSequence corpo) {
        this.corpo = corpo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensagem)) return false;

        Mensagem mensagem = (Mensagem) o;

        if (getId() != null ? !getId().equals(mensagem.getId()) : mensagem.getId() != null)
            return false;
        if (getTitulo() != null ? !getTitulo().equals(mensagem.getTitulo()) : mensagem.getTitulo() != null)
            return false;
        return getCorpo() != null ? getCorpo().equals(mensagem.getCorpo()) : mensagem.getCorpo() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitulo() != null ? getTitulo().hashCode() : 0);
        result = 31 * result + (getCorpo() != null ? getCorpo().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Mensagem{");
        sb.append("id=").append(id);
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", corpo='").append(corpo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
