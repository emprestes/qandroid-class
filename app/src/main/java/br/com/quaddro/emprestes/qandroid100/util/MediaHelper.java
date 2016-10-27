package br.com.quaddro.emprestes.qandroid100.util;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class MediaHelper {

    public static Uri getOutputMediaVideoFileUri() {
        File mf, path, dir;

        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        dir = new File(path, "quaddro");

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("CAMERA", "PROBLEMAS AO CRIAR PASTA NO SDCARD!");
                return null;
            }
        }

        String nome = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        mf = new File(String.format("%s%sVID_%s.mp4", dir.getPath(), File.separator, nome));

        return Uri.fromFile(mf);
    }
}
