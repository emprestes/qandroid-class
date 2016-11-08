package br.com.quaddro.emprestes.qandroid100.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import br.com.quaddro.emprestes.qandroid100.R;

public class BolaView extends SurfaceView {

    private Bitmap bola;
    private Bitmap campo;

    private Resources res;

    public BolaView(Context context) {
        super(context);

        init();
    }

    public BolaView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        res = getResources();
        bola = BitmapFactory.decodeResource(res, R.mipmap.bola);
        campo = BitmapFactory.decodeResource(res, R.mipmap.campo);

        setKeepScreenOn(Boolean.TRUE);
    }

    public float getBolaWidth() { return bola.getWidth(); }

    public float getBolaHeight() { return bola.getHeight(); }

    public void move(float x, float y) {
        Canvas c = null;
        SurfaceHolder sh = null;

        try {
            sh = getHolder();
            c  = sh.lockCanvas();

            if (c != null) {
                Rect r = new Rect(0, 0, c.getWidth(), c.getHeight());
                c.drawBitmap(campo, null, r, null);
                c.drawBitmap(bola, x, y, null);
            }
        } finally {
            if (sh != null && c != null) {
                sh.unlockCanvasAndPost(c);
            }
        }
    }

    public void addHolderCallback(SurfaceHolder.Callback callback) {
        getHolder().addCallback(callback);
    }
}
