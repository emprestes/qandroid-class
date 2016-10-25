package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class AnimacaoActivity extends QuaddroActivity {

    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.animacao_view);

        iv = getViewById(R.id.iv_rico);
    }

    private void startAnimation(int id) {
        Animation a = AnimationUtils.loadAnimation(this, id);

        iv.startAnimation(a);
    }

    public void rotacionar01(View view) {
        startAnimation(R.anim.rotacionar_dir);
    }

    public void rotacionar02(View view) {
        startAnimation(R.anim.rotacionar_esq);
    }
}
