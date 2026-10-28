package com.example.mihaila_cristina_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Aceeasi functionalitate ca IntroducereDate, dar cu design realizat cu ajutorul AI
 * (Material Design: carduri, TextInputLayout, butoane Material).
 */
public class IntroducereDateAI extends AppCompatActivity {

    private TextInputLayout marcaTIL;
    private TextInputLayout pretTIL;
    private TextInputEditText marcaET;
    private TextInputEditText pretET;
    private RadioGroup tipRG;
    private CheckBox garantieCB;
    private Spinner procesorSpinner;
    private RatingBar ratingBar;
    private CompoundButton ssdSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introducere_date_ai);

        marcaTIL = findViewById(R.id.marcaTIL);
        pretTIL = findViewById(R.id.pretTIL);
        marcaET = findViewById(R.id.marcaET);
        pretET = findViewById(R.id.pretET);
        tipRG = findViewById(R.id.tipRG);
        garantieCB = findViewById(R.id.garantieCB);
        procesorSpinner = findViewById(R.id.procesorSpinner);
        ratingBar = findViewById(R.id.ratingBar);
        ssdSwitch = findViewById(R.id.ssdSwitch);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.procesoare, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        procesorSpinner.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void adaugaComputer(View view) {
        marcaTIL.setError(null);
        pretTIL.setError(null);

        String marca = marcaET.getText() == null ? "" : marcaET.getText().toString().trim();
        String pretText = pretET.getText() == null ? "" : pretET.getText().toString().trim();
        int idTip = tipRG.getCheckedRadioButtonId();

        boolean valid = true;
        if (marca.isEmpty()) {
            marcaTIL.setError("Introduceti marca");
            valid = false;
        }
        double pret = 0;
        if (pretText.isEmpty()) {
            pretTIL.setError("Introduceti pretul");
            valid = false;
        } else {
            try {
                pret = Double.parseDouble(pretText);
            } catch (NumberFormatException e) {
                pretTIL.setError("Pret invalid");
                valid = false;
            }
        }
        if (idTip == -1) {
            Toast.makeText(this, "Alegeti tipul calculatorului", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (!valid) return;

        String tip = ((RadioButton) findViewById(idTip)).getText().toString();
        String procesor = procesorSpinner.getSelectedItem().toString();

        Computer computer = new Computer(marca, procesor, pret, tip,
                garantieCB.isChecked(), ratingBar.getRating(), ssdSwitch.isChecked());

        Toast.makeText(this, "Obiectul Computer a fost creat!", Toast.LENGTH_SHORT).show();

        Intent it = new Intent();
        it.putExtra(MainActivity.CHEIE_COMPUTER, computer);
        setResult(RESULT_OK, it);
        finish();
    }
}
