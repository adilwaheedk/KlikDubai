package com.khanstech.shop.klikdubai.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.utils.FileHelper;

public class ReturnPolicyFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_return_policy, container, false);
        final MainActivity mainActivity = (MainActivity) getActivity();

        TextView textView = (TextView) rootView.findViewById(R.id.tv_value);

        Spanned text;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            text = Html.fromHtml(FileHelper.readAssetFile(mainActivity,
                    mainActivity.getString(R.string.file_return_policy)), Html.FROM_HTML_MODE_LEGACY);
        } else {
            text = Html.fromHtml(FileHelper.readAssetFile(mainActivity,
                    mainActivity.getString(R.string.file_return_policy)));
        }

        textView.setText(text);
        return rootView;
    }
}
