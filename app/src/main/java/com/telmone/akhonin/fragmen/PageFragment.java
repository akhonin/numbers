package com.telmone.akhonin.fragmen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.telmone.akhonin.R;
import com.telmone.akhonin.model.NumberResponse;

public class PageFragment extends Fragment {

    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        mTextView  = view.findViewById(R.id.number_info);
        return view;
    }

    public void setNumberInfo(NumberResponse data){
        mTextView.setText(data.getText());
    }
}
