package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button a1,a2,a3,a4,a5,b1,b2,b3,b4,b5,c1,c2,c3,c4,c5,d1,d2,d3,d4,d5;
    TextView solution, result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(a1,R.id.a1);
        assignId(a2,R.id.a2);
        assignId(a3,R.id.a3);
        assignId(a4,R.id.a4);
        assignId(a5,R.id.a5);
        assignId(a1,R.id.b1);
        assignId(a2,R.id.b2);
        assignId(a3,R.id.b3);
        assignId(a4,R.id.b4);
        assignId(a5,R.id.b5);
        assignId(a1,R.id.c1);
        assignId(a2,R.id.c2);
        assignId(a3,R.id.c3);
        assignId(a4,R.id.c4);
        assignId(a5,R.id.c5);
        assignId(a1,R.id.d1);
        assignId(a2,R.id.d2);
        assignId(a3,R.id.d3);
        assignId(a4,R.id.d4);
        assignId(a5,R.id.d5);
    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }

        else if(buttonText.equals("=")){
            solution.setText(result.getText());
        }

        else if(buttonText.equals("C")){
            if(dataToCalculate.length()==0 || dataToCalculate.length()==1){
                result.setText("0");
            }
            else{
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }
        }


        else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }

        catch (Exception e){
            return "Err";
        }
    }
}