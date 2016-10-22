package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import br.com.quaddro.emprestes.qandroid100.MainActivity;
import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class SplashActivity extends QuaddroActivity {

    private Handler h;
    private Resources r;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_view);

        h = new Handler();
        r = getResources();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Runnable task;
        Integer timeInMillis;

        task = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        };
        timeInMillis = r.getInteger(R.integer.tempo_splash);

        h.postDelayed(task, timeInMillis);
    }
}
