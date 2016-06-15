package com.lia.animation.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.BaseColumns;

import com.lia.animation.util.PedoLog;

public class Pedometer {

    final String TAG = "Pedometer";
    //the max value we can read from pedometer_counter is 0x7FFF
    public static final int MAX_STEPS_CAN_READ = 0x7FFF;
    private int mWorkStatus = WORKING_STATUS_STOP;
    private long mStepCounter = 0;
    private int mMode = MODE_UNKNOWN;

    public static final int MODE_UNKNOWN = -1;
    public static final int MODE_WALKING = 0;
    public static final int MODE_RUNNING = 1;

    public static final int WORKING_STATUS_UNKONWN = -1;
    public static final int WORKING_STATUS_WORK = 1;
    public static final int WORKING_STATUS_PAUSE = 2;
    public static final int WORKING_STATUS_STOP = 3;
    public static final int WORKING_STATUS_RESTART = 4;

    public static final int DATA_UPDATE = 1;
    public static final int WORK_STATUS_CHANGED = 2;
    public static final int GOAL_UPDATE = 3;
    private long mElapsedStartTime = 0;
    private long mSysStartTime = 0;
    private long mStopTime = 0;
    private long mElapsedStopTime = 0;
    //private long mClearedSteps = 0;
    private long mLastStepsReadFromPedo = 0;
    private long mTodayExtraSteps = 0;
    private long mTodayExtraDuration = 0;

    //
    private long mObsoleteSteps = 0;
    //ADD GOAL
    public static final int INVALID_GOAL = -1;
    public static final int DEFAULT_GOAL = 8000;
    public static final int GOAL_MIN_VALUE = 0;
    public static final int GOAL_MAX_VALUE = 99999;
    public static final String PREF_NAME_PROFILE = "pedometer_profile";
    public static final String PREF_PROFILE_KEY_START_WHEN_POWER_ON = "pedometer_profile_start_when_power_on";
    public static final String PREF_PROFILE_KEY_GOAL = "pedometer_profile_goal";
    public static final String PREF_PROFILE_KEY_UPLOAD_FAIL_TOEKN_EXPIRED = "upload_fail_token_expired";
    //personal information
    public static final String PREF_NAME_PERSONAL_INFO = "pedometer_personal_info";
    public static final String PREF_PERSONAL_INFO_KEY_BIRTHDAY = "birthday";
    public static final String PREF_PERSONAL_INFO_KEY_NICKNAME = "nickname";
    public static final String PREF_PERSONAL_INFO_KEY_SEX = "sex";
    public static final String PREF_PERSONAL_INFO_KEY_HEIGHT = "height";
    public static final String PREF_PERSONAL_INFO_KEY_WEIGHT = "weight";
    public static final String PREF_PERSONAL_INFO_KEY_SUGGESTED_GOAL = "suggested_goal";
    public static final String PREF_PERSONAL_INFO_KEY_STRIDE = "step_size";
    //the following preference is for step count
    public static final String PREF_NAME_TIME = "pedometer";
    public static final String PREF_KEY_ELAPSED_START_TIME = "elapsed_start_time";
    public static final String PREF_KEY_SYS_START_TIME = "sys_start_time";
    public static final String PREF_NAME_STEPS = "pedometer_steps";
    public static final String PREF_KEY_OBSELETE_STEPS = "obselete_steps";
    //the max value we can stored in pedometer_counter is 0x7FFF
    //so we should restart pedometer every half an hour (1 or 2 hours is also OK)
    public static final String PREF_KEY_CLEARED_STEPS = "cleared_steps";


    public static final int REQEUST_ID_DAY_STORAGE = 0;
    public static final int REQEUST_ID_PERIOD_STORAGE = 1;
    public static final int REQEUST_ID_PEDO_RESTART = 2;
    public static final int REQEUST_ID_PEDO_STATUS_NOTI = 100;

    public static final String ACTION_STOP_PEDO = "com.caredear.pedometer.stoppedo";
    public static final String ACTION_PEDOMETER_WORKSTATE_CHANGE = "com.caredear.pedometer.workstatechanged";
    Context mContext;
    OnWorkStatusChangeListener mOnWorkStatusChangeListener = null;

    public Pedometer(Context context){
        mContext = context;
        resetData();
     }

    public void setWorkStatus(int status){
        PedoLog.i(TAG,"setPedometer status:"+status);
        if(mWorkStatus == status){
            PedoLog.i(TAG,"same status,ignore");
            return;
        }
        mWorkStatus = status;
        if(null != mOnWorkStatusChangeListener){
            mOnWorkStatusChangeListener.onPedoWorkStatusChange(mWorkStatus);
        }
    }


