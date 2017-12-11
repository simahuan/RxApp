package com.rx.mhlistener.rxapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rx.mhlistener.rxapp.Rx.Observers.ProgressObserver;
import com.rx.mhlistener.rxapp.Rx.RxHttp;
import com.rx.mhlistener.rxapp.Rx.RxSchedulers;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//这边是随便填写的网络请求url
		RxHttp.getInstance().getSyncServer().getLatestVersion("1", "1.0.0")
				.compose(RxSchedulers.observableIO2Main(this))
				.subscribe(new ProgressObserver<String>(this) {
					@Override
					public void onSuccess(String result) {
						Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(Throwable e, String errorMsg) {
						Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
					}
				});
	}
}
