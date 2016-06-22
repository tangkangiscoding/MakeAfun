package com.example.makeafun;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailActivity extends Activity implements android.view.View.OnClickListener{
	private String TAG = "DetailActivity";
	private Button detail_btn1,detail_btn2,detail_btn3,detail_btn4,detail_btn5;
	private TextView titlebar_both_left_text,titlebar_both_center_title,titlebar_both_right_text,detail_tex1;
	private LinearLayout titlebar_both_left_layout;
	private Intent intent;
	private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ȡ������   
        requestWindowFeature(Window.FEATURE_NO_TITLE);   
       /* //ȡ��״̬��
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    
                WindowManager.LayoutParams.FLAG_FULLSCREEN);   */
        setContentView(R.layout.activity_detail);
        initData();
        initView();
        initEvent();
    }
    
    private void initData(){
    	intent = getIntent();
    	bundle = intent.getExtras();
    	Log.e("���¼���˺�����ֱ��ǣ�", bundle.getString("account")+"-"+bundle.getString("password"));
    }
    
    private void initView(){
    	titlebar_both_left_text = (TextView)findViewById(R.id.titlebar_both_left_text);
    	titlebar_both_center_title = (TextView)findViewById(R.id.titlebar_both_center_title);
    	//titlebar_both_right_text = (TextView)findViewById(R.id.titlebar_both_right_text);
    	titlebar_both_left_layout = (LinearLayout)findViewById(R.id.titlebar_both_left_layout);
    	detail_btn1 = (Button)findViewById(R.id.detail_btn1);
    	detail_btn2 = (Button)findViewById(R.id.detail_btn2);
    	detail_btn3 = (Button)findViewById(R.id.detail_btn3);
    	detail_btn4 = (Button)findViewById(R.id.detail_btn4);
    	detail_btn5 = (Button)findViewById(R.id.detail_btn5);
    	detail_tex1 = (TextView)findViewById(R.id.detail_tex1);
    	titlebar_both_center_title.setText("���η���");
    }
    
    private void initEvent(){
    	titlebar_both_left_layout.setOnClickListener(this);
    	detail_btn1.setOnClickListener(this);
    	detail_btn2.setOnClickListener(this);
    	detail_btn3.setOnClickListener(this);
    	detail_btn4.setOnClickListener(this);
    	detail_btn5.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		// TODO �Զ����ɵķ������
		Bundle b = new Bundle();
		switch (arg0.getId()){
		case R.id.titlebar_both_left_layout :
			Log.e(TAG, "�����˷��ذ�ť��");
			intent = new Intent(DetailActivity.this, MainActivity.class);
			//intent.putExtras(bundle);
			startActivity(intent);
			finish();
			break;
		/*case R.id.auto_save_password :
			Log.e(TAG, "�����˱��水ť");
			break;*/
		case R.id.detail_btn1 :
			Log.e(TAG, "�����˰�ťһ");
			intent = new Intent(DetailActivity.this, BaiduMapActivity.class);
			startActivity(intent);
			break;
		case R.id.detail_btn2 :
			Log.e(TAG, "�����˰�ť��");
			/*//String data = "https://123.sogou.com/?12242-0037";
			String data = "http://j.map.baidu.com/ZlP2B";
			Intent intent = new Intent();// ����Intent����
			intent.setAction(Intent.ACTION_VIEW);// ΪIntent���ö���
			intent.setData(Uri.parse(data));// ΪIntent��������
			startActivity(intent);// ��Intent���ݸ�Activity*/		
			
			intent = new Intent(DetailActivity.this, SightOfPeopleNum.class);
			b.putString("Code","2");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.detail_btn3 :
			Log.e(TAG, "�����˰�ť��");
			intent = new Intent(DetailActivity.this, SightOfPeopleNum.class);
			b.putString("Code","3");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.detail_btn4 :
			Log.e(TAG, "�����˰�ť��");
			intent = new Intent(DetailActivity.this, SightOfPeopleNum.class);
			b.putString("Code","4");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.detail_btn5 :
			Log.e(TAG, "�����˰�ť��");
			/*intent = new Intent(DetailActivity.this, SightOfPeopleNum.class);
			b.putString("Code","5");
			intent.putExtras(b);
			startActivity(intent);*/
			 Uri uri = Uri.parse("tel:8765110");

             Intent intent = new Intent(Intent.ACTION_DIAL, uri);

             startActivity(intent);    
			break;
		}
 	}
}