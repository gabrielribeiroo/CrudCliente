package com.example.pacientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class listaAdapterHistorico extends ArrayAdapter<Historico> {

    private Context context;
    private ArrayList<Historico> lista;

    public listaAdapterHistorico(Context context, ArrayList<Historico> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Historico historicoPosi = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.historico, null);
        TextView textView = (TextView) convertView.findViewById(R.id.tipo);
        textView.setText(historicoPosi.getTitulo());
        TextView textView2 = (TextView) convertView.findViewById(R.id.Data);
        textView2.setText(historicoPosi.getData());
        return convertView;
    }
}
