package com.apps.idhamrahadian.hitsradioapps.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.idhamrahadian.hitsradioapps.R;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView tv = view.findViewById(R.id.aboutRadio);
        String formattedText = getString(R.string.htmlFormattedTextforAboutFrag);
        tv.setText(Html.fromHtml(formattedText));

        return view;
    }
}