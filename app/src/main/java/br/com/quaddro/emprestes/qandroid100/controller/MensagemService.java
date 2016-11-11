package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.model.Mensagem;
import br.com.quaddro.emprestes.qandroid100.retrofit.MensagemWS;
import br.com.quaddro.emprestes.qandroid100.util.MensagemRetrofitHelper;
import br.com.quaddro.emprestes.qandroid100.util.MensagemSQLiteHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MensagemService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("mservice", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("mservice", "onStartCommand");

        // Retrofit
        MensagemRetrofitHelper helper;
        MensagemWS ws;
        Call<List<Mensagem>> call;
        final Context c;

        c = getApplicationContext();
        helper = MensagemRetrofitHelper.getInstance();
        ws = helper.getWs();
        call = ws.listarTodas();

        call.enqueue(new Callback<List<Mensagem>>() {
            @Override
            public void onResponse(Call<List<Mensagem>> call, Response<List<Mensagem>> response) {
                MensagemSQLiteHelper helper;
                Iterator<Mensagem> i;
                List<Mensagem> list;

                helper = MensagemSQLiteHelper.getInstance(c);
                list = response.body();
                i = list.iterator();

                while (i.hasNext()) {
                    helper.inserir(i.next());
                }

                Log.i("RETROFIT", "ENCONTRADOS: " + list.size());

                // Notification
                if (!list.isEmpty()) {
                    PendingIntent pi = PendingIntent.getActivity(c,
                            500,
                            new Intent(c, SQLiteActivity.class),
                            PendingIntent.FLAG_ONE_SHOT);
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification n = new Notification.Builder(c)
                            .setContentTitle(c.getResources().getString(R.string.mensagem_criar))
                            .setContentIntent(pi)
                            .setAutoCancel(Boolean.TRUE)
                            .setVibrate(new long[]{1000, 500, 1000, 500})
                            .setSmallIcon(R.mipmap.quaddro)
                            .build();

                    nm.notify(125, n);
                }
            }

            @Override
            public void onFailure(Call<List<Mensagem>> call, Throwable t) {
                Log.e("RETROFIT", "PROBLEMAS", t);
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("mservice", "onDestroy");
    }
}
