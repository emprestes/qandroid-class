package br.com.quaddro.emprestes.qandroid100.domain.animal;

public abstract class Animal { // Melhor definição do objeto.

    // Estado (atributos)
    private String nome; // tem um!
    private Integer idade;

    // Construtor (comportamento)
    public Animal() {
        super();

        this.idade = 0;
    }

    public Animal(String nome, Integer idade) {
        this();

        this.nome = nome;
        this.idade = idade;
    }

    // Comportamentos (métodos)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
        return;
    }

    public abstract String comer(Object o);

    @Override
    public String toString() {
        return getNome() + ", " + getIdade();
    }
}
