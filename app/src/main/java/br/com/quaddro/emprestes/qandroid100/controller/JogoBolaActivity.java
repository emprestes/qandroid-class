package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.model.Bola;
import br.com.quaddro.emprestes.qandroid100.so.JogoBolaThread;
import br.com.quaddro.emprestes.qandroid100.view.BolaView;

public class JogoBolaActivity extends QuaddroActivity {

    private SensorManager sm;
    private Vibrator v;
    private Thread t;
    private BolaView view;
    private Bola model;

    private SurfaceHolder.Callback callbackAction = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            t = new JogoBolaThread(JogoBolaActivity.this);
            t.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            model.setTela(width, height);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            model.parar();
            t.interrupt();
        }
    };

    private SensorEventListener sensorEventAction = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] pos = event.values;
            float x = pos[0];
            float y = pos[1];

            model.setAceleracao(x, y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jogo_bola_view);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        v  = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        view = getViewById(R.id.bola_view);
        model = new Bola(view.getWidth(), view.getHeight());

        model.setTamanho(view.getBolaWidth(), view.getBolaHeight());

        view.addHolderCallback(callbackAction);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Sensor s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(sensorEventAction, s, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(sensorEventAction);

        super.onPause();
    }

    public void mover(long tempoInMillis) {
        model.posicionar(tempoInMillis);

        if (model.isToqueTela()) {
            if (model.isToqueUnicoTela()) {
                Log.d("JogoBola", model.toString());
                v.vibrate(100);

                if (model.isToqueTelaTopo() || model.isToqueTelaBase()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast t = Toast.makeText(getBaseContext(), "Escanteio", Toast.LENGTH_SHORT);

                            t.setMargin(model.getCenterX(), model.getCenterY());

                            t.show();
                        }
                    });
                } else if (model.isToqueTelaLadoEsquerdo() || model.isToqueTelaLadoDireito()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast t = Toast.makeText(getBaseContext(), "Lateral", Toast.LENGTH_SHORT);

                            t.setMargin(model.getCenterX(), model.getCenterY());

                            t.show();
                        }
                    });
                }
            }

            model.ajustarRebote();
        }

        view.move(model.getPosicaoX(), model.getPosicaoY());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add("Sensores");

        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(JogoBolaActivity.this, SensorActivity.class));
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
