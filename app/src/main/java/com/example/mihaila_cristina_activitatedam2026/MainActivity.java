package com.example.mihaila_cristina_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String CHEIE_COMPUTER = "computer";

    private TextView rezultatTV;

    private final ActivityResultLauncher<Intent> launcherAdaugare =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Computer computer = bundle.getParcelable(CHEIE_COMPUTER);
                        if (computer != null) {
                            rezultatTV.setText(computer.toString());
                            Toast.makeText(this, computer.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        rezultatTV = findViewById(R.id.rezultatTV);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Activitatea de introducere date - varianta clasica (manuala)
    public void deschidereActivitate(View view) {
        launcherAdaugare.launch(new Intent(this, IntroducereDate.class));
    }

    // Activitatea de introducere date - varianta cu design realizat cu AI
    public void deschidereActivitateAI(View view) {
        launcherAdaugare.launch(new Intent(this, IntroducereDateAI.class));
    }
}
