package br.com.quaddro.emprestes.qandroid100.controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class SensorActivity extends QuaddroActivity {

    private SensorManager sm;
    private TextView accuracy;
    private TextView values;
    private Spinner s;
    private List<Sensor> sensors;

    private int index = 0;

    private SensorEventListener sensorEventAction = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            StringBuilder sb = new StringBuilder("Valores do sensor:\n");

            for (int i = 0; i < event.values.length; i++) {
                sb.append("\tvalores[").append(i).append("] = ").append(event.values[i]).append('\n');
            }

            values.setText(sb);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int a) {
            StringBuilder sb = new StringBuilder();

            sb.append("Sensor: ").append(sensor.getName()).append('\n');
            sb.append("\tTipo: ").append(sensor.getType()).append('\n');
            sb.append("\tPrecisão: ").append(a).append('\n');
            sb.append("\tEnergia: ").append(sensor.getPower()).append('\n');
            sb.append("\tFabricante: ").append(sensor.getVendor()).append('\n');
            sb.append("\tVersão: ").append(sensor.getVersion()).append('\n');

            accuracy.setText(sb);
        }
    };

    private class SensorAdapter extends ArrayAdapter<Sensor> {

        private LayoutInflater li;
        private int layout;

        public SensorAdapter(Context context, int layout, List<Sensor> objects) {
            super(context, layout, android.R.id.text1, objects);

            this.li = LayoutInflater.from(context);
            this.layout = layout;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            Sensor s;

            convertView = convertView == null ? li.inflate(layout, null) : convertView;
            s = getItem(position);

            tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(s.getName());

            tv = (TextView) convertView.findViewById(android.R.id.text2);
            tv.setText(s.getVendor());

            return convertView;
        }
    }

    private AdapterView.OnItemSelectedListener sensorSelectedAction = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            index = position;
            sm.unregisterListener(sensorEventAction);
            sm.registerListener(sensorEventAction, sensors.get(position), SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) { }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sensores_view);

        s = getViewById(R.id.sensor);
        accuracy = getViewById(R.id.sensor_accuracy);
        values = getViewById(R.id.sensor_values);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        s.setOnItemSelectedListener(sensorSelectedAction);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensors = sm.getSensorList(Sensor.TYPE_ALL);
        s.setAdapter(new SensorAdapter(this, R.layout.sensor_view, sensors));

        sm.registerListener(sensorEventAction, sensors.get(index), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(sensorEventAction);
        super.onPause();
    }
}
