
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

public class MainActivity extends Activity implements OnClickListener{

    private final String TAG = "Main_Activity";
    Context mContext;

    ValueLineChart mStatics = null;
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
