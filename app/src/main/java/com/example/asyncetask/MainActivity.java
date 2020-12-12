package com.example.asyncetask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button button;
    MyAsynceTask mytt;
    public ProgressBar progressBar,progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar =(ProgressBar)this.findViewById(R.id.progressBar);
        progressBar1 =(ProgressBar)this.findViewById(R.id.progressBar1);
        stopProg();

        button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                doStart();
            }
        });
    }

    public void stopProg(){
        progressBar.setVisibility(View.GONE);
    }



    private void doStart()
    {
        //truyền this (chính là MainActivity hiện tại) qua Child Thread
        mytt =new MyAsynceTask(this);
        //Kích hoạt Tiến trình
        //khi gọi hàm này thì onPreExecute của mytt sẽ thực thi trước
        mytt.execute();
    }
}
