package com.example.mihaila_cristina_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaComputere extends AppCompatActivity {

    private ArrayList<Computer> lista;
    private ComputerAdapter adapter;
    private ActivityResultLauncher<Intent> editareLauncher;
    private int pozitieSelectata = -1;

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

        ListView listView = findViewById(R.id.listViewComputere);

        adapter = new ComputerAdapter(
                this,
                R.layout.item_computer,
                lista
        );

        listView.setAdapter(adapter);

        editareLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK
                            && result.getData() != null) {

                        Computer computerModificat;

                        if (android.os.Build.VERSION.SDK_INT >= 33) {
                            computerModificat = result.getData().getParcelableExtra(
                                    MainActivity.CHEIE_COMPUTER,
                                    Computer.class
                            );
                        } else {
                            computerModificat = result.getData().getParcelableExtra(
                                    MainActivity.CHEIE_COMPUTER
                            );
                        }

                        if (computerModificat != null
                                && pozitieSelectata >= 0
                                && pozitieSelectata < lista.size()) {

                            Computer computerOriginal = lista.get(pozitieSelectata);

                            computerOriginal.setMarca(computerModificat.getMarca());
                            computerOriginal.setProcesor(computerModificat.getProcesor());
                            computerOriginal.setPret(computerModificat.getPret());
                            computerOriginal.setTip(computerModificat.getTip());
                            computerOriginal.setAreGarantie(computerModificat.isAreGarantie());
                            computerOriginal.setRating(computerModificat.getRating());
                            computerOriginal.setAreSsd(computerModificat.isAreSsd());

                            adapter.notifyDataSetChanged();
                            trimiteListaInapoi();
                        }
                    }
                }
        );

        listView.setOnItemClickListener((parent, view, position, id) -> {

            pozitieSelectata = position;

            Computer computerSelectat = lista.get(position);

            Intent intent = new Intent(
                    ListaComputere.this,
                    IntroducereDate.class
            );

            intent.putExtra("computer_selectat", computerSelectat);

            editareLauncher.launch(intent);
        });

        // 4. Click lung -> stergere
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.remove(position);

            if (pozitieSelectata == position) {
                pozitieSelectata = -1;
            } else if (pozitieSelectata > position) {
                pozitieSelectata--;
            }

            adapter.notifyDataSetChanged();
            trimiteListaInapoi();

            Toast.makeText(
                    this,
                    "Computer sters",
                    Toast.LENGTH_SHORT
            ).show();

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