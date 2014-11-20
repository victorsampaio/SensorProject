package android.com.sensorproject.temperature;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.com.sensorproject.R;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorEventListener;

public class AmbientTemperatureActivity extends Activity implements SensorEventListener{

    private static final String CATEGORY = "AmbientTemperature";
    private static final int SENSOR_TYPE = Sensor.TYPE_AMBIENT_TEMPERATURE;
    private Sensor sensor;
    private SensorManager sensorManager;
    private SeekBar temperatureBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambient_temperature);

        temperatureBar = (SeekBar)findViewById(R.id.seekBar);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(SENSOR_TYPE);

        if (sensor != null){
            //Define the maximous value of the SeekBar
            float max = sensor.getMaximumRange();
            temperatureBar.setMax((int)max);
        } else {
            Toast.makeText(this, "Sensor not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Changed the sensor status of the cursor
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Ambient Temperature value
        float temperature = event.values[0];

        ((TextView) findViewById(R.id.txvTemperature)).setText("Temperature: " + temperature);
        temperatureBar.setProgress((int)temperature);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ambient_temperature, menu);
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
