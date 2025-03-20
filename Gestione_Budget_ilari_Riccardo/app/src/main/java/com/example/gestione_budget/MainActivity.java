package com.example.gestione_budget;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{

    double budget = 0;

    double[] valori = {0,0,0,0}; //Cibo, Trasporto, Altro, Ricavo
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.tBudget).setVisibility(View.VISIBLE);
        findViewById(R.id.bBudget).setVisibility(View.VISIBLE);

        findViewById(R.id.bCibo).setVisibility(View.GONE);
        findViewById(R.id.bTrasporto).setVisibility(View.GONE);
        findViewById(R.id.bAltro).setVisibility(View.GONE);
        findViewById(R.id.bRicavo).setVisibility(View.GONE);

        findViewById(R.id.tDescrizione).setVisibility(View.GONE);
        findViewById(R.id.tImporto).setVisibility(View.GONE);
        findViewById(R.id.bAdd).setVisibility(View.GONE);

        findViewById(R.id.tResult).setVisibility(View.VISIBLE);
    }

    public void bImposta_Click(View view)
    {
        try
        {
            EditText editText = findViewById(R.id.tBudget);
            budget = Double.parseDouble(editText.getText().toString());
            editText.setText("");

            editText.setVisibility(View.GONE);
            findViewById(R.id.bBudget).setVisibility(View.GONE);

            findViewById(R.id.bCibo).setVisibility(View.VISIBLE);
            findViewById(R.id.bTrasporto).setVisibility(View.VISIBLE);
            findViewById(R.id.bAltro).setVisibility(View.VISIBLE);
            findViewById(R.id.bRicavo).setVisibility(View.VISIBLE);

            AggiornaResult();
        } catch (Exception e) { }
    }

    public void bNew_Click(View view)
    {
        Button b = (Button)view;

        switch(b.getText().toString())
        {
            case "Cibo":
                index = 0;
                break;
            case "Trasporto":
                index = 1;
                break;
            case "Altro":
                index = 2;
                break;
            case "Ricavo":
                index = 3;
        }

        EditText descrizione = findViewById(R.id.tDescrizione);
        EditText importo = findViewById(R.id.tImporto);
        Button bAdd = findViewById(R.id.bAdd);

        descrizione.setVisibility(View.VISIBLE);
        importo.setVisibility(View.VISIBLE);
        bAdd.setVisibility(View.VISIBLE);
    }

    public void bAdd_Click(View view)
    {
        try
        {
            EditText descrizione = findViewById(R.id.tDescrizione);
            EditText importo = findViewById(R.id.tImporto);

            double i = Double.parseDouble(importo.getText().toString());
            valori[index] += i;

            descrizione.setVisibility(View.GONE);
            importo.setVisibility(View.GONE);
            findViewById(R.id.bAdd).setVisibility(View.GONE);

            descrizione.setText("");
            importo.setText("");

            AggiornaResult();
        }
        catch(Exception e) { }
    }

    void AggiornaResult()
    {
        TextView result = findViewById(R.id.tResult);
        String t = "";
        t += "Budget iniziale: " + budget + "\n";
        t += "Spese e ricavi:\n";
        t += "Cibo: " + valori[0] + "\n";
        t += "Trasporto: " + valori[1] + "\n";
        t += "Altro: " + valori[2] + "\n";
        t += "Ricavo: " + valori[3] + "\n";
        t += "Totale: " + (budget - valori[0] - valori[1] - valori[2] + valori[3]);

        if(budget - valori[0] - valori[1] - valori[2] + valori[3] < 0)
        {
            findViewById(R.id.bCibo).setEnabled(false);
            findViewById(R.id.bTrasporto).setEnabled(false);
            findViewById(R.id.bAltro).setEnabled(false);

            t += "\nAttenzione! Il budget è scaduto!";
        }
        else
        {
            findViewById(R.id.bCibo).setEnabled(true);
            findViewById(R.id.bTrasporto).setEnabled(true);
            findViewById(R.id.bAltro).setEnabled(true);
        }

        result.setText(t);
    }
}