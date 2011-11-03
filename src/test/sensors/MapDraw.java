package test.sensors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MapDraw extends Activity implements SurfaceHolder.Callback{
	/** Member declaration */
	public Screen mapView;
	public SurfaceHolder holder;
	public int height,width;
	
	
	/** onCreate initializes this activity */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mapView = new Screen(this);
		
		setContentView(mapView);
		height = mapView.getHeight();
		width = mapView.getWidth();
		
		holder = mapView.getHolder();
		holder.addCallback(this);
		
		
		
	}

	@Override
	public void onPause(){
		super.onPause();
		
	}
	
	/** SurfaceHolder.Callback something ... */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
		Canvas canvas = holder.lockCanvas();
		Paint paint = new Paint();
		paint.setAlpha(128);
		paint.setColor(Color.GRAY);
		canvas.drawCircle(width/2, height/2, Math.min(width, height)*3/7, paint);
		
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(5);
		canvas.drawLine(0, height/2, width, height/2, paint);
		canvas.drawLine(width/2, 0, width/2, height, paint);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(width/2, height/2, Math.min(width, height)/5, paint);
		
		Log.e("Draw","did tried to draw ...");
		holder.unlockCanvasAndPost(canvas);
		Log.e("Draw","Canvas Unlocked");
		
		mapView.setSize(height, width);
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		Log.e("Surface","Surface Creating");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}


}

class Screen extends SurfaceView{
	public int width,height;
	public float centX,centY;
	
	public Screen(Context context) {
		super(context);
		setWillNotDraw(false);

		Log.e("Screen","New Srceen Newed");
		Log.e("Screen","@"+width + ":" + height);
	}
	
	public void setSize(int h,int w){
		width = w;
		height = h;
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		centX = width/2 - Container.MyAccelerometer.x*60;
		centY = height/2 + Container.MyAccelerometer.y*60;
		canvas.drawCircle(centX, centY, 25, paint);
		
		paint.setColor(Color.RED);
		float magX,magY,norm;
		magX = Container.MyCompass.x;
		magY = Container.MyCompass.y;
		norm = (float) Math.sqrt(Math.pow(magX, 2.0) + Math.pow(magY, 2.0));
		magX = magX/norm*120;
		magY = magY/norm*120;
		canvas.drawCircle(centX - magX, centY + magY, 15, paint);
		paint.setStrokeWidth(10);
		canvas.drawLine(centX, centY, centX-magX, centY+magY, paint);
		invalidate();
	}
}

