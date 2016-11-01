package br.com.quaddro.emprestes.qandroid100.controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.flipkart.chatheads.ui.ChatHead;
import com.flipkart.chatheads.ui.ChatHeadContainer;
import com.flipkart.chatheads.ui.ChatHeadViewAdapter;
import com.flipkart.chatheads.ui.MinimizedArrangement;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.view.RicoFragment;


public class ChatHeadActivity extends ActionBarActivity {

    private ChatHeadContainer view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chat_head_view);

        view = (ChatHeadContainer) findViewById(R.id.chat_head_container);

        view.setViewAdapter(new ChatHeadViewAdapter() {
            @Override
            public FragmentManager getFragmentManager() {
                return getSupportFragmentManager();
            }

            @Override
            public Fragment instantiateFragment(Object key, ChatHead chatHead) {
                String k = (String) key;

                switch (k) {
                    case "ch001":
                    case "ch003":
                    case "ch005":
                    case "ch007":
                    case "ch009":
                        return new RicoFragment();
                    default:
                        return new Fragment();
                }
            }

            @Override
            public Drawable getChatHeadDrawable(Object key) {
                return getResources().getDrawable(R.drawable.hommer);
            }

            @Override
            public Drawable getPointerDrawable() {
                return getResources().getDrawable(R.drawable.circular);
            }

            @Override
            public View getTitleView(Object key, ChatHead chatHead) {
                TextView tv = new TextView(getApplicationContext());
                tv.setText(R.string.chat_head);
                return tv;
            }
        });

        view.addChatHead("ch001", false, true);
        view.addChatHead("ch003", false, true);
        view.addChatHead("ch004", false, true);
        view.addChatHead("ch005", false, true);
        view.addChatHead("ch006", false, true);
        view.addChatHead("ch007", false, true);
        view.addChatHead("ch008", false, true);
        view.addChatHead("ch009", false, true);
        view.addChatHead("ch002", false, true);

        view.setArrangement(MinimizedArrangement.class, null);
    }
}
