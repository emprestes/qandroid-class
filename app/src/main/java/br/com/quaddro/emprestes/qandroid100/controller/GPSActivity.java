package br.com.quaddro.emprestes.qandroid100.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.api.LocationAdapter;
import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class GPSActivity extends QuaddroActivity {

    private TextView gpsLatitude;
    private TextView gpsLongitude;
    private TextView gpsEndereco;

    private LocationManager lm;
    private LocationListener ll;
    private String gps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gps_view);

        gpsLatitude = (TextView) findViewById(R.id.gps_latitude);
        gpsLongitude = (TextView) findViewById(R.id.gps_longitude);
        gpsEndereco = (TextView) findViewById(R.id.gps_endereco);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        ll = new LocationAdapter() {
            @Override
            public void onLocationChanged(Location location) {
                localizar(location);
            }
        };

        int f, c;

        f = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        c = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (f != PackageManager.PERMISSION_GRANTED && c != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        if (f == PackageManager.PERMISSION_GRANTED || c == PackageManager.PERMISSION_GRANTED) {
            gps = lm.getBestProvider(new Criteria(), false);
            localizar(gps);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int f, c;

        f = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        c = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (f == PackageManager.PERMISSION_GRANTED || c == PackageManager.PERMISSION_GRANTED) {
            lm.requestLocationUpdates(gps, 5000, 0, ll);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        int f, c;

        f = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        c = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (f == PackageManager.PERMISSION_GRANTED || c == PackageManager.PERMISSION_GRANTED) {
            lm.removeUpdates(ll);
        }
    }

    private void localizar(String gps) {
        int f, c;

        f = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        c = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (f == PackageManager.PERMISSION_GRANTED || c == PackageManager.PERMISSION_GRANTED) {
            localizar(lm.getLastKnownLocation(gps));
        }
    }

    private void localizar(Location location) {
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            gpsLatitude.setText(String.format("Latitude %s", latitude));
            gpsLongitude.setText(String.format("Longitude %s", longitude));

            try {
                List<Address> enderecos;

                enderecos = new Geocoder(this, Locale.getDefault())
                        .getFromLocation(latitude, longitude, 1);
                if (enderecos.isEmpty()) {
                    gpsEndereco.setText("Endereço não encontrado!");
                } else {
                    Address e = enderecos.get(0);
                    gpsEndereco.setText(String.format("%s, %s, %s, %s, %s",
                            e.getThoroughfare(),
                            e.getSubThoroughfare(),
                            e.getSubLocality(),
                            e.getLocality(),
                            e.getCountryName()));
                }
            } catch (Exception cause) {
                Log.e("gps", "Problemas", cause);
            }
        } else {
            gpsLatitude.setText("Procurando...");
            gpsLongitude.setText(null);
            gpsEndereco.setText(null);
        }
    }
}
