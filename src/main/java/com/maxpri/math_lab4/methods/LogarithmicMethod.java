package com.maxpri.math_lab4.methods;

import com.maxpri.math_lab4.dto.Point;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogarithmicMethod {
    public List<List<Double>> solve(List<Point> points, LinearMethod linearMethod) {
        for (Point p: points){
            double x = p.getX();
            if (x <= 0){
                return null;
            }
            p.setX(Math.log(x));
        }

        return linearMethod.solve(points);
    }
}
