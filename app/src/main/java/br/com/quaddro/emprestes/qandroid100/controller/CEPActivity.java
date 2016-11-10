package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class CEPActivity extends QuaddroActivity {

    private TextView endereco;
    private EditText cep;

    private BroadcastReceiver enderecoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String e, m;

            e = intent.getStringExtra("endereco");

            if (e.isEmpty()) {
                m = String.format("%s :(", cep.getText());
            } else {
                endereco.setText(e);
                m = String.format("%s :D", cep.getText());
            }

            Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cep_view);

        endereco = getViewById(R.id.cep_endereco);
        cep = getViewById(R.id.cep);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(enderecoReceiver, new IntentFilter(CEPService.ACTION));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(enderecoReceiver);
        super.onPause();
    }

    public void buscar(View view) {
        Intent i = new Intent(this, CEPService.class);

        i.putExtra("cep", cep.getText());

        startService(i);
    }
}
