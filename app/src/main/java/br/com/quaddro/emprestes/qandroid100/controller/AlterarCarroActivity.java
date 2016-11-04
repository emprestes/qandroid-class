package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.exception.CarroEqualException;
import br.com.quaddro.emprestes.qandroid100.util.CarroCSVHelper;

public class AlterarCarroActivity extends QuaddroActivity {

    private EditText nomeView;
    private EditText fabricanteView;

    private String[] campos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carro_alterar_view);

        nomeView = getViewById(R.id.carro_nome);
        fabricanteView = getViewById(R.id.carro_fabricante);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent i = getIntent();
        CharSequence c = i.getCharSequenceExtra("campos");
        campos = c.toString().split(" / ");

        nomeView.setText(campos[0]);
        fabricanteView.setText(campos[1]);
    }

    public void salvar(View view) {
        try {
            CarroCSVHelper.getInstance(this).alterar(campos[0], campos[1],
                    nomeView.getText(), fabricanteView.getText(), "carros.csv");

            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT)
                    .show();

            finish();
        } catch (CarroEqualException cause) {
            Toast.makeText(this, "Carros iguais!\nNão salvo!", Toast.LENGTH_SHORT)
                    .show();
            Log.d("carros.csv", "CARROS IGUAIS", cause);
        } catch (IOException cause) {
            Log.d("carros.csv", "PROBLEMAS AO SALVAR", cause);
        }
    }
}
