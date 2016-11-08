package br.com.quaddro.emprestes.qandroid100.model;

import java.io.Serializable;

public class Aceleracao implements Serializable {

    private float x, y;

    public Aceleracao() {
        this(0, 0);
    }

    public Aceleracao(float x, float y) {
        super();

        set(x, y);
    }

    public void set(float x, float y) {
        this.x =- x;
        this.y = y;
    }

    public float multiplicarX(long tempoInMillis) {
        return multiplicar(x, tempoInMillis);
    }

    public float multiplicarY(long tempoInMillis) {
        return multiplicar(y, tempoInMillis);
    }

    private float multiplicar(float p, long tempoInMillis) {
        return p * tempoInMillis;
    }

    @Override
    public String toString() {
        return String.format("{ x=%s, y=%s }", x, y);
    }
}
