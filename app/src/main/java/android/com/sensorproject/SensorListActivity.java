package android.com.sensorproject;

import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * @author VictorSampaio
 *
 */


public class SensorListActivity extends ListActivity {
    private List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.activity_sensor_list);

        //Read the list of sensors
        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        /* In the method, .getSensorList(), the sensor Type can be changed to the type that you want
        */
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //Create the 'Array' with the names of sensors
        String array[] = new String[sensorList.size()];
        for (int i = 0; i < array.length; i++ ){
            Sensor s = sensorList.get(i);
            array[i] = s.getName() + " - " + s.getVendor() + " - " + s.getType();
        }

        //Create Adapter for populate the ListView
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, array);
        this.setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);

        // Recover the Sensor selected
        Sensor s = sensorList.get(position);
        String msg = s.getName() + " - " + s.getVendor() + " - " + s.getType();
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensor_list, menu);
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
