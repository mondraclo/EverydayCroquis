package com.example.everydaycroquis;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FrameLayout fl_top;
    LinearLayout ll_mainwindow, ll_start;

    Button btn_start;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentRunning fragmentRunning;

    public int toPx(int dp){
        int pxValue = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
        return pxValue;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_top = (FrameLayout)findViewById(R.id.fl_top);
        ll_mainwindow = (LinearLayout)findViewById(R.id.ll_mainwindow);
        for(int i=0; i<5; i++){
            Button btn = new Button(this);
            btn.setBackgroundResource(R.drawable.button_group);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_mainwindow.getLayoutParams();

            int margin_px = toPx(10);
            params.setMargins(margin_px, margin_px, margin_px, margin_px);
            btn.setLayoutParams(params);
            btn.setText("" + (i + 1));


            ll_mainwindow.addView(btn);
        }

        ll_start = (LinearLayout)findViewById(R.id.ll_start);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(onClickListener);

        fragmentManager = getSupportFragmentManager();
        fragmentRunning = new FragmentRunning();

    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_start:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                    );
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.fl_top, fragmentRunning);
                    fragmentTransaction.commitAllowingStateLoss();
                    break;
            }
        }
    };
}