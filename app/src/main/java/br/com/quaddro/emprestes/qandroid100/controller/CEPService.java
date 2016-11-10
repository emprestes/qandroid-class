package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CEPService extends IntentService {

    public static final String ACTION = "quaddro.intent.CEP";

    public CEPService() {
        super("CEPService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        CharSequence cep;

        cep = intent.getCharSequenceExtra("cep");

        buscar(cep);
    }

    private void buscar(CharSequence cep) {
        String endereco;
        URL url;
        URLConnection connection;

        try {
            url = new URL(String.format("http://api.postmon.com.br/v1/cep/%s", cep));
            connection = url.openConnection();

            try (InputStream in = connection.getInputStream();
                 BufferedReader buffer = new BufferedReader(new InputStreamReader(in))) {
                JSONObject json = new JSONObject(buffer.readLine());

                endereco = String.format("%s, %s, %s, %s",
                        json.getString("logradouro"),
                        json.getString("cep"),
                        json.getString("bairro"),
                        json.getString("cidade"));

                Intent i = new Intent(ACTION);
                i.putExtra("endereco", endereco);

                sendBroadcast(i);

                Log.i("CEP", endereco);
            }
        } catch (Exception cause) {
            Log.e("CEP", "Problemas", cause);
        }
    }
}
