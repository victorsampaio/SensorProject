package android.com.sensorproject.light;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.com.sensorproject.R;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SensorLightActivity extends Activity implements SensorEventListener {

    private static final int SENSOR_TYPE = Sensor.TYPE_LIGHT;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SeekBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_light);

        progress = (SeekBar)findViewById(R.id.progress);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(SENSOR_TYPE);
        if (sensor != null){
            // Difine the maximum value to the SeekBar
            float max = sensor.getMaximumRange();
            progress.setMax((int) max);
        } else {
            Toast.makeText(this, "Sensor not availabla", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor != null){
            sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Read the sensor value(intensity of the light)
        float value = event.values[0];
        ((TextView)findViewById(R.id.tValue)).setText("Light: " + value);
        //updates the value of the SeekBar
        progress.setProgress((int)value);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensor_light, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
