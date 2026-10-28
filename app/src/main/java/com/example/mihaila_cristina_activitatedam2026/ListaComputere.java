package com.example.mihaila_cristina_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaComputere extends AppCompatActivity {

    private ArrayList<Computer> lista;
    private ArrayAdapter<Computer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_computere);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Luam lista trimisa de MainActivity
        lista = getIntent().getParcelableArrayListExtra(MainActivity.CHEIE_LISTA);
        if (lista == null) {
            lista = new ArrayList<>();
        }

        // 2. Legam lista de ListView printr-un adapter
        ListView listView = findViewById(R.id.listViewComputere);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        // 3. Click scurt -> Toast cu obiectul
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, lista.get(position).toString(), Toast.LENGTH_LONG).show());

        // 4. Click lung -> stergere
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.remove(position);
            adapter.notifyDataSetChanged();
            trimiteListaInapoi();
            Toast.makeText(this, "Computer sters", Toast.LENGTH_SHORT).show();
            return true;
        });

        trimiteListaInapoi();
    }

    private void trimiteListaInapoi() {
        Intent it = new Intent();
        it.putParcelableArrayListExtra(MainActivity.CHEIE_LISTA, lista);
        setResult(RESULT_OK, it);
    }
}