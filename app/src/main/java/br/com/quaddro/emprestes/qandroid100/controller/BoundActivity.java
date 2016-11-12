package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class BoundActivity extends QuaddroActivity {

    private Button btAgora, btPararServico;
    private TextView tvAgora;

    private BoundService service;
    private boolean isBound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bound_view);

        btAgora = getViewById(R.id.bound_agora);
        btPararServico = getViewById(R.id.bound_parar_servico);
        tvAgora = getViewById(R.id.bound_tv_agora);

        btAgora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound) {
                    tvAgora.setText(service.now());
                }
            }
        });

        btPararServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound) {
                    unbindService(myConnection);
                    isBound = false;
                }

                stopService(new Intent(BoundActivity.this, BoundService.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        startService(new Intent(this, BoundService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        bindService(new Intent(this, BoundService.class), myConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (isBound) {
            unbindService(myConnection);
            isBound = false;
        }
    }

    private ServiceConnection myConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MeuBinder b = (MeuBinder) service;

            BoundActivity.this.service = b.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            service = null;
        }
    };
}
