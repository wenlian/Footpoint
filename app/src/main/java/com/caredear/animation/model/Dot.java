package com.caredear.animation.model;

public class Dot {

    float x;
    float y;

    public Dot(){

    }

    public Dot(float x,float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "dot's position<"+x+","+y+">";
    }
}
