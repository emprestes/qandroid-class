package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.util.MensagemSQLiteHelper;

public class NovaMensagemActivity extends QuaddroActivity {

    private EditText etMensagem;
    private EditText etCorpo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mensagem_nova_view);

        etMensagem = getViewById(R.id.mensagem_titulo);
        etCorpo = getViewById(R.id.mensagem_corpo);
    }

    public void limpar(View view) {
        etMensagem.setText(null);
        etMensagem.requestFocus();
        etCorpo.setText(null);
    }

    public void salvar(View view) {
        Toast t;
        try {
            MensagemSQLiteHelper.getInstance(this)
                    .inserir(etMensagem.getText(), etCorpo.getText());

            t = Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT);

            finish();
        } catch (Exception cause) {
            t = Toast.makeText(this, "Ops...", Toast.LENGTH_SHORT);
            Log.e("SQLite", "Problemas ao salvar!", cause);
        }

        if (t != null) {
            t.show();
        }
    }
}
