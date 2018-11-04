package com.example.listviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class makeOneTodoList extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeonetodolist);
        Button button_back = (Button) findViewById(R.id.button_back);
        Button button_yes = (Button) findViewById(R.id.button_yes);
        editText = (EditText) findViewById(R.id.edit_1) ;
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_yes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String data = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data_return", data);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
