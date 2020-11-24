package com.example.everydaycroquis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetails extends Fragment {
    int groupId = 0;
    String strKeyword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        groupId = getArguments().getInt("groupId"); // 전달한 key 값
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        setKeyword();
        setKeywordButton();
    }

    public void setKeyword(){
        strKeyword = "#남자 #여자 #어른 #아이 #노인 #옷입은 #나체 #달리는 #걷는 #서있는 #앉아있는 #누워있는";
    }
    public void setKeywordButton(){
        MainActivity mainActivity = ((MainActivity)getActivity());

        String str = strKeyword;
        String[] strArr = str.split(" ");

        FlowLayout layout = getView().findViewById(R.id.flow_layout);
        for (String s : strArr) {
            TextView view = new TextView(getView().getContext());
            view.setBackgroundResource(R.drawable.button_keyword);

            view.setText(s);
            view.setTextColor(Color.WHITE);
            view.setTextSize(15);

            view.setIncludeFontPadding(false);
            view.setGravity( Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL );

            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams( mainActivity.toPx(8), mainActivity.toPx(8));
            view.setLayoutParams(params);

            layout.addView(view);
        }
    }
}
