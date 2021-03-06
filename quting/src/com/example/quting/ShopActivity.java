package com.example.quting;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

public class ShopActivity extends Activity{
	
	private GridView gridView;
	private ShopAdapter adapter;
	
	private QuTingApplication application; //程序全局类
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_shop);
		
 		application = (QuTingApplication)getApplication();
 		application.addActivity(this);
		
		gridView = (GridView)findViewById(R.id.gridview);
		//adapter = new ShopAdapter(getApplicationContext());
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {  // 测试测试测试测试
			@Override
			public void onItemClick(AdapterView parent, View view, int position, long id) {
				
			}  
		});
	}
	
	public void onBackMainClicked(View view){
		Intent intent = new Intent(this,MainActivity.class);
 		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
 		startActivity(intent);
     	if(version >= 5) {   
     	     overridePendingTransition(R.anim.slid_left, R.anim.slid_right);
     	}
	}
	
    // 返回键 返回到主界面
 	@Override
 	public boolean onKeyDown(int keyCode, KeyEvent event) {
 		if (keyCode == KeyEvent.KEYCODE_BACK) {
 			onBackMainClicked(null);
 		}
 		return super.onKeyDown(keyCode, event);
 	}
}
