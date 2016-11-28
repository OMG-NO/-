package com.jredu.tk.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jredu.tk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GridOleExamFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_grid_ole_exam, container, false);

        return view;
    }

}
