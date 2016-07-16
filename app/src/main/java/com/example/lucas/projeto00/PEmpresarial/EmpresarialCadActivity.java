package com.example.lucas.projeto00.PEmpresarial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucas.projeto00.R;

public class EmpresarialCadActivity extends Activity implements LocationListener {

    EmpresaDAO dao;
    EditText edtID, edtNome, edtTelefone, edtSite, edtLatitude, edtLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresarial_cad);

        edtID = (EditText) findViewById(R.id.empresarial_cad_edtID);
        edtNome = (EditText) findViewById(R.id.empresarial_cad_edtNome);
        edtTelefone = (EditText) findViewById(R.id.empresarial_cad_edtTelefone);
        edtLatitude = (EditText) findViewById(R.id.empresarial_cad_edtLatitude);
        edtLongitude = (EditText) findViewById(R.id.empresarial_cad_edtLongitude);
        edtSite = (EditText) findViewById(R.id.empresarial_cad_edtSite);

        dao = new EmpresaDAO(this);
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
        menu.add(0, 1, 0, "Salvar");
        menu.add(0, 2, 0, "Alterar");
        menu.add(0, 3, 0, "Buscar");
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
        Empresa e = new Empresa();
        e.setNome(edtNome.getText().toString());
        e.setTelefone(edtTelefone.getText().toString());
        e.setSite(edtSite.getText().toString());
        e.setLatitude(edtLatitude.getText().toString());
        e.setLongitude(edtLongitude.getText().toString());
        dao.salvar(e);
        setResult(1);
        finish();
    }

    public void buscar() {
        Empresa empresa = dao.buscar(edtID.getText().toString());
        edtNome.setText(empresa.getNome());
        edtTelefone.setText(empresa.getTelefone());
        edtSite.setText(empresa.getSite());
        edtLatitude.setText(empresa.getLatitude());
        edtLongitude.setText(empresa.getLongitude());
        setResult(3);
    }

    public void alterar() {
        Empresa e = new Empresa();
        e.setId(new Long(edtID.getText().toString()));
        e.setNome(edtNome.getText().toString());
        e.setSite(edtSite.getText().toString());
        e.setTelefone(edtTelefone.getText().toString());
        e.setLatitude(edtLatitude.getText().toString());
        e.setLongitude(edtLongitude.getText().toString());
        dao.alterar(e);
        setResult(2);
        finish();
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("valor", edtID.getText().toString());
        outState.putString("valor", edtLongitude.getText().toString());
        outState.putString("valor", edtLatitude.getText().toString());
        outState.putString("valor", edtNome.getText().toString());
        outState.putString("valor", edtSite.getText().toString());
        outState.putString("valor", edtTelefone.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        edtID.setText(bundle.getString("valor"));
        edtLongitude.setText(bundle.getString("valor"));
        edtNome.setText(bundle.getString("valor"));
        edtSite.setText(bundle.getString("valor"));
        edtLatitude.setText(bundle.getString("valor"));
        edtTelefone.setText(bundle.getString("valor"));
        Log.i("bundle", "restore");
    }
}
