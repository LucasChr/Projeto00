package com.example.lucas.projeto00.Listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucas.projeto00.PEmpresarial.Empresa;
import com.example.lucas.projeto00.R;

import java.util.List;

/**
 * Created by lucas on 14/07/16.
 */
public class EmpresarialListAdapter extends ArrayAdapter<Empresa> {

    int layout;
    Context context;
    List<Empresa> empresas;

    public EmpresarialListAdapter(Context context, int layout, List empresas) {
        super(context, layout, empresas);
        this.context = context;
        this.empresas = empresas;
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemV = inf.inflate(layout, null);

        TextView tvNome = (TextView) itemV.findViewById(R.id.empresarial_list_item_tvNome);
        TextView tvTelefone = (TextView) itemV.findViewById(R.id.empresarial_list_item_tvTelefone);
        TextView tvSite = (TextView) itemV.findViewById(R.id.empresarial_list_item_tvSite);

        Empresa empresa = empresas.get(position);
        tvNome.setText(empresa.getNome());
        tvTelefone.setText(empresa.getTelefone());
        tvSite.setText(empresa.getSite());

        return itemV;
    }
}
