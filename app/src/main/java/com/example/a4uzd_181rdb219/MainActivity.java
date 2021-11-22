package com.example.a4uzd_181rdb219;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mana3dVirsma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mana3dVirsma = new Mana3dVirsma(this);
        setContentView(mana3dVirsma);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mana3dVirsma.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mana3dVirsma.onPause();
    }
}