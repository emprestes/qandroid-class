package br.com.quaddro.emprestes.qandroid100.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.model.Oi;

public class OiActivity extends QuaddroActivity {

    private Oi model;
    private EditText etView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.oi_view);

        model = new Oi();
        etView = (EditText) findViewById(R.id.editText);
    }

    public void dizerOi(View v) {
        Toast toast;
        String message;

        message = etView.getText().toString();
        message = model.dizer(message);
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);

        toast.show();
    }
}
