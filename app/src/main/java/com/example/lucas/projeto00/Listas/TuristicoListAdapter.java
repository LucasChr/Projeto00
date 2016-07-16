package com.example.lucas.projeto00.Listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucas.projeto00.PTuristico.Turistico;
import com.example.lucas.projeto00.R;

import java.util.List;

/**
 * Created by lucas on 14/07/16.
 */
public class TuristicoListAdapter extends ArrayAdapter<Turistico> {

    int layout;
    Context ct;
    List<Turistico> turisticos;

    public TuristicoListAdapter(Context context, int layout, List turisticos) {
        super(context, layout, turisticos);
        this.ct = context;
        this.layout = layout;
        this.turisticos = turisticos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(layout,null);

        TextView tvNome = (TextView) itemView.findViewById(R.id.turistico_list_item_tvNome);
        TextView tvEndereco = (TextView) itemView.findViewById(R.id.turistico_list_item_tvLocalizacao);

        Turistico turistico = turisticos.get(position);
        tvNome.setText(turistico.getNome());
        tvEndereco.setText(turistico.getEndereco());

        return itemView;
    }
}
