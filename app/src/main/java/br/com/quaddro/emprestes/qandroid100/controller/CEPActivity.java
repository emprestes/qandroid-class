package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class CEPActivity extends QuaddroActivity {

    private TextView endereco;
    private EditText cep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cep_view);

        endereco = getViewById(R.id.cep_endereco);
        cep = getViewById(R.id.cep);
    }

    public void buscar(View view) {
        String endereco;
        URL url;
        URLConnection connection;

        try {
            url = new URL(String.format("http://api.postmon.com.br/v1/cep/%s", cep.getText()));
            connection = url.openConnection();

            try (InputStream in = connection.getInputStream();
                 BufferedReader buffer = new BufferedReader(new InputStreamReader(in))) {
                JSONObject json = new JSONObject(buffer.readLine());

                endereco = String.format("%s, %s, %s, %s",
                        json.getString("logradouro"),
                        json.getString("cep"),
                        json.getString("bairro"),
                        json.getString("cidade"));

                this.endereco.setText(endereco);
            }
        } catch (Exception cause) {
            Log.e("CEP", "Problemas", cause);
        }
    }
}
