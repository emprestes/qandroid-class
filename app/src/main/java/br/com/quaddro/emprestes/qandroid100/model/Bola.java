package br.com.quaddro.emprestes.qandroid100.model;

import java.io.Serializable;

public class Bola implements Serializable {

    private long tempoAtual = 0;

    private float w, h;

    private Tela tela;
    private Posicao posicao;
    private Velocidade velocidade;
    private Aceleracao aceleracao;

    public Bola(float telaLargura, float telaAltura) {
        this(new Tela(telaLargura, telaAltura));
    }

    public Bola(Tela t) {
        super();

        this.tela = t;
        this.posicao = new Posicao(this);
        this.velocidade = new Velocidade();
        this.aceleracao = new Aceleracao();
    }

    public void setTamanho(float w, float h) {
        this.w = w;
        this.h = h;
    }

    public void setAceleracao(float x, float y) {
        this.aceleracao.set(x, y);
    }

    public void setTela(int w, int h) {
        tela.setW(w);
        tela.setH(h);
        posicao.atualizar(this);
    }

    public float getPosicaoX() { return posicao.getX(); }

    public float getPosicaoY() { return posicao.getY(); }

    public float getCenterX()  { return (tela.getW() / 2) - (w / 2); }

    public float getCenterY()  { return (tela.getH() / 2) - (h / 2); }

    public void parar() { setTela(0, 0); }

    public void posicionar(long tempoInMillis) {
        if (tela.isNotConfigTamanho())
            return;

        if (tempoAtual <= 0) {
            tempoAtual = tempoInMillis;
            return;
        }

        long tempoDecorrido = tempoInMillis - tempoAtual;
        tempoAtual = tempoInMillis;

        velocidade.aumentar(aceleracao, tempoDecorrido);
        posicao.atualizar(velocidade, tempoDecorrido);
    }

    public boolean isToqueTela() {
        return isToqueTelaLadoEsquerdo() ||
                isToqueTelaLadoDireito() ||
                isToqueTelaTopo() ||
                isToqueTelaBase();
    }

    public boolean isToqueTelaTopo() {
        return tela.isToqueLadoTopo(posicao.getY());
    }

    public boolean isToqueTelaBase() {
        return tela.isToqueLadoBase(posicao.getY() + h);
    }

    public boolean isToqueTelaLadoEsquerdo() {
        return tela.isToqueLadoEsquerdo(posicao.getX());
    }

    public boolean isToqueTelaLadoDireito() {
        return tela.isToqueLadoDireito(posicao.getX() + w);
    }

    public boolean isToqueUnicoTela() {
        return isToqueUnicoTelaLadoEsquerdo() ||
                isToqueUnicoTelaLadoDireito() ||
                isToqueUnicoTelaLadoTopo() ||
                isToqueUnicoTelaLadoBase();
    }

    public boolean isToqueUnicoTelaLadoTopo() {
        return tela.isToqueUnicoLadoTopo(posicao.getY());
    }

    public boolean isToqueUnicoTelaLadoBase() {
        return tela.isToqueUnicoLadoBase(posicao.getY() + h);
    }

    public boolean isToqueUnicoTelaLadoEsquerdo() {
        return tela.isToqueUnicoLadoEsquerdo(posicao.getX());
    }

    public boolean isToqueUnicoTelaLadoDireito() {
        return tela.isToqueUnicoLadoDireito(posicao.getX() + w);
    }

    public void ajustarRebote() {
        ajustarReboteVertical();
        ajustarReboteHorizontal();
    }

    private void ajustarReboteHorizontal() {
        boolean isBateuEmX = isToqueTelaLadoEsquerdo();
        if (isBateuEmX) {
            posicao.reboteEsquerdo();
            velocidade.reboteX();
        }

        isBateuEmX = isToqueTelaLadoDireito();
        if (isBateuEmX) {
            posicao.reboteDireito(tela.getW(), w);
            velocidade.reboteX();
        }

        if (isBateuEmX) {
            velocidade.ajustarHorizontalNegativa();
        }
    }

    private void ajustarReboteVertical() {
        boolean isBateuEmY = isToqueTelaTopo();
        if (isBateuEmY) {
            posicao.reboteTopo();
            velocidade.reboteY();
        }

        isBateuEmY = isToqueTelaBase();
        if (isBateuEmY) {
            posicao.reboteBase(tela.getH(), h);
            velocidade.reboteY();
        }

        if (isBateuEmY) {
            velocidade.ajustarVerticalNegativa();
        }
    }

    @Override
    public String toString() {
        return String.format("bola={ tela=%s, w=%s, h=%s, posicao=%s, velocidade=%s, aceleracao=%s, tempoAtual=%s }",
                tela, w, h, posicao, velocidade, aceleracao, tempoAtual);
    }

    public boolean isGol() {
        float meio = tela.getW() / 2;
        return isToqueTelaTopo() && (posicao.getY() > (meio - h) && posicao.getY() < (meio + h));
    }
}
