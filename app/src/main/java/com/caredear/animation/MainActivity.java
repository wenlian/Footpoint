
package com.caredear.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

    private final String TAG = "Animation";
    TextView mTextView = null;
    Button mButton = null;
    Button mBallButton = null;
    Button mBallButton2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextView = (TextView) findViewById(R.id.textView);
        //mTextView.setOnClickListener(this);
//        mTextView.animate().alpha(0.5f);
//        mButton = (Button) findViewById(R.id.btn);
//        mButton.setOnClickListener(this);
//        mBallButton = (Button) findViewById(R.id.ball);
//        mBallButton.setOnClickListener(this);
//        mBallButton2 = (Button) findViewById(R.id.ball2);
//        mBallButton2.setOnClickListener(this);
//        new AlphaAnimation(1.0f, 0.2f);
    }

    private void playAlphaAnimation() {
        ObjectAnimator objAni = ObjectAnimator.ofFloat(mTextView, "alpha", 0f,1f);
        objAni.setDuration(5000);
        objAni.start();
    }

   void scaleY() {
       ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "y", 0.2f);
       animator.setDuration(1000);
       animator.start();
   }

    void playScaleAnimation() {
        int originalWidth = mButton.getWidth();
        Keyframe kf0 = Keyframe.ofInt(0, 400);
        Keyframe kf1 = Keyframe.ofInt(0.25f, 200);
        Keyframe kf2 = Keyframe.ofInt(0.5f, 400);
        Keyframe kf4 = Keyframe.ofInt(0.75f, 100);
        Keyframe kf3 = Keyframe.ofInt(1f, originalWidth);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("width", kf0, kf1, kf2, kf4, kf3);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(mButton, pvhRotation);
        rotationAnim.setDuration(2000);
        rotationAnim.start();
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
        if(view == mTextView) {
            //playAlphaAnimation();
            scaleY();
        } else if (view == mButton) {
            playScaleAnimation();
        } else if (view == mBallButton || view == mBallButton2) {
            verticalRun();
        }
        
    }

    private void verticalRun() {
        final float startY = 0f;
        final float endY = 500f;
        final float h = endY - startY;
        ValueAnimator animator = ValueAnimator.ofFloat(startY,endY);//h = 100
        animator.setDuration(2000);
        animator.setTarget(mBallButton);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float fraction = valueAnimator.getAnimatedFraction();
                mBallButton.setTranslationY(fraction* fraction * h);
                mBallButton.setTranslationX(fraction* 200);

            }
        });

        animator.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onAnimationRepeat(Animator animator) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onAnimationEnd(Animator animator) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onAnimationCancel(Animator animator) {
                // TODO Auto-generated method stub
                
            }
        });

        float btn2X = mBallButton2.getX();
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBallButton2, "scaleX", 1.0f,1.5f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBallButton2, "scaleY", 1.0f,1.5f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBallButton2, "x", btn2X,btn2X * 4);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBallButton2, "x",btn2X* 4,btn2X);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(anim4).after(anim3);
        //animatorSet.play(anim1).with(anim2);
        //animatorSet.setDuration(3000);
//        ValueAnimator animator1 = ValueAnimator.ofFloat(startY,endY);//h = 100
//        animator1.setDuration(2000);
//        animator1.setTarget(mBallButton2);
//        animator1.addUpdateListener(new AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//
//                float currentY = ((Float)valueAnimator.getAnimatedValue()).floatValue();
//                mBallButton2.setTranslationY(currentY);
//
//            }
//        });
        animator.start();
        animatorSet.start();
    }

}
