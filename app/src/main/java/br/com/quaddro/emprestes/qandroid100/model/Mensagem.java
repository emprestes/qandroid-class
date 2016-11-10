package br.com.quaddro.emprestes.qandroid100.model;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private Long nid;
    private String title;
    private String body;

    private Mensagem(Long nid, String title, String body) {
        this.nid = nid;
        this.title = title;
        this.body = body;
    }

    public static Mensagem criar(CharSequence titulo, CharSequence corpo) {
        return criar(titulo.toString(), corpo.toString());
    }

    public static Mensagem criar(String titulo, String corpo) {
        return criar((Long) null, titulo, corpo);
    }

    public static Mensagem criar(Long id, CharSequence titulo, CharSequence corpo) {
        return criar(id, titulo.toString(), corpo.toString());
    }

    public static Mensagem criar(String id, String titulo, String corpo) {
        return criar(Long.valueOf(id), titulo, corpo);
    }

    public static Mensagem criar(Long id, String titulo, String corpo) {
        return new Mensagem(id, titulo, corpo);
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensagem)) return false;

        Mensagem mensagem = (Mensagem) o;

        if (getNid() != null ? !getNid().equals(mensagem.getNid()) : mensagem.getNid() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(mensagem.getTitle()) : mensagem.getTitle() != null)
            return false;
        return getBody() != null ? getBody().equals(mensagem.getBody()) : mensagem.getBody() == null;

    }

    @Override
    public int hashCode() {
        int result = getNid() != null ? getNid().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Mensagem{");
        sb.append("nid=").append(nid);
        sb.append(", title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
