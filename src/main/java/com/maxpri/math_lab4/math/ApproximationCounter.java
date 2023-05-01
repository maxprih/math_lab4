package com.maxpri.math_lab4.math;

import com.maxpri.math_lab4.dto.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Component
public class ApproximationCounter {
    public double count(List<Point> points, Function<Double, Double> fi) {
        double s = 0;

        for (Point p : points) {
            s += Math.pow(fi.apply(p.getX()) - p.getY(), 2);
        }

        return Math.sqrt(s / points.size());
    }

    public List<Double> phis(List<Point> points, Function<Double, Double> fi) {
        List<Double> res = new ArrayList<>();

        for (Point p : points) {
            res.add(fi.apply(p.getX()));
        }

        return res;
    }

    public List<Double> epsilons(List<Point> points, Function<Double, Double> fi) {
        List<Double> res = new ArrayList<>();

        for (Point p : points) {
            res.add(fi.apply(p.getX()) - p.getY());
        }

        return res;
    }

    public double calculateCorrelationCoefficient(List<Point> points) {
        double sumX = 0;
        double sumY = 0;
        int n = points.size();

        for (Point p : points) {
            sumX += p.getX();
            sumY += p.getY();
        }

        double meanX = sumX / n;
        double meanY = sumY / n;

        double numerator = 0;
        double denominatorX = 0;
        double denominatorY = 0;

        for (Point p : points) {
            double deviationX = p.getX() - meanX;
            double deviationY = p.getY() - meanY;

            numerator += deviationX * deviationY;
            denominatorX += deviationX * deviationX;
            denominatorY += deviationY * deviationY;
        }

        double denominator = Math.sqrt(denominatorX) * Math.sqrt(denominatorY);
        double correlation = numerator / denominator;

        return correlation;
    }
}
