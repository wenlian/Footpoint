package com.lia.footprint.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lia.footprint.R;
import com.lia.footprint.model.Dot;
import com.lia.footprint.model.DotInfo;


public class Foot extends View {

    private final String TAG = "FootView";
    private DotInfo [] dotsInfo = {};

    Drawable mFootBg = null;
    int mFootBgWidth = 0;
    int mFootBgHeight = 0;

    boolean mAttached = false;
    private boolean mChanged;
    protected final Context mContext;

    /*public static final Dot DOT1= new Dot(0.1f,0.1f);
    public static final Dot DOT2= new Dot(0.8f,0.7f);
    public static final Dot DOT3= new Dot(0.5f,0.5f);
    public static final Dot DOT4= new Dot(0.3f,0.2f);
    public static final Dot DOT5= new Dot(0.7f,0.95f);
    DotInfo dotInfo1 = new DotInfo(DOT1, 50);
    DotInfo dotInfo2 = new DotInfo(DOT3, 100);
    DotInfo dotInfo3 = new DotInfo(DOT5, 80);
    DotInfo dotInfo4 = new DotInfo(DOT2, 60);
    DotInfo[] dotsInfo = {dotInfo1,dotInfo2,dotInfo3,dotInfo4};*/
    Paint mDotPaint = null;

    private PaintFlagsDrawFilter mPfd;

    public Foot(Context context) {
        this(context, null);
        //dotsInfo = context.
    }

    public Foot(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Foot(Context context, AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        mFootBg = context.getResources().getDrawable(R.drawable.foot_bg);

        mFootBgWidth = mFootBg.getIntrinsicWidth();
        mFootBgHeight = mFootBg.getIntrinsicHeight();

        Log.d(TAG, "mFootBgWidth:"+mFootBgWidth+";mFootBgHeight:"+mFootBgHeight);
        initDotPaint();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (!mAttached) {
            mAttached = true;

        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAttached) {
            mAttached = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize =  MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize =  MeasureSpec.getSize(heightMeasureSpec);

        float hScale = 1.0f;
        float vScale = 1.0f;

        if (widthMode != MeasureSpec.UNSPECIFIED && widthSize < mFootBgWidth) {
            hScale = (float) widthSize / (float) mFootBgWidth;
        }

        if (heightMode != MeasureSpec.UNSPECIFIED && heightSize < mFootBgHeight) {
            vScale = (float )heightSize / (float) mFootBgHeight;
        }

        float scale = Math.min(hScale, vScale);

        Log.d(TAG, "scale:"+scale);
        setMeasuredDimension(resolveSizeAndState((int) (mFootBgWidth * scale), widthMeasureSpec, 0),
                resolveSizeAndState((int) (mFootBgHeight * scale), heightMeasureSpec, 0));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mChanged = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        boolean changed = mChanged;
        if (changed) {
            mChanged = false;
        }

        int availableWidth = getWidth();
        int availableHeight = getHeight();

        int x = availableWidth / 2;
        int y = availableHeight / 2;

        int w = mFootBg.getIntrinsicWidth();
        int h = mFootBg.getIntrinsicHeight();

        boolean scaled = false;

        if (availableWidth < w || availableHeight < h) {
            scaled = true;
            float scale = Math.min((float) availableWidth / (float) w,
                                   (float) availableHeight / (float) h);
            canvas.save();
            canvas.scale(scale, scale, x, y);
        }

        if (changed) {
            mFootBg.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
        }
        mFootBg.draw(canvas);
        if (scaled) {
            canvas.restore();
        }

        if(dotsInfo != null && dotsInfo.length > 0) {
            for(int i = 0;i<dotsInfo.length;i++) {
                drawDot(canvas,dotsInfo[i],availableWidth,availableHeight);
            }
        }

    }


    private void initDotPaint() {
        //mPfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        mDotPaint = new Paint();
        mDotPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mDotPaint.setStrokeWidth(dp2Px(10));
    }

    static float dp2Px(int dp) {
        return Resources.getSystem().getDisplayMetrics().density * dp;
    }

    private void drawDot(Canvas canvas,DotInfo dotInfo,int width,int height) {
        Log.d(TAG, "width:"+width+";height:"+height+";dot:"+dotInfo.position);
        int xLocation = (int)(dotInfo.position.getX() * width);
        int yLocation = (int)(dotInfo.position.getY() * height);
        Log.d(TAG, "x:"+xLocation+";y:"+yLocation);
        int strokeWidthInDp = 1 +  (int)(dotInfo.pressure/10);
        float strokeWidthInPx = dp2Px(strokeWidthInDp);
        mDotPaint.setStrokeWidth(strokeWidthInPx);
        canvas.drawPoint(xLocation, yLocation, mDotPaint);
    }

    public void setDotsInfo(DotInfo[] dotInfo) {
        this.dotsInfo = dotInfo;
        invalidate();
    }
}
