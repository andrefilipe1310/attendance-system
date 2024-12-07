package com.etepam.attendance_system.domain.model.student;


import lombok.Getter;

@Getter
public class FaceEntity {
    private int x;
    private int y;
    private int width;
    private int height;
    private double ratio;

    public FaceEntity(int x, int y, int width, int height, double ratio) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "FaceEntity{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", ratio=" + ratio +
                '}';
    }
}