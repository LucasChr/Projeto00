package com.example.lucas.projeto00.PTuristico;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.lucas.projeto00.R;

public class TuristicoCadActivity extends Activity implements LocationListener {

    TuristicoDAO dao;

    final int MENU_SALVAR = 1;
    final int MENU_ALTERAR = 2;
    final int MENU_BUSCAR = 3;

    EditText edtID, edtNome, edtLatitude, edtLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turistico_cad);
        edtID = (EditText) findViewById(R.id.turistico_cad_edtID);
        edtNome = (EditText) findViewById(R.id.turistico_cad_edtNome);
        edtLatitude = (EditText) findViewById(R.id.turistico_cad_edtLatitude);
        edtLongitude = (EditText) findViewById(R.id.turistico_cad_edtLongitude);

        dao = new TuristicoDAO(this);
        Intent intent = getIntent();
        if (!intent.getStringExtra("ID").equals("")) {
            String id = intent.getStringExtra("ID");
            edtID.setText(id);
            buscar();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_SALVAR, 0, "Salvar");
        menu.add(0, MENU_ALTERAR, 0, "Alterar");
        menu.add(0, MENU_BUSCAR, 0, "Buscar");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 1:
                salvar();
                break;
            case 2:
                alterar();
                break;
            case 3:
                buscar();
                break;
        }

        return true;
    }

    public void salvar() {
        Turistico t = new Turistico();
        t.setNome(edtNome.getText().toString());
        t.setLatitude(edtLatitude.getText().toString());
        t.setLongitude(edtLongitude.getText().toString());
        dao.salvar(t);
        setResult(1);
        finish();
    }

    public void alterar() {
        Turistico t = new Turistico();
        t.setId(new Long(edtID.getText().toString()));
        t.setNome(edtNome.getText().toString());
        t.setLatitude(edtLatitude.getText().toString());
        t.setLongitude(edtLongitude.getText().toString());
        dao.alterar(t);
        setResult(2);
        finish();
    }

    public void buscar() {
        Turistico turistico = dao.buscar(edtID.getText().toString());
        edtNome.setText(turistico.getNome());
        edtLatitude.setText(turistico.getLatitude());
        edtLongitude.setText(turistico.getLongitude());
        setResult(3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationManager lM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        lM.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        LocationManager lM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        edtLatitude.setText(String.valueOf(location.getLatitude()));
        edtLongitude.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

