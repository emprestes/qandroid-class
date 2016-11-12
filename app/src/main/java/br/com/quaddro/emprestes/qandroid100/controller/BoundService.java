package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

public class BoundService extends Service {

    private Chronometer c;
    private MeuBinder b;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("BoundService", "onCreate");

        b = new MeuBinder(this);
        c = new Chronometer(this);
        c.setBase(SystemClock.elapsedRealtime());
        c.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("BoundService", "onBind");
        return b;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v("BoundService", "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("BoundService", "onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("BoundService", "onDestroy");
        c.stop();
    }

    public String now() {
        long now = SystemClock.elapsedRealtime();
        int h, m, s, ms;

        h  = (int) (now / 3600000);
        m  = (int) (now - h * 3600000) / 60000;
        s  = (int) (now - h * 3600000 - m * 60000) / 1000;
        ms = (int) (now - h * 3600000 - m * 60000 - s * 1000);

        return String.format("%s:%s:%s:%s", h, m, s, ms);
    }
}

class MeuBinder extends Binder {

    private BoundService service;

    MeuBinder(BoundService service) {
        this.service = service;
    }

    public BoundService getService() {
        return service;
    }
}
