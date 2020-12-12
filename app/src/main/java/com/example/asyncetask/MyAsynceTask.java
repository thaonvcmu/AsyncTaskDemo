package com.example.asyncetask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsynceTask extends AsyncTask<Void, Integer, Void> {

    TextView textView;
    Button button;
    //khai báo Activity để lưu trữ địa chỉ của MainActivity
    Activity contextCha;
    //constructor này được truyền vào là MainActivity
    public MyAsynceTask(Activity contextCha) {
        this.contextCha = contextCha;
        Log.i("TONAER","MyAsyncTask");
    }

    //hàm này sẽ được thực hiện đầu tiên
    @Override
    protected void onPreExecute() {
        ProgressBar progressBar=(ProgressBar) contextCha.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        // TODO Auto-generated method stub
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!",
                Toast.LENGTH_LONG).show();
    }

    //sau đó tới hàm doInBackground
    //tuyệt đối không được cập nhật giao diện trong hàm này
    @Override
    protected Void doInBackground(Void... arg0) {
        for(int i=0;i<=100;i++)
        {
            //nghỉ 100 milisecond thì tiến hành update UI
            SystemClock.sleep(100);
            //khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }

    /**
     * ta cập nhập giao diện trong hàm này
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
        //thông qua contextCha để lấy được control trong MainActivity
        //R.id.progressBar1: là pgbar kiểu ngang, nó sẽ chạy theo % của thanh
        ProgressBar progressBar2=(ProgressBar) contextCha
                .findViewById(R.id.progressBar1);
        //vì publishProgress chỉ truyền 1 đối số
        //nên mảng values chỉ có 1 phần tử
        int giatri=values[0];
        //tăng giá trị của Progressbar lên
        progressBar2.setProgress(giatri);
        //đồng thời hiện thị giá trị là % lên TextView
        TextView textView=(TextView)
                contextCha.findViewById(R.id.textView);
        textView.setText(giatri+"%");

    }

    /**
     * sau khi tiến trình thực hiện xong thì hàm này sảy ra
     */
    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        Toast.makeText(contextCha, " GG WP !",
                Toast.LENGTH_LONG).show();
        ProgressBar progressBar=(ProgressBar) contextCha.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);//dùng cái này để dừng cái pgbar hình tròn.
    }

    /**
     * tui làm 2 hàm ví dụ này về params trong Android
     */
    public void goividu()
    {
        viduParamschoham();
        viduParamschoham(5);
        viduParamschoham(5,6);
        viduParamschoham(5,6,5,6,7,8,9,0,0);
    }

    /**
     * dấu ... dùng khai báo param
     * tức là ta truyền bao nhiêu đối số cũng được
     * ds trở thành mảng 1 chiều
     * @param ds
     */
    public void viduParamschoham(int ... ds)
    {
        //test chơi...
        int pt0=ds[0];//có lỗi nếu như không truyền đối số nào
        for(int n:ds)
        {
            System.out.println(n);
        }
        //hoặc
        for(int i=0;i<ds.length;i++)
        {
            System.out.println(ds[i]);
        }
    }
}
