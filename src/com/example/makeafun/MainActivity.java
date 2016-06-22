package com.example.makeafun;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements android.view.View.OnClickListener{
	private String TAG = "MainActivity";
	private Button login,register;
	private CheckBox remember;
	private EditText accounts,password; 
	private Intent intent;
	private ImageView image;
	private Bundle bd;
	private TextView app;
	private LinearLayout loginly;
	private Drawable drawable = null;  
    private URL url; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ȡ������   
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    
    @SuppressLint("NewApi")
	private void initView(){
    	
    	//�Ե�¼ģ��ĺ�����ͼƬ��drawableתbitmap���޸�ͼƬ����ΪԲ�ǣ���ת��drawable��ʾΪģ�鱳��
    	InputStream is = getResources().openRawResource(R.drawable.login_input);    
        Bitmap mBitmap = BitmapFactory.decodeStream(is); 
        Drawable drawable = new BitmapDrawable(getResources(), toRoundCorner(mBitmap,10));  
        
    	accounts = (EditText)findViewById(R.id.accounts);
    	password = (EditText)findViewById(R.id.password);
    	app = (TextView)findViewById(R.id.app);
    	loginly = (LinearLayout)findViewById(R.id.input);
    	loginly.setBackground(drawable);
    	//image = (ImageView)findViewById(R.id.image);
    	login = (Button)findViewById(R.id.login);                  //��¼��ť
    	remember = (CheckBox)findViewById(R.id.auto_save_password);  //��ס���밴ť
    	register = (Button)findViewById(R.id.regist);  //ע�ᰴť
    	app.setText("��������");
    	
    	//String source = "��ֻ��һ�����ԣ�����<u>wocaole</u>��<i>б����</i>��<font color='red'>��ɫ��</font>�ĸ�ʽ";   
    	//app.setText(Html.fromHtml(source));
    	
    	 /*String html="<html><head><title>TextViewʹ��HTML</title></head><body><p><strong>ǿ��</strong></p><p><em>б��</em></p>"  
                 +"<p><a href=\"http://www.dreamdu.com/xhtml/\">������HTML����</a>ѧϰHTML!</p><p><font color=\"#aabb00\">��ɫ1"  
                 +"</p><p><font color=\"#00bbaa\">��ɫ2</p><h1>����1</h1><h3>����2</h3><h6>����3</h6><p>����>С��<</p><p>" +  
                 "����������ͼƬ</p><img src=\"http://pic33.nipic.com/20130927/12045420_085444259113_2.png\"/></body></html>";*/  
    	String html="<html><head><title>Happy Travel</title></head><body><p><strong>����</strong></p></body></html>";
           
    	 //����һ�������ɹ�ʵ����ͼƬ�Ļ�ȡ����ʾ
    	 app.setMovementMethod(LinkMovementMethod.getInstance());//��������������ĳ�������Ч
    	 URLImageGetter ReviewImgGetter = new URLImageGetter(MainActivity.this, app);//ʵ����URLImageGetter��
    	 app.setText(Html.fromHtml(html,ReviewImgGetter,null));
    	 /*//�����������ʹ���Ǻõģ�����������Ͳ�����
    	  * String html = "<h1>this is h1</h1>"
 				+ "<p>This text is normal</p>"
 				+ "<img src='http://pic33.nipic.com/20130927/12045420_085444259113_2.png' />";
    	 Spanned sp = Html.fromHtml(html, new Html.ImageGetter() {
				@Override
				public Drawable getDrawable(String source) {
					InputStream is = null;
					try {
						is = (InputStream) new URL(source).getContent();
						Drawable d = Drawable.createFromStream(is, "src");
						d.setBounds(0, 0, d.getIntrinsicWidth(),
								d.getIntrinsicHeight());
						is.close();
						return d;
					} catch (Exception e) {
						return null;
					}
				}
			}, null);
    		app.setText(sp);        */   
    	 
    	 
    	 
    	 String fontPath = "fonts/huawenxinkai.ttf";

    	 Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
    	 app.setTypeface(tf);

    }
    
    private void initEvent(){
    	login.setOnClickListener(this);
    	remember.setOnClickListener(this);
    	register.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		// TODO �Զ����ɵķ������
		switch (arg0.getId()){
		case R.id.login :
			String ac = accounts.getText().toString();
			String pa = password.getText().toString();
			bd = new Bundle();
			if(!ac.equals("")&&!pa.equals("")){
				Log.e("�˺�����ֱ��ǣ�", ac+"---"+pa);
				Log.e(TAG, "�����˵�¼��ť");
				bd.putString("account", ac);
				bd.putString("password", pa);
				intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtras(bd);
				startActivity(intent);
				//finish();
			}else{
				Toast.makeText(MainActivity.this, "�˺������벻��Ϊ��", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.auto_save_password :
			Log.e(TAG, "�����˱��水ť");
			break;
		case R.id.regist :
			Log.e(TAG, "������ע�ᰴť");
			break;
		}
 	}
	
	ImageGetter imgGetter = new Html.ImageGetter() {  
       /* public Drawable getDrawable(String source) {  
              Drawable drawable = null;  
              URL url;    
              try {     
                  url = new URL(source);    
                  drawable = Drawable.createFromStream(url.openStream(), "");  //��ȡ��·ͼƬ  
              } catch (Exception e) {    
                  return null;    
              }    
              drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable  
                            .getIntrinsicHeight());  
              return drawable;   
        }  */
		public Drawable getDrawable(final String source) {


			new Thread(new Runnable() {

			@Override
			public void run() {
			// TODO Auto-generated method stub
			System.out.println("======");
			//String sourceurl = "http://avatar.csdn.net/0/3/8/2_zhang957411207.jpg";
			try {
			url = new URL(source);
			drawable = Drawable.createFromStream(url.openStream(), "");

			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
			drawable.getIntrinsicHeight());
			}
			}).start();

			return drawable;
			};
	};  
	
//��ͼƬ������Բ��
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {  
 
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);  
 
        Canvas canvas = new Canvas(output);  
 
        final int color = 0xff424242;  
 
        final Paint paint = new Paint();  
 
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
 
        final RectF rectF = new RectF(rect);  
 
        final float roundPx = pixels;  
 
        paint.setAntiAlias(true);  
 
        canvas.drawARGB(0, 0, 0, 0);  
 
        paint.setColor(color);  
 
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);  
 
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
 
        canvas.drawBitmap(bitmap, rect, rect, paint); 
        return output;  
 
    }
	
}
