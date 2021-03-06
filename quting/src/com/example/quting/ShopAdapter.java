package com.example.quting;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.quting.entity.media.MediaBaseEntity;
import com.example.quting.entity.media.MediaEntity;
import com.example.quting.entity.mp3.Mp3BaseEntity;
import com.example.quting.media.MyMediaPlayer;
import com.example.quting.resource.MediaInfo;
import com.example.quting.resource.Mp3Info;
import com.example.quting.utils.BitmapTool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopAdapter extends BaseAdapter {
	
	 private LayoutInflater mInflater;                 
	 private Context context;        
	 
	 //地址是动态 获取的
	 private String myurl = "http://t.pamakids.com/api/media?page=1";
	 
	 private MediaInfo mediaInfo;
	 private List<MediaBaseEntity> mediaList;
	 
	 private int flag;
	 
	 private File cache;                                 // 文件缓存
	 
	 private MyMediaPlayer myMediaPlayer;
	 
	 public ShopAdapter(Context context,int flag,File cache){
		 this.context = context;
		 
		 mediaInfo = MediaInfo.getInstance();
		 this.mediaList = mediaInfo.swichMediaBaseEntity(flag);
		 
		 this.flag = flag;
		 this.cache = cache;
		 
		 mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
	 }
	 
	@Override
	public int getCount() {
		return mediaList.size();
	}

	@Override
	public Object getItem(int position) {
		return mediaList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
        if (convertView != null){
            view = convertView;
        } else {
            view = mInflater.inflate(R.layout.shop_grid_item, null);
        }
        
        MediaBaseEntity mediaBaseEntity = mediaList.get(position);  // 获取实体类
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        asyncloadImage(icon,mediaBaseEntity.getMtype());
    	
    	TextView leibieView = (TextView)view.findViewById(R.id.story_name);
        leibieView.setText(mediaBaseEntity.getName());
        
        ImageView imageView = (ImageView)view.findViewById(R.id.icon_play);
        imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("onPlayStateClicked", "dddd");
				String myurl = "http://t.pamakids.com/api/mfiles?medium_id=4";
				//http://t.pamakids.com/mp3/1.mp3
				List<Mp3BaseEntity> list;
				try {
					list = Mp3Info.getInstance().findMp3ListFromNet(myurl).getMfiles();
					List<String> listUrl = new ArrayList<String>();
					for(int i=0;i<list.size();i++){
						String str = list.get(i).getUrl().substring(6, list.get(i).getUrl().length());
						listUrl.add("http://t.pamakids.com"+str);
					}
					MyMediaPlayer player = new MyMediaPlayer(listUrl);
					player.start();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		return view;
	  }
	
	  private void asyncloadImage(ImageView icon, String path) {   // 异步加载图片方法
        AsyncImageTask task = new AsyncImageTask(MediaInfo.getInstance(),icon);  // 实例化异步任务类  
        task.execute(path);                                            // 执行任务下载图片
      }
 
     private final class AsyncImageTask extends AsyncTask<String, Integer, Bitmap> {  // 异步类 
        private MediaInfo mediaInfo;                                           // MediaInfo
        private ImageView icon;
 
        public AsyncImageTask(MediaInfo mediaInfo, ImageView icon) {  // 构造方法  
            this.mediaInfo = mediaInfo;
            this.icon = icon;
        }
 
        // 后台运行的子线程子线程  返回对象对于 第三个参数
        @Override
        protected Bitmap doInBackground(String... params) {  
            try {
                return mediaInfo.getImageURI(params[0],cache);   // service 
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
 
        // 这个放在在ui线程中执行
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 完成图片的绑定
            if (icon != null && bitmap != null) {
            	icon.setImageBitmap(bitmap);   // 在ui线程中执行
                Log.i("setImageURIsetImageURI", bitmap+"");
            }
        }
    }
     
    public void setFlag(int flag){
    	this.flag = flag;
    }
    
    public int getFlag(){
    	return flag;
    }
}
