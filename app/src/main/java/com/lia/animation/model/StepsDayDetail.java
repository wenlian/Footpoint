package com.lia.animation.model;

public class StepsDayDetail {

    private  String id;
    private  String stepid;
    private  String start_time;
    private  String end_time;
    private  String hour;
    //how many steps since last sampling
    private  String steps;

    //0 for add ,1 for updated,2 for uploaded
    public StepsDayDetail (String id,String dayId,String startTime,String endTime,String hour,String steps){
        this.id = id;
        this.stepid = dayId;
        this.start_time = startTime;
        this.end_time = endTime;
        this.hour = hour;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepid() {
        return stepid;
    }

    public void setStepid(String stepid) {
        this.stepid = stepid;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

}