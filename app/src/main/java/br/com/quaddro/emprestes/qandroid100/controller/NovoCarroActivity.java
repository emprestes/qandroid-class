package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.util.CarroCSVHelper;

public class NovoCarroActivity extends QuaddroActivity {

    private EditText nomeView;
    private EditText fabricanteView;
    private EditText descricaoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carro_novo_view);

        nomeView = getViewById(R.id.carro_nome);
        fabricanteView = getViewById(R.id.carro_fabricante);
        descricaoView = getViewById(R.id.carro_descricao);
    }

    public void limpar(View view) {
        nomeView.setText(null);
        nomeView.requestFocus();
        fabricanteView.setText(null);
        descricaoView.setText(null);
    }

    public void salvar(View view) {
        try {
            CarroCSVHelper.getInstance(this)
                    .inserir(nomeView.getText(),
                            fabricanteView.getText(),
                            descricaoView.getText(), "carros.csv");

            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT)
                    .show();

            finish();
        } catch (IOException cause) {
            Log.d("carros.csv", "PROBLEMAS AO SALVAR", cause);
        }
    }
}
