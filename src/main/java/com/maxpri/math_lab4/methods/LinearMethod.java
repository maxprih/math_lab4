package com.maxpri.math_lab4.methods;

import com.maxpri.math_lab4.dto.Point;
import com.maxpri.math_lab4.math.ApproximationCounter;
import com.maxpri.math_lab4.math.GaussSystemSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author max_pri
 */
@Component
public class LinearMethod {

    private double SX;
    private double SY;
    private double SXX;
    private double SXY;

    @Autowired
    private GaussSystemSolver gauss;
    @Autowired
    private ApproximationCounter apprCounter;

    public List<List<Double>>  solve(List<Point> points) {
        int n = points.size();
        SX = 0;
        SY = 0;
        SXX = 0;
        SXY = 0;
        for (Point p : points) {
            double x = p.getX();
            double y = p.getY();
            SX += x;
            SY += y;
            SXX += x * x;
            SXY += x * y;
        }
        double[][] matrix = new double[2][3];
        matrix[0] = new double[]{SXX, SX, SXY};
        matrix[1] = new double[]{SX, n, SY};
        double[] answer = gauss.solve(matrix);
        double a = answer[0];
        double b = answer[1];
        List<List<Double>> res = new ArrayList<>();
        List<Double> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(apprCounter.calculateCorrelationCoefficient(points));
        list.add(apprCounter.count(points, (x -> a * x + b)));
        res.add(list);
        res.add(apprCounter.phis(points, (x -> a * x + b)));
        res.add(apprCounter.epsilons(points, (x -> a * x + b)));

        return res;
    }


}
