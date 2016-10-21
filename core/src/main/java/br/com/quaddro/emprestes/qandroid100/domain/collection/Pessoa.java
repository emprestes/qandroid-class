package br.com.quaddro.emprestes.qandroid100.domain.collection;

public class Pessoa implements Comparable<Pessoa> {

    private String nome;

    private String sobrenome;

    private Integer idade;

    public Pessoa(String nome, String sobrenome, Integer idade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    @Override
    public int compareTo(Pessoa o) {
        int comp = sobrenome.compareToIgnoreCase(o.sobrenome);

        comp = comp == 0 ? nome.compareToIgnoreCase(o.nome) : comp;
        comp = comp == 0 ? idade.compareTo(o.idade) : comp;

        return comp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", sobrenome='").append(sobrenome).append('\'');
        sb.append(", idade=").append(idade);
        sb.append('}');
        return sb.toString();
    }
}
