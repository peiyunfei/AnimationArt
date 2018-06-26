package com.pyf.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void secondBezier(View view) {
        startActivity(new Intent(this, SecondBezierActivity.class));
    }

    public void thirdBezier(View view) {
        startActivity(new Intent(this, ThirdBezierActivity.class));
    }

    public void drawPad(View view) {
        startActivity(new Intent(this, DrawPadActivity.class));
    }

    public void pathMorphing(View view) {
        startActivity(new Intent(this, PathMorphingActivity.class));
    }

    public void pathBezier(View view) {
        startActivity(new Intent(this, PathBezierActivity.class));
    }
}
