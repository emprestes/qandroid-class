package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.os.PersistableBundle;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class JogoBolaActivity extends QuaddroActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.jogo_bola_view);
    }
}
