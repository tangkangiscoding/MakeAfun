package com.example.makeafun;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebFunActivity extends Activity {
	private String str;
	private WebView webView;//������ʾ��ͼ�İٶ���ҳ
	 @SuppressLint("JavascriptInterface")
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.webview);
	        BaiduMapActivity bb=new BaiduMapActivity();
	        webView = (WebView)this.findViewById(R.id.webView);
	        webView.getSettings().setJavaScriptEnabled(true);//����javaScript����
	        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
	       // webView.setWebViewClient(new MyWebViewClient());
		   //����һ��Activity��ȡ���ٶȵ�ͼ�������
	        Intent intent=getIntent();
	        Bundle bundle=intent.getExtras();
	        Double Longitude=bundle.getDouble("Longitude");
	        Double Latitude=bundle.getDouble("Latitude");	        
	        //����ҳ��ʾ����
	        webView.loadUrl("http://j.map.baidu.com/F4N2B");   
	 }
	 
	 final class MyWebViewClient extends WebViewClient {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			public void onPageStarted(WebView view, String url, Bitmap favicon) {
		//		view.getSettings().setJavaScriptEnabled(true);
				//view.loadUrl(url);
			//	view.loadUrl("javascript:var my = document.getElementById('tools');if (my != null)my.parentNode.removeChild(my);");
//				ProgressDialog progressDialog = ProgressDialog.show(WebHAHAActivity.this, "���Ե�Ƭ��~",
//					      "���ڼ�����ҳ����", true,true);
//				progressDialog.show();
				super.onPageStarted(view, url, favicon);
			}

			public void onPageFinished(WebView view, String url) {
				//view.loadUrl("javascript:window.local_obj.showSource('<title>'+document.getElementById('tab3').innerHTML+'</title>');");
//				view.loadUrl("javascript:window.local_obj.showSource('<span>'+"
//						+ "document.getElementsByTagName('span')[0].innerHTML+'</html>');");
				view.loadUrl("javascript:var my = document.getElementById('tools');if (my != null)my.parentNode.removeChild(my);");
				super.onPageFinished(view, url);
			}
		}

	 final class InJavaScriptLocalObj {
			public void showSource(String html) {
				try {
//					int index = html.indexOf("Kbps");
//					String result = html.substring(index - 9, index + 4);
//					result = result.substring(result.indexOf(">") + 1,
//							result.indexOf("Kbps"));
//					DecimalFormat df = new java.text.DecimalFormat("#.##");
					str=html;
				} catch (Exception e) {
				}

			}
		}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		this.finish();
		return super.onKeyDown(keyCode, event);
	}
	 
}