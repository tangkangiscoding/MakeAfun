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
	 * 显示字符串
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
		 * 监听函数，更新位置的时候，格式化成字符串，输出到屏幕中
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
					sb.append("上次定位时间 : ");
					sb.append("\n");
					sb.append(location.getTime());
					sb.append("\n");
					sb.append("\n经度 : ");
					sb.append("\n");
					sb.append(location.getLatitude());
					sb.append("\n");
					sb.append("\n纬度 : ");
					sb.append("\n");
					sb.append(location.getLongitude());
					sb.append("\n");
					sb.append("\n半径 : ");
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
		//				sb.append("\n省：");
		//				sb.append(location.getProvince());
		//				sb.append("\n市：");
		//				sb.append(location.getCity());
		//				sb.append("\n区/县：");
		//				sb.append(location.getDistrict());
						sb.append("\n地址 : ");
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
			
			// 需要地址信息，设置为其他任何值（string类型，且不能为null）时，都表示无地址信息。  
		     option.setAddrType("all");  
		     // 设置是否返回POI的电话和地址等详细信息。默认值为false，即不返回POI的电话和地址信息。   
		     option.setPoiExtraInfo(true);  
		       
		     // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。   
		     option.setProdName("通过GPS定位我当前的位置");  
		       
		     // 设置GPS，使用gps前提是用户硬件打开gps。默认是不打开gps的。   
		     option.setOpenGps(true);  
		       
		     // 定位的时间间隔，单位：ms  
		     // 当所设的整数值大于等于1000（ms）时，定位SDK内部使用定时定位模式。  
		      option.setScanSpan(500);  
		       
		     // 查询范围，默认值为500，即以当前定位位置为中心的半径大小。  
		     option.setPoiDistance(500);  
		     // 禁用启用缓存定位数据  
		     option.disableCache(true);  
		       
		     // 坐标系类型，百度手机地图对外接口中的坐标系默认是bd09ll  
		     option.setCoorType("bd09ll");  
		       
		     // 设置最多可返回的POI个数，默认值为3。由于POI查询比较耗费流量，设置最多返回的POI个数，以便节省流量。  
		     option.setPoiNumber(3);  
		       
		     // 设置定位方式的优先级。  
		     // 当gps可用，而且获取了定位结果时，不再发起网络请求，直接返回给用户坐标。这个选项适合希望得到准确坐标位置的用户。如果gps不可用，再发起网络请求，进行定位。  
		     option.setPriority(LocationClientOption.GpsFirst); 
		     option.setPoiExtraInfo(true);
			 mLocationClient.setLocOption(option);
		}
}