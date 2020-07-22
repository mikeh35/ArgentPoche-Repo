package com.mikeh35.argentpochev1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textTotal;
    private  TextView textName;

    private DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textTotal = (TextView) findViewById(R.id.textTotal);
        final EditText textName = (EditText) findViewById(R.id.textName);

        Button btnGetTotal = findViewById(R.id.btnGetTotal);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnIncrement = findViewById(R.id.btnIncrement);
        Button btnDecrement = findViewById(R.id.btnDecrement);

        btnGetTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                int total = dataManager.getTotal(name);
                textTotal.setText(String.valueOf(total));
            }
        });

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                dataManager.insertArgent(name, 1);

                List<MontantData> montants = dataManager.readAll();
                textView.setText("");
                for (MontantData montant : montants) {
                    textView.append(montant.toString() + "\n");
                }

            }
        });

        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                dataManager.insertArgent(name, -1);

                List<MontantData> montants = dataManager.readAll();
                textView.setText("");
                for (MontantData montant : montants) {
                    textView.append(montant.toString() + "\n");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.deleteTable();
                textView.setText("");
            }
        });

        dataManager = new DataManager(this);

        dataManager.insertArgent("Bruno",1 );
        dataManager.insertArgent("Mike",1 );
        dataManager.insertArgent("Alain",1 );
        dataManager.insertArgent("JM",1 );



            List<MontantData> montants = dataManager.readAll();

            for (MontantData montant : montants) {
                textView.append(montant.toString() + "\n");
            }

        }
}