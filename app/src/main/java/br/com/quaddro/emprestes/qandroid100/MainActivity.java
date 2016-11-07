package br.com.quaddro.emprestes.qandroid100;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.controller.AnimacaoActivity;
import br.com.quaddro.emprestes.qandroid100.controller.CameraActivity;
import br.com.quaddro.emprestes.qandroid100.controller.ChatHeadActivity;
import br.com.quaddro.emprestes.qandroid100.controller.DrawerActivity;
import br.com.quaddro.emprestes.qandroid100.controller.FileCSVActivity;
import br.com.quaddro.emprestes.qandroid100.controller.MensagemService;
import br.com.quaddro.emprestes.qandroid100.controller.OiActivity;
import br.com.quaddro.emprestes.qandroid100.controller.SQLiteActivity;
import br.com.quaddro.emprestes.qandroid100.controller.SharedPreferenceActivity;
import br.com.quaddro.emprestes.qandroid100.controller.SpinnerActivity;
import br.com.quaddro.emprestes.qandroid100.controller.WebActivity;

public class MainActivity extends QuaddroActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_view);

        Log.d("mqa", "MainActivity criada!");

        setOnClickActivity(R.id.btn_aulas_01_04, OiActivity.class);
        setOnClickActivity(R.id.btn_aulas_05_11, AnimacaoActivity.class);
        setOnClickActivity(R.id.btn_aula_11, SpinnerActivity.class);
        setOnClickActivity(R.id.btn_aula_12c, CameraActivity.class);
        setOnClickActivity(R.id.btn_aula_12w, WebActivity.class);
        setOnClickActivity(R.id.btn_aula_13, DrawerActivity.class);
        setOnClickActivity(R.id.btn_aula_15, ChatHeadActivity.class);
        setOnClickActivity(R.id.btn_aula_16a, SharedPreferenceActivity.class);
        setOnClickActivity(R.id.btn_aula_16b, FileCSVActivity.class);
        setOnClickActivity(R.id.btn_aula_17, SQLiteActivity.class);
        setOnClickService(R.id.btn_aula_19, MensagemService.class);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Deseja ir?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();

        alert.show();
    }
}
