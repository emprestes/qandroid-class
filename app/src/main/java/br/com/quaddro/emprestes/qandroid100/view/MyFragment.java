package br.com.quaddro.emprestes.qandroid100.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {

    private int id;

    public MyFragment setImageResource(int id) {
        this.id = id;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView iv = new ImageView(getActivity());

        iv.setImageResource(id);
        iv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        return iv;
    }
}
