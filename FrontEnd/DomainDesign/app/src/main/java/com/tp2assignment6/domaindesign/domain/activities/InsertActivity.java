package com.tp2assignment6.domaindesign.domain.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InsertActivity extends AppCompatActivity {

    private Button buttonSubmit;
    private Button buttonClose;

    public InsertActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonClose = (Button) findViewById(R.id.buttonInsClose);

        assert buttonSubmit != null;
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ManageActivity.class);
                startActivity(intent);
            }
        });
        assert buttonClose != null;
        buttonClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
            }
        });
    }
}
