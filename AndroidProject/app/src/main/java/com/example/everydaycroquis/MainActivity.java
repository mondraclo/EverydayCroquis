package com.example.everydaycroquis;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_top = (FrameLayout)findViewById(R.id.fl_top);
        ll_mainwindow = (LinearLayout)findViewById(R.id.ll_mainwindow);
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