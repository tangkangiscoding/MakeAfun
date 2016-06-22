package com.example.makeafun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class BaiduMapActivity extends Activity {
	public LocationClient mLocationClient = null;
	public TextView mTv;
	public Button mLocBtn;
	private double Latitude;
	private double Longitude;
	private Typeface tf;
	private String fontPath ;
	public MyLocationListenner myListener = new MyLocationListenner();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        fontPath = "fonts/huawenxinkai.ttf";
		tf = Typeface.createFromAsset(getAssets(), fontPath);
		
        mLocationClient = new LocationClient( this );
        mLocationClient.registerLocationListener( myListener );
        mLocBtn = (Button)findViewById(R.id.localButton);
        mLocBtn.setTypeface(tf);
        mTv = (TextView)findViewById(R.id.mapinfo);
        mLocationClient.start();
        mLocBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (mLocationClient != null && mLocationClient.isStarted()){
					setLocationOption();
					mLocationClient.requestLocation();	
					Bundle bundle=new Bundle();
					bundle.putDouble("Latitude", Latitude);
					bundle.putDouble("Longitude", Longitude);					
					Intent intent=new Intent(BaiduMapActivity.this,WebHAHAActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					
				}				
			}
		});
    }
    
    /**
	 * ��ʾ�ַ���
	 * @param str
	 */
	public void logMsg(String str) {
		try {
			if ( mTv != null )
				fontPath = "fonts/youyuan.ttf";
				tf = Typeface.createFromAsset(getAssets(), fontPath);
				mTv.setText(str);
			    mTv.setTypeface(tf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /**
		 * ��������������λ�õ�ʱ�򣬸�ʽ�����ַ������������Ļ��
		 */
		public class MyLocationListenner implements BDLocationListener {
			@Override
			public void onReceiveLocation(BDLocation location) {
				if (location == null)
					return ;
				if(location.getAddrStr()==null){
					mLocationClient.requestLocation();
				}
				Latitude=location.getLatitude();
				Longitude=location.getLongitude();
					StringBuffer sb = new StringBuffer(256);
					sb.append("\n");
					sb.append("�ϴζ�λʱ�� : ");
					sb.append("\n");
					sb.append(location.getTime());
					sb.append("\n");
					sb.append("\n���� : ");
					sb.append("\n");
					sb.append(location.getLatitude());
					sb.append("\n");
					sb.append("\nγ�� : ");
					sb.append("\n");
					sb.append(location.getLongitude());
					sb.append("\n");
					sb.append("\n�뾶 : ");
					sb.append("\n");
					sb.append(location.getRadius());
					sb.append("\n");
					/*sb.append("time : ");
					sb.append(location.getTime());
					sb.append("\nerror code : ");
					sb.append(location.getLocType());
					sb.append("\nlatitude : ");
					sb.append(location.getLatitude());
					sb.append("\nlontitude : ");
					sb.append(location.getLongitude());
					sb.append("\nradius : ");
					sb.append(location.getRadius());*/
					if (location.getLocType() == BDLocation.TypeGpsLocation){
						sb.append("\nspeed : ");
						sb.append(location.getSpeed());
						sb.append("\nsatellite : ");
						sb.append(location.getSatelliteNumber());
					} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
		//				sb.append("\nʡ��");
		//				sb.append(location.getProvince());
		//				sb.append("\n�У�");
		//				sb.append(location.getCity());
		//				sb.append("\n��/�أ�");
		//				sb.append(location.getDistrict());
						sb.append("\n��ַ : ");
						sb.append("\n");
						sb.append(location.getAddrStr());
					}
					
					/*sb.append("\nsdk version : ");
					sb.append(mLocationClient.getVersion());
					sb.append("\nisCellChangeFlag : ");
					sb.append(location.isCellChangeFlag());*/
					logMsg(sb.toString());
				
			}
			
			public void onReceivePoi(BDLocation poiLocation) {
				if (poiLocation == null){
					return ; 
				}
				StringBuffer sb = new StringBuffer(256);
				sb.append("Poi time : ");
				sb.append(poiLocation.getTime());
				sb.append("\nerror code : "); 
				sb.append(poiLocation.getLocType());
				sb.append("\nlatitude : ");
				sb.append(poiLocation.getLatitude());
				sb.append("\nlontitude : ");
				sb.append(poiLocation.getLongitude());
				sb.append("\nradius : ");
				sb.append(poiLocation.getRadius());
				if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation){
					sb.append("\naddr : ");
					sb.append(poiLocation.getAddrStr());
				} 
				if(poiLocation.hasPoi()){
					sb.append("\nPoi:");
					sb.append(poiLocation.getPoi());
				}else{				
					sb.append("noPoi information");
				}
			}
		}
		
		private void setLocationOption(){
			LocationClientOption option = new LocationClientOption();
			option.setServiceName("com.baidu.location.service_v2.6");
			
			// ��Ҫ��ַ��Ϣ������Ϊ�����κ�ֵ��string���ͣ��Ҳ���Ϊnull��ʱ������ʾ�޵�ַ��Ϣ��  
		     option.setAddrType("all");  
		     // �����Ƿ񷵻�POI�ĵ绰�͵�ַ����ϸ��Ϣ��Ĭ��ֵΪfalse����������POI�ĵ绰�͵�ַ��Ϣ��   
		     option.setPoiExtraInfo(true);  
		       
		     // ���ò�Ʒ�����ơ�ǿ�ҽ�����ʹ���Զ���Ĳ�Ʒ�����ƣ����������Ժ�Ϊ���ṩ����Ч׼ȷ�Ķ�λ����   
		     option.setProdName("ͨ��GPS��λ�ҵ�ǰ��λ��");  
		       
		     // ����GPS��ʹ��gpsǰ�����û�Ӳ����gps��Ĭ���ǲ���gps�ġ�   
		     option.setOpenGps(true);  
		       
		     // ��λ��ʱ��������λ��ms  
		     // �����������ֵ���ڵ���1000��ms��ʱ����λSDK�ڲ�ʹ�ö�ʱ��λģʽ��  
		      option.setScanSpan(500);  
		       
		     // ��ѯ��Χ��Ĭ��ֵΪ500�����Ե�ǰ��λλ��Ϊ���ĵİ뾶��С��  
		     option.setPoiDistance(500);  
		     // �������û��涨λ����  
		     option.disableCache(true);  
		       
		     // ����ϵ���ͣ��ٶ��ֻ���ͼ����ӿ��е�����ϵĬ����bd09ll  
		     option.setCoorType("bd09ll");  
		       
		     // �������ɷ��ص�POI������Ĭ��ֵΪ3������POI��ѯ�ȽϺķ�������������෵�ص�POI�������Ա��ʡ������  
		     option.setPoiNumber(3);  
		       
		     // ���ö�λ��ʽ�����ȼ���  
		     // ��gps���ã����һ�ȡ�˶�λ���ʱ�����ٷ�����������ֱ�ӷ��ظ��û����ꡣ���ѡ���ʺ�ϣ���õ�׼ȷ����λ�õ��û������gps�����ã��ٷ����������󣬽��ж�λ��  
		     option.setPriority(LocationClientOption.GpsFirst); 
		     option.setPoiExtraInfo(true);
			 mLocationClient.setLocOption(option);
		}
}