package com.example.a4uzd_181rdb219;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class Mana3dVirsma extends GLSurfaceView {

    private MansRenderetajs mansRenderetajs;

    public Mana3dVirsma(Context context){
        super(context);
        init();
    }

    public Mana3dVirsma(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        mansRenderetajs = new MansRenderetajs();
        setRenderer(mansRenderetajs);
        // Render the view only when there is a change in the drawing data
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private float prevX, prevY;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;


    @Override
    public boolean onTouchEvent(MotionEvent e){

        float x = e.getX();
        float y = e.getY();



        switch(e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mansRenderetajs.setmPosX((float) (x));
                mansRenderetajs.setmPosY((float) (y));
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - prevX;
                mansRenderetajs.setmRotZ(mansRenderetajs.getmRotZ() + dx*0.3f);

                float dy = y - prevY;
                mansRenderetajs.setmRotX(mansRenderetajs.getmRotX() + dy*0.3f);
                break;

        }
        prevX = x;
        prevY = y;
        return true;
    }
}