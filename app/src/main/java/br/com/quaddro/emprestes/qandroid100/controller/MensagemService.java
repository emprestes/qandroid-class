package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import br.com.quaddro.emprestes.qandroid100.R;

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

        Context c =  getApplicationContext();
        PendingIntent pi = PendingIntent.getActivity(c,
                500,
                new Intent(c, SQLiteActivity.class),
                PendingIntent.FLAG_ONE_SHOT);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification n = new Notification.Builder(c)
                .setContentTitle(c.getResources().getString(R.string.mensagem_criar))
                .setContentIntent(pi)
                .setAutoCancel(Boolean.TRUE)
                .setVibrate(new long[] {1000, 500, 1000, 500})
                .setSmallIcon(R.mipmap.quaddro)
                .build();

        nm.notify(125, n);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("mservice", "onDestroy");
    }
}
