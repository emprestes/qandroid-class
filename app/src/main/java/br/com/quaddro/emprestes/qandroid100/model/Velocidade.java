package br.com.quaddro.emprestes.qandroid100.model;

import java.io.Serializable;

public class Velocidade implements Serializable {

    public static final float MINIMA = 5f;
    public static final float PIXELS_POR_METRO = 25;
    public static final float REBOTE = 0.6f;

    private float x, y;

    public Velocidade() {
        this(0, 0);
    }

    public Velocidade(float x, float y) {
        super();

        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void aumentar(Aceleracao a, long tempoInMillis) {
        x += (a.multiplicarX(tempoInMillis) / 1000) * PIXELS_POR_METRO;
        y += (a.multiplicarY(tempoInMillis) / 1000) * PIXELS_POR_METRO;
    }

    public void reboteY() {
        y =- y * REBOTE;
    }

    public void reboteX() {
        x =- x * REBOTE;
    }

    public void ajustarVerticalNegativa() {
        if (Math.abs(y) < MINIMA)
            setY(0);
    }

    public void ajustarHorizontalNegativa() {
        if (Math.abs(x) < MINIMA)
            setX(0);
    }

    @Override
    public String toString() {
        return String.format("{ x=%s, y=%s }", x, y);
    }
}
