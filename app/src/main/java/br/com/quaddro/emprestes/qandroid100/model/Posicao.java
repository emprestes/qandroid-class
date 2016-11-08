package br.com.quaddro.emprestes.qandroid100.model;

import java.io.Serializable;

public class Posicao implements Serializable {

    private float x, y;

    public Posicao(Bola b) {
        this(b.getCenterX(), b.getCenterY());
    }

    public Posicao(float x, float y) {
        super();

        this.y = y;
        this.x = x;
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

    public void atualizar(Bola b) {
        setX(b.getCenterX());
        setY(b.getCenterY());
    }

    public void atualizar(Velocidade v, long tempoInMillis) {
        x += (multiplicarX(v, tempoInMillis) / 1000) * Velocidade.PIXELS_POR_METRO;
        y += (multiplicarY(v, tempoInMillis) / 1000) * Velocidade.PIXELS_POR_METRO;
    }

    private float multiplicarX(Velocidade v, long tempoInMillis) {
        return multiplicar(v.getX(), tempoInMillis);
    }

    private float multiplicarY(Velocidade v, long tempoInMillis) {
        return multiplicar(v.getY(), tempoInMillis);
    }

    private float multiplicar(float p, long tempoInMillis) {
        return p * tempoInMillis;
    }

    public void reboteTopo() {
        setY(0);
    }

    public void reboteBase(float telaAltura, float bolaAltura) {
        setY(telaAltura - bolaAltura);
    }

    public void reboteEsquerdo() {
        setX(0);
    }

    public void reboteDireito(float telaLargura, float bolaLargura) {
        setX(telaLargura - bolaLargura);
    }

    @Override
    public String toString() {
        return String.format("{ x=%s, y=%s }", x, y);
    }
}
