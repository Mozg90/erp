package ru.smuit.erp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ERPActivity extends AppCompatActivity {
    EditText editTextD;
    EditText editTextSy;
    EditText editTextDadj;
    EditText editTextG;
    EditText editTextF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erp);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        editTextD = (EditText) findViewById(R.id.editTextD);
        editTextSy = (EditText) findViewById(R.id.editTextSy);
        editTextDadj = (EditText) findViewById(R.id.editTextDadj);
        editTextG = (EditText) findViewById(R.id.editTextG);
        editTextF = (EditText) findViewById(R.id.editTextF);
        builder.setCancelable(false)
                //.setIcon(R.drawable.ic_android_cat)
                .setNegativeButton("Закрыть",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emtyField = false;
                if(TextUtils.isEmpty(editTextD.getText().toString())) {
                    editTextD.setError("Заполните поле");
                    emtyField = true;
                }
                if(TextUtils.isEmpty(editTextSy.getText().toString())) {
                    editTextSy.setError("Заполните поле");
                    emtyField = true;
                }
                if(TextUtils.isEmpty(editTextDadj.getText().toString())) {
                    editTextDadj.setError("Заполните поле");
                    emtyField = true;
                }
                if(TextUtils.isEmpty(editTextG.getText().toString())) {
                    editTextG.setError("Заполните поле");
                    emtyField = true;
                }
                if(TextUtils.isEmpty(editTextF.getText().toString())) {
                    editTextF.setError("Заполните поле");
                    emtyField = true;
                }
                if (!emtyField) {
                    float erp = Float.parseFloat(editTextD.getText().toString()) * 0.061f +
                            Float.parseFloat(editTextSy.getText().toString()) * 0.034f -
                            Float.parseFloat(editTextDadj.getText().toString()) * 0.11f -
                            Float.parseFloat(editTextG.getText().toString()) * 0.151f +
                            Float.parseFloat(editTextF.getText().toString()) * 0.101f +
                            0.584f;
                    builder.setTitle("Результат: " + String.format("%.2f", erp));
                    if (erp >= 0.7f) {
                        if (erp > 1.0f) {
                            builder.setMessage("При ЭРП > 1 - положительный прогноз сохранения годовой ремисссии.");
                        } else {
                            builder.setMessage("При 0.7 < ЭРП < 1 - неустойчивый прогноз сохранения годовой ремисссии.");
                        }
                    } else {
                        builder.setMessage("При ЭРП < 0.7 - отрицательный прогноз, высокая вероятность срыва.");
                    }
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }
}
