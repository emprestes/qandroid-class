package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.MainActivity;
import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class LoginActivity extends QuaddroActivity {

    private EditText etUser;
    private EditText etPassword;
    private Switch swRemember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_view);

        etUser = getViewById(R.id.et_user);
        etPassword = getViewById(R.id.et_password);
        swRemember = getViewById(R.id.sw_remember);
    }

    public void acessar(View view) {
        CharSequence user, password;

        user = etUser.getText();
        password = etPassword.getText();

        if ("quaddro".equals(user.toString()) && "123".equals(password.toString())) {
            // FIXME Salvar o lembrar senha!!
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Verificar acesso!", Toast.LENGTH_LONG).show();
        }
    }
}
