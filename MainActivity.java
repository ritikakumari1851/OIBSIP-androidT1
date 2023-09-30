package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTv,solutionTv;
    MaterialButton buttonc,buttonbrackopen,buttonbrackclose;
    MaterialButton buttonplus,buttondivide,buttonminus,buttonmultiply,buttoneqals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAc,buttondot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);
        assignId(buttonc,R.id.button_C);
        assignId(buttonbrackopen,R.id.button_openbracket);
        assignId(buttonbrackclose,R.id.button_closebracket);
        assignId(buttonplus,R.id.button_plus);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonminus,R.id.button_minus);
        assignId(buttonmultiply,R.id.button_multiply);
        assignId(buttoneqals,R.id.button_equal);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAc,R.id.button_ac);
        assignId(buttondot,R.id.button_dot);
    }
    void assignId(MaterialButton btn,int id){
        btn= findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton)  view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutionTv.getText().toString();
        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            if (dataToCalculate.length() > 0) {
                StringBuilder sb = new StringBuilder(solutionTv.getText());
                sb.deleteCharAt(solutionTv.getText().length() - 1);
                dataToCalculate = sb.toString();
                solutionTv.setText(dataToCalculate);
                resultTv.setText(dataToCalculate);
            }
            return;
        }
        else{
            dataToCalculate=dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult= getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
    try {
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable = context.initStandardObjects();
        String finalResult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
        if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "");
        }
        return finalResult;
    }
    catch(Exception e){
        return "Err";

        }
    }
}