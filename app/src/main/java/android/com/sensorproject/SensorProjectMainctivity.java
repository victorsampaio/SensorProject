package android.com.sensorproject;

import android.app.Activity;
import android.app.ListActivity;
import android.com.sensorproject.light.SensorLightActivity;
import android.com.sensorproject.proximity.SensorProximityActivity;
import android.com.sensorproject.temperature.AmbientTemperatureActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SensorProjectMainctivity extends ListActivity {

    private static final String CATEGORY = "sensorProjectMainActivity";
    private static final String[] options = new String[]{"1 - Sensors list", "2 - Sensor Light Activity",
            "3 - Temperature","4 - Proximity" ,"5 - Exit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sensor_project_mainctivity);

        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,options));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                    startActivity(new Intent(this, SensorListActivity.class));
                break;

            case 1:
                startActivity(new Intent(this, SensorLightActivity.class));
                break;

            case 2:
                startActivity(new Intent(this, AmbientTemperatureActivity.class));
                break;

            case 3:
                startActivity(new Intent(this, SensorProximityActivity.class));
                break;

            case 4:
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensor_project_mainctivity, menu);
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
