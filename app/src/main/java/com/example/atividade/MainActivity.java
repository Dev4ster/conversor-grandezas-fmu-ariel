package com.example.atividade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText potencia, tensao, corrente , resistencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        potencia = findViewById(R.id.potencia);
        tensao = findViewById(R.id.tensao);
        corrente = findViewById(R.id.corrente);
        resistencia = findViewById(R.id.resistencia);
    }


    private String formatToString(EditText editText){
        return editText.getText().toString();
    }

    private Double formatToDouble(String string){
        return Double.parseDouble(string);
    }

    private boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length() == 0;
    }

    public void reset(View view){
        potencia.setText("");
        corrente.setText("");
        tensao.setText("");
        resistencia.setText("");
    }

    public void helper(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://arieldias.com/novo/material/2019-2/PDM/Atividade3.pdf"));
        startActivity(browserIntent);
    }

    public void calc(View view) {
        /*
        combinações:

            Potência - Tensão
            Potência - Corrente
            Potência - Resistência

            Tensão - Corrente
            Tensão - Resistência

            Corrente - Resistência
        */
        String txtPotencia = formatToString(potencia);
        String txtTensao = formatToString(tensao);
        String txtCorrente = formatToString(corrente);
        String txtResistencia = formatToString(resistencia);

        double res1; // resultado 1
        double res2; // resultado 2
        double aux; // variável auxiliar


        // se todos estiverem vazios
        if(isEmpty(tensao) && isEmpty(potencia) && isEmpty(corrente) && isEmpty(resistencia)){
            Toast.makeText(MainActivity.this,
                    "Erro Campos vazios!", Toast.LENGTH_LONG).show();
            return;
        }

        //  informando ->  V R
        if(!isEmpty(tensao) && !isEmpty(resistencia) && (isEmpty(potencia) && isEmpty(corrente))){
            //potência = tencao*2/resistencia
             aux = Math.pow(Double.parseDouble(txtTensao),2);
            res1 = aux/Double.parseDouble(txtResistencia);
            potencia.setText(""+res1);
            //corrente = tencao/resistencia
            res2 = Double.parseDouble(txtTensao) / Double.parseDouble(txtResistencia);
            corrente.setText(""+res2 );
            return;
        }

        // informando -> V I
        if(!isEmpty(tensao) && !isEmpty(corrente) && (isEmpty(potencia) && isEmpty(resistencia))){
            //potência = tencao * corrente
            res1 = Double.parseDouble(txtTensao) * Double.parseDouble(txtCorrente);
            potencia.setText(""+res1);
            //resistencia = tencao/corrente
            res2 = Double.parseDouble(txtTensao) / Double.parseDouble(txtCorrente);
            resistencia.setText(""+res2 );
            return;
        }

        //  informando -> P V
        if(!isEmpty(potencia) && !isEmpty(tensao) && (isEmpty(corrente) && isEmpty(resistencia))){
            //Resistência = v^2/p
            aux = Math.pow(formatToDouble(txtTensao),2);
            res1 =  aux / formatToDouble(txtPotencia);
            resistencia.setText(""+res1);
            //corrente = p/v
            res2 = formatToDouble(txtPotencia) / formatToDouble(txtTensao);
            corrente.setText(""+res2 );
            return;
        }

        //  informando -> P I
        if(!isEmpty(potencia) && !isEmpty(corrente) && (isEmpty(tensao) && isEmpty(resistencia))){
            //Tensão = p/i
            res1 =   formatToDouble(txtPotencia) / formatToDouble(txtCorrente);
            tensao.setText(""+res1);
            //resistência = p/i^2
            aux  = Math.pow(formatToDouble(txtCorrente),2);
            res2 = formatToDouble(txtPotencia) / aux;
            resistencia.setText(""+res2 );
            return;
        }

        //  informando -> P R
        if(!isEmpty(potencia) && !isEmpty(resistencia) && (isEmpty(tensao) && isEmpty(corrente))){
            //Tensão = raiz de p*r
            aux = formatToDouble(txtPotencia) * formatToDouble(txtResistencia);
            res1 =   Math.sqrt(aux);
            tensao.setText(""+res1);

            //corrente = raiz de p/r
            aux  = formatToDouble(txtPotencia) / formatToDouble(txtResistencia);
            res2 = Math.sqrt(aux);
            corrente.setText(""+res2 );
            return;
        }

        //  informando -> I R
        if(!isEmpty(corrente) && !isEmpty(resistencia) && (isEmpty(tensao) && isEmpty(potencia))){
            //Tensão = r * i
            res1 =   formatToDouble(txtResistencia) * formatToDouble(txtCorrente);
            tensao.setText(""+res1);

            //potencia = r * i ^2
            aux  = Math.pow(formatToDouble(txtCorrente),2);
            res2 = formatToDouble(txtResistencia) * aux;
            potencia.setText(""+res2 );
            return;
        }

        // caso não entre em nenhum if
        Toast.makeText(MainActivity.this,
                "Verifique se existem dois campos preeenchidos", Toast.LENGTH_LONG).show();
    }


}
