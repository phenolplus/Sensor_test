package test.sensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SensorCheckActivity extends Activity {
    /** Members Declaration */
	private SensorManager manager;
	private Sensor sensor,compass;
	private TextView accInfo,readX,readY,readZ,comInfo,compX,compY,compZ;
	private SensorEventListener aListener,mListener;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        accInfo = (TextView) findViewById(R.id.textView1);
        readX = (TextView) findViewById(R.id.textViewX);
        readY = (TextView) findViewById(R.id.textViewY);
        readZ = (TextView) findViewById(R.id.textViewZ);
        
        comInfo = (TextView) findViewById(R.id.textView2);
        compX = (TextView) findViewById(R.id.textViewP);
        compY = (TextView) findViewById(R.id.textViewQ);
        compZ = (TextView) findViewById(R.id.textViewR);
        
        // Get sensors
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Container.manager = manager;
        // Get accelerometer
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Get compass
        compass = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    	
    }
    
    @Override
    public void onResume(){
    	super.onResume();
        // setup accelerometer       
        if(sensor == null) {
        	// not found
        	Log.e("Sensors","sensor NOT found");
        	accInfo.setText("No Acelerometer found !!!");
        	Toast.makeText(this, "Error occured !!", Toast.LENGTH_SHORT).show();
        	readX.setText("Sorry ...");
        	readY.setText("Sorry ...");
        	readZ.setText("Sorry ...");
        } else {
        	// Print informations
        	String str ="\nAccelerometer is :\n"
        			+ "Sensor Type = " + sensor.getType() + "\n"
        			+ "Sensor made by :" + sensor.getVendor() + "\n" 
        			+ "Time resolution = " + sensor.getMinDelay() + "us\n"
        			+ "Resolution = " +sensor.getResolution() + "\n";
        	accInfo.setText(str);
        	setAccelerometerListener();
        	Log.e("Sensors","Accelerometer set !");
        }
        
        // setup compass
        if(compass == null){
        	// not found
        	Log.e("Sensors","sensor NOT found");
        	comInfo.setText("No Magnetic-field sensor found !!!");
        	Toast.makeText(this, "Error occured !!", Toast.LENGTH_SHORT).show();
        	compX.setText("Sorry ...");
        	compY.setText("Sorry ...");
        	compZ.setText("Sorry ...");
        } else {
            	// Print informations
            	String str ="\n\nMagnetic Field Sensor is :\n"
            			+ "Sensor Type = " + compass.getType() + "\n"
            			+ "Sensor made by :" + compass.getVendor() + "\n" 
            			+ "Time resolution = " + compass.getMinDelay() + "us\n"
            			+ "Resolution = " +compass.getResolution() + "\n";
            	comInfo.setText(str);
            	setCompassListener();
            	Log.e("Sensors","Compass set !");
        }
        
        
        Log.e("Application","Sensor Activity Created");
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	
    	if(sensor != null){
    		manager.unregisterListener(aListener);
    	}
    	
    	if(compass != null){
    		manager.unregisterListener(mListener);
    	}
    	
    	Log.e("Application","Destroied");
    }
    
    
    
    private void setAccelerometerListener(){
    	// setup & implement accelerometer listener
    	aListener = new SensorEventListener(){

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSensorChanged(SensorEvent event) {
				// TODO Auto-generated method stub
				Container.MyAccelerometer.x = event.values[0];
				Container.MyAccelerometer.y = event.values[1];
				Container.MyAccelerometer.z = event.values[2];
				
				readX.setText(String.format("read X = %+.3f m/s^2",event.values[0]));
				readY.setText(String.format("read Y = %+.3f m/s^2",event.values[1]));
				readZ.setText(String.format("read Z = %+.3f m/s^2",event.values[2]));
			}
    		
    		
    	};
    	manager.registerListener(aListener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }
    
    private void setCompassListener(){
    	mListener = new SensorEventListener(){

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSensorChanged(SensorEvent event) {
				// TODO Auto-generated method stub
				Container.MyCompass.x = event.values[0];
				Container.MyCompass.y = event.values[1];
				Container.MyCompass.z = event.values[2];
				
				compX.setText(String.format("read X = %+.3f uT",event.values[0]));
				compY.setText(String.format("read Y = %+.3f uT",event.values[1]));
				compZ.setText(String.format("read Z = %+.3f uT",event.values[2]));
			}
    		
    	};
    	
    	manager.registerListener(mListener, compass, SensorManager.SENSOR_DELAY_GAME);
    }
    
    /** Controlling the view */
    public void clicked(View view){
    	
    	Toast.makeText(this, "Changing to Graphics ", Toast.LENGTH_SHORT).show();
       	Intent intent = new Intent();
		intent.setClass(this, MapDraw.class);
		
		startActivity(intent);
		Log.e("Intent","Switched");

    }
    
   
    
}