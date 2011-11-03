package test.sensors;

import android.hardware.SensorManager;

public final class Container {
	public static SensorManager manager;
	
	public static final class MyCompass {
		public static float x;
		public static float y;
		public static float z;
	}
	
	public static final class MyAccelerometer {
		public static float x;
		public static float y;
		public static float z;
	}
	
}
