package com.lia.footprint.model;

public class DotInfo {

    public Dot position;
    public float pressure = 0f;

    public DotInfo(Dot dot,float pressure) {
        this.position = dot;
        if(pressure > 0) {
            this.pressure = pressure;
        }
    }
    public Dot getPosition() {
        return position;
    }

    public void setPosition(Dot position) {
        this.position = position;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

}
