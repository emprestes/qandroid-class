package br.com.quaddro.emprestes.qandroid100.controller;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class PlayerActivity extends QuaddroActivity {

    private ImageButton ibPlay;
    private ListView lv;
    private SeekBar sb;
    private Handler h;
    private ContentResolver cr;
    private Cursor c;
    private Uri u;
    private MediaPlayer mp;
    private int pos;
    private boolean completed = false;

    private Runnable updateView = new Runnable() {
        @Override
        public void run() {
            if (mp != null && mp.isPlaying() && !completed) {
                sb.setProgress(mp.getCurrentPosition());
                h.postDelayed(this, 300);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_view);

        ibPlay = getViewById(R.id.mediaPlay);
        sb = getViewById(R.id.seekBar);
        lv = getViewById(android.R.id.list);
        cr = getContentResolver();
        u = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        pos = 0;
        h = new Handler();

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mp != null && fromUser) {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (c.moveToPosition(position)) {
                    pos = position;

                    if (mp != null && mp.isPlaying()) {
                        mp.stop();
                        mp.reset();
                        mp.release();
                    }

                    int index = c.getColumnIndex(MediaStore.Audio.Media.DATA);
                    String s = c.getString(index);

                    mp = MediaPlayer.create(getBaseContext(), Uri.parse(s));
                    mp.setOnCompletionListener(onCompletionAction);

                    sb.setProgress(0);
                    sb.setMax(mp.getDuration());

                    onClickPlay(view);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int f = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (f != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }

        c = cr.query(u, null, null, null, "album ASC, title ASC");

        ListAdapter a = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                c,
                new String[] {
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ARTIST
                },
                new int[] {
                        android.R.id.text1,
                        android.R.id.text2
                },
                0);

        lv.setAdapter(a);

        if (c.moveToFirst()) {
            int index = c.getColumnIndex(MediaStore.Audio.Media.DATA);
            String s = c.getString(index);

            mp = MediaPlayer.create(getBaseContext(), Uri.parse(s));
            mp.setOnCompletionListener(onCompletionAction);
            sb.setProgress(0);
            sb.setMax(mp.getDuration());
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionAction = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (c.moveToPosition(++pos)) {
                int index = c.getColumnIndex(MediaStore.Audio.Media.DATA);
                String s = c.getString(index);

                mp = MediaPlayer.create(getBaseContext(), Uri.parse(s));
                mp.setOnCompletionListener(this);

                onClickPlay(null);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }
    }

    public void onClickPrevious(View view) {
        pos = --pos < 0 ? 0 : pos;

        if (c.moveToPosition(pos)) {
            if (mp != null && mp.isPlaying()) {
                mp.stop();
                mp.reset();
                mp.release();
            }

            int index = c.getColumnIndex(MediaStore.Audio.Media.DATA);
            String s = c.getString(index);

            mp = MediaPlayer.create(getBaseContext(), Uri.parse(s));
            mp.setOnCompletionListener(onCompletionAction);

            sb.setProgress(0);
            sb.setMax(mp.getDuration());

            onClickPlay(view);
        }
    }

    public void onClickPlay(View view) {
        if (mp != null) {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

            if (mp.isPlaying()) {
                mp.pause();
                ibPlay.setImageResource(android.R.drawable.ic_media_play);
            } else {
                mp.start();
                completed = false;
                ibPlay.setImageResource(android.R.drawable.ic_media_pause);
                lv.smoothScrollToPositionFromTop(pos, 0);
                h.postDelayed(updateView, 100);
            }
        }
    }

    public void onClickNext(View view) {
        if (c.moveToPosition(++pos)) {
            if (mp != null && mp.isPlaying()) {
                mp.stop();
                mp.reset();
                mp.release();
            }

            int index = c.getColumnIndex(MediaStore.Audio.Media.DATA);
            String s = c.getString(index);

            mp = MediaPlayer.create(getBaseContext(), Uri.parse(s));
            mp.setOnCompletionListener(onCompletionAction);

            sb.setProgress(0);
            sb.setMax(mp.getDuration());

            onClickPlay(view);
        } else if (pos > c.getCount()){
            --pos;
        }
    }
}
