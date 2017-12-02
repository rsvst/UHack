package com.han_app.yaguio.han_app;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rsvst on 02/12/2017.
 */

public class Play  extends Service{
    @Nullable


    public static boolean flashswitch = false;
    public static boolean soundswitch = false;
    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    Camera.Parameters params;
    MediaPlayer mp;



    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onCreate() {


        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            Toast.makeText(this, "Flash is not available for this device.", Toast.LENGTH_SHORT).show();
        }


        getCamera();

        final Handler handler = new Handler();
        final int delay = 200; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                if(flashswitch){
                    if(isFlashOn){
                        turnOffFlash();
                    }
                    else {
                        turnOnFlash();
                    }
                } else{
                    turnOffFlash();
                }

                if (soundswitch){
                    playSound();
                }


                handler.postDelayed(this, delay);
            }
        }, delay);
    }


    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {
                Log.d("Camera Error. Error: ", e.getMessage());
            }
        }
    }

    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;

        }

    }

    private void playSound(){
        mp = MediaPlayer.create(this, R.raw.dash);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.release();
            }
        });
        mp.start();
    }


    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
