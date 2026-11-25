package com.example.mihaila_cristina_activitatedam2026;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private Context context;
    private int layoutId; //R.layout.imagine_layout
    private List<ImagineDomeniu> lista;

    public ImagineAdapter(Context context, int layoutId, List<ImagineDomeniu> lista) {
        this.context = context;
        this.layoutId = layoutId;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rand = LayoutInflater.from(context).inflate(layoutId,parent,false);
        ImageView iv = rand.findViewById(R.id.imagineIV);
        TextView tv = rand.findViewById(R.id.textTV);

        ImagineDomeniu item = lista.get(position);
        tv.setText(item.getTextAfisat());

        if(item.getImagine()!=null){
            iv.setImageBitmap(item.getImagine());
        }
        else{
            iv.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return rand;
    }
}
