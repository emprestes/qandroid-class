package br.com.quaddro.emprestes.qandroid100.model;

import java.io.Serializable;

public class Tela implements Serializable {

    private float w, h;
    private byte tld, tle, tlt, tlb;

    public Tela(float w, float h) {
        super();

        this.w = w;
        this.h = h;

        tld = tle = tlt = tlb = 0;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public boolean isNotConfigTamanho() {
        return w <= 0 || h <= 0;
    }

    public boolean isToqueLadoTopo(float y) {
        return y < 0;
    }

    public boolean isToqueLadoBase(float y) {
        return y > h;
    }

    public boolean isToqueLadoEsquerdo(float x) {
        return x < 0;
    }

    public boolean isToqueLadoDireito(float x) {
        return x > w;
    }

    public boolean isToqueUnicoLadoTopo(float y) {
        boolean t = isToqueLadoTopo(y);
        tlt = isToqueUnico(t, tlt);
        return t && tlt == 1;
    }

    public boolean isToqueUnicoLadoBase(float y) {
        boolean t = isToqueLadoBase(y);
        tlb = isToqueUnico(t, tlb);
        return t && tlb == 1;
    }

    public boolean isToqueUnicoLadoEsquerdo(float x) {
        boolean t = isToqueLadoEsquerdo(x);
        tle = isToqueUnico(t, tle);
        return t && tle == 1;
    }

    public boolean isToqueUnicoLadoDireito(float x) {
        boolean t = isToqueLadoDireito(x);
        tld = isToqueUnico(t, tld);
        return t && tld == 1;
    }

    private byte isToqueUnico(boolean t, byte lado) {
        return !t ? 0 : lado < 2 ? ++lado : lado;
    }

    @Override
    public String toString() {
        return String.format("{ tld=%s, tlb=%s, tle=%s, tlt=%s, w=%s, h=%s }", tld, tlb, tle, tlt, w, h);
    }
}
