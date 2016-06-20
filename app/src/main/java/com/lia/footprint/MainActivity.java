package com.lia.footprint;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.lia.footprint.model.ValueLinePoint;
import com.lia.footprint.model.ValueLineSeries;
import com.lia.footprint.util.FootPrintLog;
import com.lia.footprint.view.Foot;
import com.lia.footprint.view.ValueLineChart;

import com.lia.footprint.model.Dot;
import com.lia.footprint.model.DotInfo;

public class MainActivity extends Activity implements OnClickListener{

    private final String TAG = "Main_Activity";
    Context mContext;

    ValueLineChart mStatics = null;
    public static final Dot DOT1= new Dot(0.2f,0.3f);
    public static final Dot DOT2= new Dot(0.6f,0.3f);
    public static final Dot DOT3= new Dot(0.4f,0.9f);

    public static final Dot DOT4= new Dot(0.4f,0.3f);
    public static final Dot DOT5= new Dot(0.8f,0.3f);
    public static final Dot DOT6= new Dot(0.65f,0.9f);
    DotInfo dotInfo1 = new DotInfo(DOT1, 200);
    DotInfo dotInfo2 = new DotInfo(DOT2, 100);
    DotInfo dotInfo3 = new DotInfo(DOT3, 100);

    DotInfo dotInfo4 = new DotInfo(DOT4, 100);
    DotInfo dotInfo5 = new DotInfo(DOT5, 100);
    DotInfo dotInfo6 = new DotInfo(DOT6, 200);
    DotInfo[] dotsInfo = {dotInfo1,dotInfo2,dotInfo3};
    DotInfo[] dotsInfo1 = {dotInfo4,dotInfo5,dotInfo6};

    Foot mFootView;
    Foot lFootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        initResources();
    }

    //now we just show a demo
    private void updateValueLineChart() {
        SparseIntArray data = new SparseIntArray();
        data.append(0,10);
        data.append(1,30);
        data.append(2,80);
        data.append(3,60);
        data.append(4,70);
        data.append(5,10);
        data.append(6,100);
        data.append(7,90);
        data.append(8,85);
        data.append(9,95);
        data.append(10,50);
        data.append(11,60);


        FootPrintLog.i(TAG, "updateBarChart");
        mStatics.clearChart();
        ValueLineSeries series = new ValueLineSeries();
        series.setColor(mContext.getResources().getColor(android.R.color.white));
        for (int i = 0; i < data.size(); i++) {
            Integer valueInteger = data.get(i);
            int value = 0;
            if (null != valueInteger) {
                value = valueInteger.intValue();
            }
            ValueLinePoint point = new ValueLinePoint(i + "", value);
            if (i % 4 != 0) {
                point.setIgnore(true);
            }
            series.addPoint(point);
        }
        mStatics.addSeries(series);
        mStatics.startAnimation();
    }

    private void initResources() {
        mFootView = (Foot) findViewById(R.id.foot);
        lFootView = (Foot) findViewById(R.id.foot1);
        mFootView.setDotsInfo(dotsInfo);
        lFootView.setDotsInfo(dotsInfo1);
        //mFootView = new Foot(mContext,dotsInfo);
        //lFootView = new Foot(mContext,dotsInfo1);;
        mStatics = (ValueLineChart) findViewById(R.id.valueLineChart);
        updateValueLineChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}