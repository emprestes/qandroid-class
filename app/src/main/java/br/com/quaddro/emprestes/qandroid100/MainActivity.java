package br.com.quaddro.emprestes.qandroid100;

import android.os.Bundle;
import android.util.Log;

import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.controller.MegaSenaActivity;
import br.com.quaddro.emprestes.qandroid100.controller.OiActivity;

public class MainActivity extends QuaddroActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_view);

        Log.d("mqa", "MainActivity criada!");

        setOnClickListener(R.id.btn_aulas_01_04, OiActivity.class);
    }
}
