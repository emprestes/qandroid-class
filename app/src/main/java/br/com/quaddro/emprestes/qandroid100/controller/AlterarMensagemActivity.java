package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.repository.Entity;
import br.com.quaddro.emprestes.qandroid100.util.MensagemSQLiteHelper;

public class AlterarMensagemActivity extends QuaddroActivity {

    private Long id;
    private EditText etMensagem;
    private EditText etCorpo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mensagem_alterar_view);

        etMensagem = getViewById(R.id.mensagem_titulo);
        etCorpo = getViewById(R.id.mensagem_corpo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent i = getIntent();

        id = i.getLongExtra("id", 0);
        Cursor c = MensagemSQLiteHelper.getInstance(this).recuperar(id);

        if (c.moveToFirst()) {
            etMensagem.setText(c.getString(c.getColumnIndex(Entity.Mensagem.Columns.TITULO)));
            etCorpo.setText(c.getString(c.getColumnIndex(Entity.Mensagem.Columns.CORPO)));
        }
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
                    .atualizar(id, etMensagem.getText(), etCorpo.getText());

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
