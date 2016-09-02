package com.tp2assignment6.domaindesign.domain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ManageActivity extends AppCompatActivity {

    private Button createButton;
    private Button updateButton;
    private Button deleteButton;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        createButton = (Button) findViewById(R.id.createRecButton);
        updateButton = (Button) findViewById(R.id.updateRecButton);
        deleteButton = (Button) findViewById(R.id.deleteRecButton);
        closeButton = (Button) findViewById(R.id.closeButton);

        assert createButton != null;
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(intent);
            }
        });
        assert updateButton != null;
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });

        assert closeButton != null;
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
            }
        });
    }
}
