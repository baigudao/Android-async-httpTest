package com.jackie.android_async_httptest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jackie.android_async_httptest.R;
import com.jackie.android_async_httptest.util.AsyncHttpHelper;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2016/12/20.
 */
public class MainActivity extends Activity {

    private Button button_get;
    private Button button_post;
    private Button button_file;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button_get = (Button) findViewById(R.id.btn1);
        button_post = (Button) findViewById(R.id.btn2);
        button_file = (Button) findViewById(R.id.btn3);
        textView = (TextView) findViewById(R.id.tv_response);

        button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpHelper.getClient().get("http://ifeng.com", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, org.apache.http.Header[] headers, String s, Throwable throwable) {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int i, org.apache.http.Header[] headers, String s) {
                        textView.setText(s);
                    }
                });
            }
        });

        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams();
                params.put("user", "admin");
                params.put("pwd", "123456");
                AsyncHttpHelper.getClient().post("http://xxx.com", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        //相关逻辑操作
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        //相关逻辑操作
                    }
                });
            }
        });

        button_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = null;
                try {
                    params = new RequestParams();
                    params.put("meg", "上传图片到服务器");
                    params.put("img", new File("/sdcard/xxx.png"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                AsyncHttpHelper.getClient().post("xxx", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        //返回的数据操作
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        //返回的数据操作
                    }
                });
            }
        });

    }
}
