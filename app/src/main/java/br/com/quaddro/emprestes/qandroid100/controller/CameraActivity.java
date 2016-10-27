package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;
import br.com.quaddro.emprestes.qandroid100.util.MediaHelper;

public class CameraActivity extends QuaddroActivity {

    private Button btImagem;
    private Button btVideo;
    private FrameLayout flCamera;

    private View.OnClickListener imagemAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(i, 100);
        }
    };

    public void gravarVideo(View view) {
        Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        Uri uri = MediaHelper.getOutputMediaVideoFileUri();

        i.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(i, 200);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.camera_view);

        btImagem = getViewById(R.id.imagem);
        btVideo = getViewById(R.id.video);
        flCamera = getViewById(R.id.camera);

        btImagem.setOnClickListener(imagemAction);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String message = null;
        flCamera.removeAllViews();

        switch (requestCode) {
            case 100: // Imagem
                switch (resultCode) {
                    case RESULT_OK:
                        Bitmap b = (Bitmap) data.getExtras().get("data");
                        ImageView iv = new ImageView(this);

                        iv.setImageBitmap(b);

                        btImagem.setBackgroundColor(Color.GREEN);
                        btVideo.setBackgroundColor(Color.LTGRAY);
                        flCamera.addView(iv);
                        break;
                    case RESULT_CANCELED:
                        message = getResources().getString(R.string.imagem_cancelada);
                        break;
                }
                break;
            case 200: // Câmera
                switch (resultCode) {
                    case RESULT_OK:
                        VideoView vv = new VideoView(this);
                        MediaController mc = new MediaController(this);
                        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        Uri uri = data.getData();

                        vv.setLayoutParams(lp);
                        vv.setMediaController(mc);
                        vv.setVideoURI(uri);

                        flCamera.addView(vv);
                        btImagem.setBackgroundColor(Color.LTGRAY);
                        btVideo.setBackgroundColor(Color.GREEN);
                        break;
                    case RESULT_CANCELED:
                        message = getResources().getString(R.string.video_cancelado);
                        break;
                }
                break;
        }

        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
