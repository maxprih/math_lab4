package com.maxpri.math_lab4.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author max_pri
 */
@Getter
@Setter
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
