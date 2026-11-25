
package com.example.mihaila_cristina_activitatedam2026;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ComputerAdapter extends BaseAdapter {

    private ArrayList<Computer> lista;
    private Context context;
    private int layout;

    // Constructor
    public ComputerAdapter(Context context, int layout, ArrayList<Computer> lista) {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Computer getItem(int position) {
        return this.lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        //daca nu avem randul, aici incarcam cum ar veni layout-ul din xml
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            view = inflater.inflate(layout, parent, false);
        }

        Computer computer = getItem(position);

        TextView marcaTV = view.findViewById(R.id.marcaTV);
        TextView procesorTV = view.findViewById(R.id.procesorTV);
        TextView pretTV = view.findViewById(R.id.pretTV);
        TextView tipTV = view.findViewById(R.id.tipTV);
        TextView garantieTV = view.findViewById(R.id.garantieTV);
        TextView ssdTV = view.findViewById(R.id.ssdTV);
        TextView ratingTV = view.findViewById(R.id.ratingTV);

        marcaTV.setText(computer.getMarca());

        procesorTV.setText("Procesor: " + computer.getProcesor());

        pretTV.setText("Pret: " + computer.getPret() + " RON");

        tipTV.setText("Tip: " + computer.getTip());

        garantieTV.setText("Garantie: " + (computer.isAreGarantie() ? "Da" : "Nu"));

        ssdTV.setText("SSD: " + (computer.isAreSsd() ? "Da" : "Nu"));

        ratingTV.setText("Rating: " + computer.getRating() + " / 5");

        return view;
    }
}
