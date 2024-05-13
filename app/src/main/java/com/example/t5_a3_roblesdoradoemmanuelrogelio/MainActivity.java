package com.example.t5_a3_roblesdoradoemmanuelrogelio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private String operation,operationSave;
    private double num_uno,num_dos,resultado;
    private TextView caja_res,caja_historial;
    private  boolean operationRepit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        caja_res = findViewById(R.id.caja_txt);
        caja_historial = findViewById(R.id.caja_historial);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void mostrarNumeros(View v){
        String save_box = caja_res.getText().toString();
        Button btn = (Button)v; //Convierto el view a button para sacar el str
        String btnTxt = btn.getText().toString();

        //Toast.makeText(this, btnTxt, Toast.LENGTH_SHORT).show();
        caja_res.setText(save_box+btnTxt);
    }
    public void eliminarDatos(View v){

        if(v.getId() == R.id.btn_C){
            caja_res.setText(null);
        } else if (v.getId() == R.id.btn_CE) {
            caja_res.setText(null);
            caja_historial.setText(null);
            num_uno=0;
            num_dos=0;
            operation=null;

        } else if (v.getId() ==  R.id.btn_borrar_uno) {

            if (!caja_res.getText().toString().equals("")) {
                String completo = caja_res.getText().toString();

                int longitud = completo.length();

                caja_res.setText(completo.substring(0,(longitud-1)));
            }
        }
    }
    public void elegirOperaciones(View v){
        Button btnConvert = (Button)v;

        if (operation == null){

            if (!caja_res.getText().toString().equals("")){
                num_uno = Double.parseDouble(caja_res.getText().toString());

                operation = btnConvert.getText().toString();
                caja_res.setText(null);
            }else{
                Toast.makeText(this, "Ingresa un número", Toast.LENGTH_SHORT).show();
            }

        }else{
            if (!caja_res.getText().toString().equals("")){
                operationRepit=true;
                operationSave = btnConvert.getText().toString();
                resolverOperacion(v);

            }else{
                Toast.makeText(this, "Ingresa un número", Toast.LENGTH_SHORT).show();
            }

            operationRepit=false;
        }
    }
    public void resolverOperacion(View v){

        if(operation != null){


            if (!caja_res.getText().toString().equals("")){
                num_dos = Double.parseDouble(caja_res.getText().toString());

                switch (operation){

                    case "/":
                        resultado = num_uno/num_dos;
                        break;
                    case "*":
                        resultado = num_uno*num_dos;
                        break;
                    case "-":
                        resultado = num_uno-num_dos;
                        break;
                    case "+":
                        resultado = num_uno+num_dos;
                        break;

                }

                if (!operationRepit){
                    caja_res.setText(resultado+"");
                    caja_historial.setText(num_uno+" "+operation+" "+num_dos+" = "+resultado);
                    num_uno=0;
                    num_dos=0;
                    operation=null;
                }else{
                    num_uno = resultado;
                    num_dos=0;
                    caja_res.setText(null);
                    caja_historial.setText(resultado+operationSave);
                    operation=operationSave;
                }

            }else{
                Toast.makeText(this, "Ingresa un número", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void botonesRapidos(View v){

        if (!caja_res.getText().toString().equals("")){
            double fastNum = Double.parseDouble(caja_res.getText().toString());

            if(v.getId() == R.id.btn_Porcentaje){
                caja_res.setText(String.valueOf(fastNum/100));
            } else if (v.getId() == R.id.btn_raizCuadrada) {
                caja_res.setText(String.valueOf(Math.sqrt(fastNum)));
            } else if (v.getId() == R.id.btn_cuadrado) {
                caja_res.setText(String.valueOf(fastNum*fastNum));
            } else if (v.getId() == R.id.btn_uno_sobrex) {
                caja_res.setText(String.valueOf(1/fastNum));
            } else {
                caja_res.setText(String.valueOf(fastNum*(-1)));
            }
        }else{
            Toast.makeText(this, "Ingresa un número", Toast.LENGTH_SHORT).show();
        }

    }
    public void puntoDecimal(View v){
        String verificar = caja_res.getText().toString();

        if(verificar.indexOf('.') == (-1)){
            if(verificar.equals("")){
                caja_res.setText("0.");
            }else{
                caja_res.setText(verificar+".");
            }
        }

    }
}