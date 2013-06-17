package com.example.quting;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class MainActivity extends Activity{
	
	private GridView gridView;
	private GridAdapter adapter;
	
	private ListView listView;
	private ConfigAdapter configadapter;
	
	private SlidingLayout slidingLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		slidingLayout = (SlidingLayout)findViewById(R.id.slidingLayout);
		gridView = (GridView)findViewById(R.id.gridview);
		adapter = new GridAdapter(getApplicationContext());
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {  // 测试测试测试测试
			@Override
			public void onItemClick(AdapterView parent, View view, int position, long id) {
				if (position == 0) {
					goToMyFavActivity();
				}else if (position == 1) {
					goToPlayActivity();
				}
			}  
		});
		
		listView = (ListView)findViewById(R.id.config_listview);
//	    要想让ListView显示底部的分割线，需要同事满足以下的选项: 
//		1）mFooterDividersEnabled 必须为true，其默认值是true的 
//		2)ListView的高度必须为FILL_PARENT。
		configadapter = new ConfigAdapter(getApplicationContext());
		listView.setAdapter(configadapter);
		//slidingLayout.setScrollEvent(gridView);
	}
	
	public void onConfigClicked(View view){
		// 实现点击一下menu展示左侧布局，再点击一下隐藏左侧布局的功能
		if (slidingLayout.isLeftLayoutVisible()) {
			slidingLayout.scrollToRightLayout();
		} else {
			slidingLayout.scrollToLeftLayout();
		}
	}
	
	public void onSearchClicked(View view){
		Intent intent = new Intent(this,SearchActivity.class);
		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
		startActivity(intent);
    	if(version >= 5) {   
    	     overridePendingTransition(R.anim.slid_up, R.anim.slid_down);
    	}
	}
	
	public void goToMyFavActivity(){
		Intent intent = new Intent(this,MyFavActivity.class);
		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
		startActivity(intent);
    	if(version >= 5) {   
    	     overridePendingTransition(R.anim.slid_left, R.anim.slid_right);
    	}
	}
	
	public void goToPlayActivity(){
		Intent intent = new Intent(this,PlayActivity.class);
//		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
		startActivity(intent);
//    	if(version >= 5) {   
//    	     overridePendingTransition(R.anim.slid_left, R.anim.slid_right);
//    	}
	}
	
	public void goToShopClicked(View view){
		Intent intent = new Intent(this,ShopActivity.class);
		int version = Integer.valueOf(android.os.Build.VERSION.SDK);   
		startActivity(intent);
    	if(version >= 5) {   
    	     overridePendingTransition(R.anim.slid_left, R.anim.slid_right);
    	}
	}
	
}
