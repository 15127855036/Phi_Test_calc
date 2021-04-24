package com.augtons.tjuttestc9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {
    Button skip = null;
    SkipThread skipThread = null;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        skip = findViewById(R.id.skipButton);

        skipThread = new SkipThread();
        skipThread.setTickListener(leftTime -> {
            if (leftTime == 0){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }else {
                skip.setText("0" + leftTime + " 跳过");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        skipThread.start();
    }

    public void skipIt(View view){
        skipThread.interrupt();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    private class SkipThread extends Thread{
        private TickListener tickListener;
        private int leftTime;
        @Override
        public void run() {
            int timeOut = 2;
            for (int i = 0; i< timeOut; i++){
                if (!this.isInterrupted()) {
                    leftTime = timeOut - i;
                    if (tickListener != null) {
                        handler.post(() -> {
                            tickListener.onTick(leftTime);
                        });
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                        this.interrupt();
                        break;
                    }
                }else {
                    break;
                }
            }
            if (!this.isInterrupted() & tickListener != null) {
                handler.post(() -> {
                    tickListener.onTick(0);
                });
            }

        }

        public void setTickListener(TickListener tickListener) {
            this.tickListener = tickListener;
        }
    }
    private interface TickListener{
        void onTick(int leftTime);
    }
}