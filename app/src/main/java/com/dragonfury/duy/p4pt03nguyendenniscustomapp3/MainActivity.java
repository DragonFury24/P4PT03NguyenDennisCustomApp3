package com.dragonfury.duy.p4pt03nguyendenniscustomapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        DrawView drawView = new DrawView(this);
        if (drawView.resetGame()){
            finish();
        }
    }
}

