package br.com.quaddro.emprestes.qandroid100.so;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import br.com.quaddro.emprestes.qandroid100.controller.JogoBolaActivity;

public class JogoBolaThread extends Thread {

    private boolean isRunning = true;

    private JogoBolaActivity controller;

    public JogoBolaThread(JogoBolaActivity controller) {
        super("JogoBola Thread");

        this.controller = controller;
    }

    @Override
    public void interrupt() {
        isRunning = false;
        super.interrupt();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                controller.mover(System.currentTimeMillis());
                TimeUnit.MICROSECONDS.sleep(16);
            } catch (InterruptedException cause) {
                Log.e(getName(), "PROBLEMAS COM A THREAD", cause);
                isRunning = false;
            }
        }
    }
}
