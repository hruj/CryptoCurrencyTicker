package com.example.student.simplelogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText name,pass;
    TextView t1;
    int count=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.BT);
        name=findViewById(R.id.NM);
        pass=findViewById(R.id.PW);
        t1=findViewById(R.id.TV);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("Arnesh")&& pass.getText().toString().equals("1234")){
                    Intent intent=new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                else if(name.getText().toString().equals("Hrushit")&&pass.getText().toString().equals("5678")){
                    Intent clickIntent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(clickIntent);
                }
                else if(name.getText().toString().equals("Shyam")&&pass.getText().toString().equals("@1234")){
                    Intent click = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(click);
                }
                else{
                    count--;
                    t1.setText(String.format("Attempts Remaining: %s", String.valueOf(count)));
                    if(count==0){b1.setEnabled(false);finish();}
                }
            }
        });
    }
}
