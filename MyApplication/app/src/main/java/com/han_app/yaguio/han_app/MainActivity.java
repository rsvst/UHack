package com.han_app.yaguio.han_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Intent service = new Intent(MainActivity.this, Play.class);
        startService(service);

    }

    @Override
    protected void onDestroy() {
        Intent service = new Intent(MainActivity.this, Play.class);
        stopService(service);

        super.onDestroy();
    }

    private void init() {
        ImageButton search = (ImageButton) findViewById(R.id.search);
        ImageButton signal = (ImageButton) findViewById(R.id.signal);
        ImageButton setting = (ImageButton) findViewById(R.id.setting);
        final ImageButton sound = (ImageButton) findViewById(R.id.sound);
        final ImageButton flash = (ImageButton) findViewById(R.id.flash);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Search.class);
                startActivity(in);
            }
        });

        signal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Signal.class);
                startActivity(in);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Settings.class);
                startActivity(in);
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Play.soundswitch){
                    Play.soundswitch = false;
                    sound.setImageResource(R.drawable.sound);
                }
                else{
                    Play.soundswitch = true;
                    sound.setImageResource(R.drawable.soundon);
                }
            }
        });

        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Play.flashswitch) {
                    Play.flashswitch = false;
                    flash.setImageResource(R.drawable.light);
                }
                else{
                    Play.flashswitch = true;
                    flash.setImageResource(R.drawable.lighton);
                }

            }
        });
    }

}
