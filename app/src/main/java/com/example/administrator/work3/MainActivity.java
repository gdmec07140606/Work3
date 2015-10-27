package com.example.administrator.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button calculatorButton;
    private EditText weightEditText;
    private CheckBox manCB;
    private CheckBox womanCB;
    private TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorButton = (Button) findViewById(R.id.calculator);
        weightEditText = (EditText) findViewById(R.id.weight);
        manCB = (CheckBox) findViewById(R.id.man);
        womanCB =(CheckBox) findViewById(R.id.woman);
        resultTV = (TextView) findViewById(R.id.result);
    }
    protected  void onStart(){
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEditText.getText().toString().trim().equals("")) {
                    if (!manCB.isChecked() || womanCB.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("----------评估结果------------\n");
                        if (manCB.isChecked()) {
                            sb.append("男性标准身高");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "(cm)\n");
                        }
                        if (womanCB.isChecked()) {
                            sb.append("\n女性标准身高为：");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "(cm)");
                        }
                        resultTV.setText(sb.toString());
                    } else {
                        showMessage("请选择性别！");
                    }
                } else {
                    showMessage("请输入体重！");
                }
            }
        });



    }

    private void showMessage(String s) {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(s);
        alert.show();

    }

    private double evaluateHeight(Double weight, String sex) {
        double height;
        if(sex=="男"){
            height = 170-(62-weight)/0.6;

        }else{
            height = 158-(52-weight)/0.5;
        }


        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, 1, Menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
