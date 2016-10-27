package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class WebActivity extends QuaddroActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.web_view);
    }
}
