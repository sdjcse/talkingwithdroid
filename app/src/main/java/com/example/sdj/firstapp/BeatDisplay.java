package com.example.sdj.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BeatDisplay extends Activity {


    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private LineGraphSeries<DataPoint> values;
    private int counter = 0;

    private Button runButton;
    private Button stopButton;
    private Thread keyThread;

    private float[] randList(int n){
        float retArr [] = new float[n];
        Random rand = new Random();
        for (int i=0;i<n;i++){
            retArr[i] = rand.nextFloat();
        }
        return retArr;
    }
    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beat_display);
        GraphView gv = (GraphView) findViewById(R.id.graph);
        values = new LineGraphSeries<DataPoint>();
        gv.addSeries(values);
        Viewport vp = gv.getViewport();
        vp.setYAxisBoundsManual(true);
        vp.setMinY(0);
        vp.setMaxY(10);
        vp.setScrollable(true);
        //vp.scrollToEnd();


        this.runListener();
        this.stopListener();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            appendValues();
                        }
                    });

                    try {
                        Thread.sleep(300);
                    }catch (InterruptedException e){
                        System.out.println("Caught an Exception!"+ e);
                    }
                }
            }
        }).start();

    }


    public void runListener(){
        runButton = (Button) findViewById(R.id.run_button);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BeatDisplay.this,"Run Pressed"+counter,Toast.LENGTH_SHORT).show();
                //keyThread.start();
            }
        });
    }

    public void stopListener(){
        stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //keyThread.interrupt();
            }
        });
    }

    private void appendValues(){
        float fl = new Random().nextFloat() * (10f);
        values.appendData(new DataPoint(counter++, fl),false,20);
    }

}
