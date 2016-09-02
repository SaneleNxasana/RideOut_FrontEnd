package com.tp2assignment6.domaindesign.domain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UpdateActivity extends AppCompatActivity {

    private Button buttonUpdate;
    private Button buttonClose;

    public UpdateActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonClose = (Button) findViewById(R.id.buttonUpdClose);

        assert buttonUpdate != null;
        buttonUpdate.setOnClickListener(new View.OnClickListener(){
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
