package com.example.mihaila_cristina_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroducereDate extends AppCompatActivity {

    private EditText marcaET;
    private EditText pretET;
    private RadioGroup tipRG;
    private CheckBox garantieCB;
    private Spinner procesorSpinner;
    private RatingBar ratingBar;
    private CompoundButton ssdSwitch;
    private Computer computerSelectat;
    private Button buttonAdauga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introducere_date);
        buttonAdauga = findViewById(R.id.buttonAdauga);

        // Identificam componentele vizuale
        marcaET = findViewById(R.id.marcaET);
        pretET = findViewById(R.id.pretET);
        tipRG = findViewById(R.id.tipRG);
        garantieCB = findViewById(R.id.garantieCB);
        procesorSpinner = findViewById(R.id.procesorSpinner);
        ratingBar = findViewById(R.id.ratingBar);
        ssdSwitch = findViewById(R.id.ssdSwitch);

        // Configuram Spinner-ul
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.procesoare,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        procesorSpinner.setAdapter(adapter);

        // Verificam daca am primit un computer pentru modificare
        computerSelectat =
                getIntent().getParcelableExtra("computer_selectat");

        if (computerSelectat != null) {

            // Completam marca
            marcaET.setText(computerSelectat.getMarca());

            // Completam pretul
            pretET.setText(String.valueOf(computerSelectat.getPret()));

            // Selectam tipul computerului
            for (int i = 0; i < tipRG.getChildCount(); i++) {
                RadioButton radioButton =
                        (RadioButton) tipRG.getChildAt(i);

                if (radioButton.getText().toString()
                        .equals(computerSelectat.getTip())) {

                    radioButton.setChecked(true);
                    break;
                }
            }

            if (computerSelectat != null) {
                buttonAdauga.setText("Salvează modificările");
            } else {
                buttonAdauga.setText("Adaugă computer");
            }

            // Completam garantia
            garantieCB.setChecked(computerSelectat.isAreGarantie());

            // Selectam procesorul
            for (int i = 0; i < procesorSpinner.getCount(); i++) {

                if (procesorSpinner.getItemAtPosition(i).toString()
                        .equals(computerSelectat.getProcesor())) {

                    procesorSpinner.setSelection(i);
                    break;
                }
            }

            // Completam rating-ul
            ratingBar.setRating(computerSelectat.getRating());

            // Completam informatia despre SSD
            ssdSwitch.setChecked(computerSelectat.isAreSsd());
        }

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }


    public void adaugaComputer(View view) {

        // 1. Preluăm valorile introduse în formular
        String marca = marcaET.getText().toString().trim();
        String pretText = pretET.getText().toString().trim();
        int idTip = tipRG.getCheckedRadioButtonId();

        // 2. Verificăm dacă au fost completate câmpurile obligatorii
        if (marca.isEmpty() || pretText.isEmpty() || idTip == -1) {
            Toast.makeText(this,
                    "Completează marca, prețul și tipul computerului!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Convertim prețul în număr
        double pret;

        try {
            pret = Double.parseDouble(pretText);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Prețul introdus nu este valid!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // 4. Preluăm restul valorilor din formular
        String tip = ((RadioButton) findViewById(idTip))
                .getText().toString();

        String procesor = procesorSpinner.getSelectedItem().toString();

        boolean garantie = garantieCB.isChecked();
        float rating = ratingBar.getRating();
        boolean ssd = ssdSwitch.isChecked();

        Computer computer;

        // 5. Verificăm dacă modificăm un computer existent
        if (computerSelectat != null) {

            // Actualizăm obiectul primit
            computerSelectat.setMarca(marca);
            computerSelectat.setProcesor(procesor);
            computerSelectat.setPret(pret);
            computerSelectat.setTip(tip);
            computerSelectat.setAreGarantie(garantie);
            computerSelectat.setRating(rating);
            computerSelectat.setAreSsd(ssd);

            computer = computerSelectat;

            Toast.makeText(this,
                    "Computer modificat cu succes!",
                    Toast.LENGTH_SHORT).show();

        } else {

            // Creăm un obiect nou
            computer = new Computer(
                    marca,
                    procesor,
                    pret,
                    tip,
                    garantie,
                    rating,
                    ssd
            );

            Toast.makeText(this,
                    "Computer adăugat cu succes!",
                    Toast.LENGTH_SHORT).show();
        }

        // 6. Trimitem computerul înapoi către ListaComputere
        Intent it = new Intent();
        it.putExtra(MainActivity.CHEIE_COMPUTER, computer);

        setResult(RESULT_OK, it);
        finish();
    }
}
