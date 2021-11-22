package com.example.a4uzd_181rdb219;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
//import androidx.compose.ui.graphics.Matrix;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.Matrix;
import android.util.Log;

public class MansRenderetajs implements GLSurfaceView.Renderer {

    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private final float[] mvpMatrix = new float[16];
    private float mWidth = 0;
    private float mHeigt = 0;
    //private final float[] rotationMatrix = new float[16];

    private Trissturis mTrissturis;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        mTrissturis = new Trissturis();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0, width, height);
        mWidth = width;
        mHeigt = height;
        float ratio = (float) width/height;
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 20);

    }

    public static int loadShader(int type, String shaderCode){
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    protected float mPosZ;
    public void setmPosZ(float mPosZ) {this.mPosZ = mPosZ; }

    protected float mPosX;
    public void setmPosX(float mPosX) {
        mPosX = (float)(2.0f *   (mPosX/mWidth) - 1.0f) * -1f;
        this.mPosX = mPosX;
    }

    protected float mPosY;
    public void setmPosY(float mPosY) {
        mPosY = (float)(-2.0f * (mPosY / mHeigt) + 1.0f) ;
        this.mPosY = mPosY;
    }

    protected float mRotZ;
    public void setmRotZ(float mRotZ) {this.mRotZ = mRotZ; }

    public float getmRotZ(){
        return mRotZ;
    }


    protected float mRotX;
    public void setmRotX(float mRotX) {this.mRotX = mRotX; }

    public float getmRotX(){
        return mRotX;
    }



    @Override
    public void onDrawFrame(GL10 unused) {
        float[] scratch = new float[16];

        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        float[] moveMatrix = new float[16];
        Matrix.setIdentityM(moveMatrix, 0);
        //mPosX = mPosX * 2.0f / cam.GetScreenWidth();

        Log.d("X: ", String.valueOf(mPosX));
        Matrix.translateM(moveMatrix, 0, mPosX, mPosY, mPosZ);

        //Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, moveMatrix, 0);

        float[] horRotationMatrix = new float[16];
        Matrix.setRotateM(horRotationMatrix, 0, mRotZ, 0, 0, 1.0f);

        float[] verRotationMatrix = new float[16];
        Matrix.setRotateM(verRotationMatrix, 0, mRotX, 1.0f, 0, 0);

        float[] rotationMatrix = new float[16];
        Matrix.multiplyMM(rotationMatrix, 0, horRotationMatrix, 0, verRotationMatrix, 0);


        float[] modelMatrix = new float [16];
        Matrix.multiplyMM(modelMatrix, 0, moveMatrix, 0, rotationMatrix, 0);
        Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, modelMatrix, 0);

        mTrissturis.draw(scratch);
    }
}