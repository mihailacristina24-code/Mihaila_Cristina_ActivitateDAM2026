
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
    public ComputerAdapter(Context context,
                           int layout,
                           ArrayList<Computer> lista) {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }

    // Returneaza numarul de elemente din lista
    @Override
    public int getCount() {
        return lista.size();
    }

    // Returneaza obiectul de pe pozitia solicitata
    @Override
    public Computer getItem(int position) {
        return lista.get(position);
    }

    // Returneaza identificatorul elementului
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Construieste vizual fiecare rand din ListView
    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {

        View view = convertView;

        // Daca nu avem deja un rand creat,
        // incarcam layout-ul XML
        if (view == null) {
            LayoutInflater inflater =
                    LayoutInflater.from(context);

            view = inflater.inflate(layout, parent, false);
        }

        // Luam obiectul corespunzator pozitiei
        Computer computer = getItem(position);

        // Identificam elementele vizuale
        TextView marcaTV = view.findViewById(R.id.marcaTV);
        TextView procesorTV = view.findViewById(R.id.procesorTV);
        TextView pretTV = view.findViewById(R.id.pretTV);
        TextView tipTV = view.findViewById(R.id.tipTV);
        TextView garantieTV = view.findViewById(R.id.garantieTV);
        TextView ssdTV = view.findViewById(R.id.ssdTV);
        TextView ratingTV = view.findViewById(R.id.ratingTV);

        // Completam elementele vizuale cu datele obiectului
        marcaTV.setText(computer.getMarca());

        procesorTV.setText("Procesor: " + computer.getProcesor());

        pretTV.setText("Pret: " + computer.getPret() + " RON");

        tipTV.setText("Tip: " + computer.getTip());

        garantieTV.setText("Garantie: " +
                (computer.isAreGarantie() ? "Da" : "Nu"));

        ssdTV.setText("SSD: " +
                (computer.isAreSsd() ? "Da" : "Nu"));

        ratingTV.setText("Rating: " +
                computer.getRating() + " / 5");

        return view;
    }
}
