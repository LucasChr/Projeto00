package com.example.lucas.projeto00.Mapa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucas.projeto00.R;

public class MapaActivity extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
