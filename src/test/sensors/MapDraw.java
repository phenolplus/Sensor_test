package test.sensors;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MapDraw extends Activity implements SurfaceHolder.Callback{
	/** Member declaration */
	SurfaceView mapView;
	SurfaceHolder holder;
	int height,width;
	float centX,centY;
	
	/** onCreate initializes this activity */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mapView = new SurfaceView(this);
		setContentView(mapView);
		height = mapView.getHeight();
		width = mapView.getWidth();
		
		holder = mapView.getHolder();
		holder.addCallback(this);
		
		
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
