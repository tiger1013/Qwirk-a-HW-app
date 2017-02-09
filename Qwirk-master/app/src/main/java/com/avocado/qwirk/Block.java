package com.avocado.qwirk;

public class Block {
    int day;
    int startTime;
    int endTime;
    int color;

    public Block(int day, int startTime, int endTime, int color) {
        if ((0 <= day) && (day <= 7) && (0 <= startTime) && (startTime <= 48) && (startTime <= endTime) && (endTime <= 48)) {
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
            this.color = color;
        } else {
            throw new IllegalArgumentException("invalid block params: day (0-7), startTime (0-endTime), endTime(startTime-48)");
        }
    }

    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color){ this.color = color;}

}
