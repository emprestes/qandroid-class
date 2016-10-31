package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.view.MyFragment;

public class DrawerActivity extends ActionBarActivity {

    private DrawerLayout dl;
    private ListView lv;
    private FrameLayout fl;
    private ActionBarDrawerToggle toggle;
    private ActionBar ab;
    private MyArrayAdapter myaa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_view);

        dl = (DrawerLayout) findViewById(R.id.drawer);
        lv = (ListView) findViewById(android.R.id.list);
        fl = (FrameLayout) findViewById(R.id.drawer_content);
        ab = getSupportActionBar();

        myaa = new MyArrayAdapter(this,
                new MyAction(android.R.drawable.ic_menu_save, "Listar", R.drawable.hommer),
                new MyAction(android.R.drawable.ic_menu_add, "Adicionar", R.drawable.mascote),
                new MyAction(android.R.drawable.ic_menu_delete, "Apagar", R.drawable.quadrado));

        lv.setAdapter(myaa);

        toggle = new ActionBarDrawerToggle(this, dl, R.string.drawer_open, R.string.drawer_close);

        dl.setDrawerListener(toggle);

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lv.setOnItemClickListener(new MyOnItemClickListener());
    }

    private class MyAction {
        private int icon;
        private String label;
        private int img;

        MyAction(int icon, String label, int img) {
            super();

            this.icon = icon;
            this.label = label;
            this.img = img;
        }
    }

    private class MyArrayAdapter extends ArrayAdapter<MyAction> {

        private MyAction[] actions = {};

        public MyArrayAdapter(Context c, MyAction... actions) { // Vararg
            super(c, 0, actions);

            this.actions = actions;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater li = getLayoutInflater();

                convertView = li.inflate(R.layout.drawer_action, parent, false);
            }

            ImageView iv = (ImageView) convertView.findViewById(R.id.drawer_icon);
            TextView tv = (TextView) convertView.findViewById(R.id.drawer_label);

            iv.setImageResource(actions[position].icon);
            tv.setText(actions[position].label);

            return convertView;
        }
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        private MyFragment fragment = new MyFragment();

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            fragment.setImageResource(myaa.actions[position].img);
            String title;

            getSupportFragmentManager()
                .beginTransaction()
                        .replace(R.id.drawer_content, fragment)
                        .commit();

            lv.setItemChecked(position, true);
            title = myaa.actions[position].label;

            setTitle(title);
            dl.closeDrawer(lv);
        }
    }
}