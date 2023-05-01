package com.maxpri.math_lab4.methods;

import com.maxpri.math_lab4.dto.Point;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PowerMethod {


    public List<List<Double>> solve(List<Point> points, LinearMethod linearMethod) {
        for (Point p : points) {
            double x = p.getX();
            double y = p.getY();
            if (y <= 0 || x <= 0) {
                return null;
            }
            p.setX(Math.log(x));
            p.setY(Math.log(y));
        }

        return linearMethod.solve(points);
    }
}