    public void resetData(){
        mWorkStatus = WORKING_STATUS_UNKONWN;
        mStepCounter = 0;
        mElapsedStartTime = 0;
        mSysStartTime = 0;
        mStopTime = 0;
        //mClearedSteps = 0;
        mTodayExtraSteps = 0;
        mTodayExtraDuration = 0;
        mLastStepsReadFromPedo = 0;
        mMode = MODE_UNKNOWN;
        mObsoleteSteps = 0;
    }

    public void setStepCounter(long counter){
        mStepCounter = counter;
    }

    public long getStepCounter(){
        if(mWorkStatus == WORKING_STATUS_UNKONWN){
            return 0;
        }
        return mStepCounter;
    }

    public void setMode(int mode){
        mMode = mode;
    }

    public boolean isPedometerRunning(){
        return mWorkStatus == WORKING_STATUS_WORK;
    }

    public int getWorkingStatus(){
        return mWorkStatus;
    }

    public int getMode(){
        return mMode;
    }


    public long getWorkingDuration(){
        if(isPedometerRunning()){
            return SystemClock.elapsedRealtime() - mElapsedStartTime;
        } else if (mElapsedStopTime > mElapsedStartTime){
            return mElapsedStopTime - mElapsedStartTime;
        }
        return 0;
    }

    public void setSysStartTime(long startTime){
        mSysStartTime = startTime;
    }

    public long getSysStartTime(){
        return mSysStartTime;
    }

    public void setElapsedStartTime(long startTime){
        mElapsedStartTime = startTime;
    }

    public long getElapsedStartTime(){
        return mElapsedStartTime;
    }

    public void setStopTime(long stopTime){
        mStopTime = stopTime;
    }

    public long getStopTime(){
        return mStopTime;
    }

    public void setElapsedStopTime(long elapsedTime){
        mElapsedStopTime = elapsedTime;;
    }

    public long getElapsedStopTime(){
        return mElapsedStopTime;
    }

    public void setLastStepsReadFromPedo(long steps) {
        mLastStepsReadFromPedo = steps;
    }

    public long getLastStepsReadFromPedo(){
        return mLastStepsReadFromPedo;
    }

    public void setOnWorkStatusChangeListener(OnWorkStatusChangeListener l){
        if(null != l){
            mOnWorkStatusChangeListener = l;
        }else {
            mOnWorkStatusChangeListener = null;
        }
    }

    /**
    public void addClearedSteps(long steps){
        mClearedSteps += steps;
    }

    public long getClearedSteps(){
        return mClearedSteps;
    }
    */

    public long getObsoleteSteps() {
        return mObsoleteSteps;
    }

    public void setObsoleteSteps(long obsoleteSteps) {
        mObsoleteSteps = obsoleteSteps;
    }
    //steps except this time
    public void setTodayExtraSteps(long steps){
        mTodayExtraSteps = steps;
    }

    //duration except this time
    public void setTodayExtraDuration(long duration){
        mTodayExtraDuration = duration;
    }

    //steps except this time
    public long getTodayExtraSteps(){
        return mTodayExtraSteps;
    }

    //duration except this time
    public long getTodayExtraDuration(){
        return mTodayExtraDuration;
    }

    public class PedoWorkInfo {
       public int workStatus = WORKING_STATUS_UNKONWN;
       public long stepCount = 0;
       public int mode = MODE_UNKNOWN;
    }
 
    public static class Columns implements BaseColumns{

        public static class StepColumn implements BaseColumns{
            public static final Uri CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/step");
            public static final Uri LASTEST_DAY_CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/lastest/0");
            public static final Uri LASTEST_WEEK_CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/lastest/1");
            public static final Uri LASTEST_MONTH_CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/lastest/2");
            public static final String _ID = "_id";
            //public static final String START_TIME = "start_time";
            //public static final String END_TIME = "end_time";
            public static final String DURATION = "duration";
            public static final String YEAR = "year";
            public static final String MONTH = "month";
            public static final String DAY = "day";
            public static final String WEEK = "week";
            //how many steps since last sampling
            public static final String STEPS = "steps";
            public static final String GOAL = "goal";
        }

        public static class DetailColumn implements BaseColumns{
            public static final Uri CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/detail");
            public static final Uri CONTENT_URI_DAY_DETAIL =
                    Uri.parse("content://com.caredear.pedometer/daydetail/");
            /**
            public static final Uri LASTEST_DAY_CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/lastest/0");
            public static final Uri LASTEST_WEEK_CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/lastest/1");
            public static final Uri LASTEST_MONTH_CONTENT_URI =
                    Uri.parse("content://com.caredear.pedometer/lastest/2");
                    */
            public static final String _ID = "_id";
            public static final String DAY_ID = "dayId";
            public static final String START_TIME = "start_time";
            public static final String END_TIME = "end_time";
            public static final String HOUR = "hour";
            //how many steps since last sampling
            public static final String STEPS = "steps";
            public static final String UPDATED = "updated";//for 1 true,0 for false
        }

    }
    public interface OnWorkStatusChangeListener{
        public abstract void onPedoWorkStatusChange(int status);
    }
}