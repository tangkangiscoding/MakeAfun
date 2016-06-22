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
        //取消标题   
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    
    @SuppressLint("NewApi")
	private void initView(){
    	
    	//对登录模块的海贼王图片有drawable转bitmap，修改图片四周为圆角，再转回drawable显示为模块背景
    	InputStream is = getResources().openRawResource(R.drawable.login_input);    
        Bitmap mBitmap = BitmapFactory.decodeStream(is); 
        Drawable drawable = new BitmapDrawable(getResources(), toRoundCorner(mBitmap,10));  
        
    	accounts = (EditText)findViewById(R.id.accounts);
    	password = (EditText)findViewById(R.id.password);
    	app = (TextView)findViewById(R.id.app);
    	loginly = (LinearLayout)findViewById(R.id.input);
    	loginly.setBackground(drawable);
    	//image = (ImageView)findViewById(R.id.image);
    	login = (Button)findViewById(R.id.login);                  //登录按钮
    	remember = (CheckBox)findViewById(R.id.auto_save_password);  //记住密码按钮
    	register = (Button)findViewById(R.id.regist);  //注册按钮
    	app.setText("乐游天下");
    	
    	//String source = "这只是一个测试，测试<u>wocaole</u>、<i>斜体字</i>、<font color='red'>红色字</font>的格式";   
    	//app.setText(Html.fromHtml(source));
    	
    	 /*String html="<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"  
                 +"<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1"  
                 +"</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>" +  
                 "下面是网络图片</p><img src=\"http://pic33.nipic.com/20130927/12045420_085444259113_2.png\"/></body></html>";*/  
    	String html="<html><head><title>Happy Travel</title></head><body><p><strong>乐游</strong></p></body></html>";
           
    	 //方法一本方法成功实现了图片的获取与显示
    	 app.setMovementMethod(LinkMovementMethod.getInstance());//加这句才能让里面的超链接生效
    	 URLImageGetter ReviewImgGetter = new URLImageGetter(MainActivity.this, app);//实例化URLImageGetter类
    	 app.setText(Html.fromHtml(html,ReviewImgGetter,null));
    	 /*//这个方法单独使用是好的，但是在这里就不行了
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
		// TODO 自动生成的方法存根
		switch (arg0.getId()){
		case R.id.login :
			String ac = accounts.getText().toString();
			String pa = password.getText().toString();
			bd = new Bundle();
			if(!ac.equals("")&&!pa.equals("")){
				Log.e("账号密码分别是：", ac+"---"+pa);
				Log.e(TAG, "你点击了登录按钮");
				bd.putString("account", ac);
				bd.putString("password", pa);
				intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtras(bd);
				startActivity(intent);
				//finish();
			}else{
				Toast.makeText(MainActivity.this, "账号与密码不能为空", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.auto_save_password :
			Log.e(TAG, "你点击了保存按钮");
			break;
		case R.id.regist :
			Log.e(TAG, "你点击了注册按钮");
			break;
		}
 	}
	
	ImageGetter imgGetter = new Html.ImageGetter() {  
       /* public Drawable getDrawable(String source) {  
              Drawable drawable = null;  
              URL url;    
              try {     
                  url = new URL(source);    
                  drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片  
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
	
//将图片制作成圆角
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
