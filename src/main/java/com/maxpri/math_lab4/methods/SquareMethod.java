package com.maxpri.math_lab4.methods;

import com.maxpri.math_lab4.dto.Point;
import com.maxpri.math_lab4.math.ApproximationCounter;
import com.maxpri.math_lab4.math.GaussSystemSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SquareMethod {

    private double SX;
    private double SY;

    private double SXX;
    private double SXXX;
    private double SXXXX;

    private double SXY;
    private double SXXY;


    @Autowired
    private GaussSystemSolver gauss;
    @Autowired
    private ApproximationCounter apprCounter;

    public List<List<Double>> solve(List<Point> points) {
        int n = points.size();
        SX = 0;
        SY = 0;
        SXX = 0;
        SXXX = 0;
        SXXXX = 0;
        SXY = 0;
        SXXY = 0;
        for (Point p : points) {
            double x = p.getX();
            double y = p.getY();
            SX += x;
            SY += y;
            SXX += x * x;
            SXY += x * y;
            SXXX += x * x * x;
            SXXXX += x * x * x * x;
            SXXY += x * x * y;
        }

        double[][] matrix = new double[3][4];
        matrix[0] = new double[]{n, SX, SXX, SY};
        matrix[1] = new double[]{SX, SXX, SXXX, SXY};
        matrix[2] = new double[]{SXX, SXXX, SXXXX, SXXY};
        double[] answer = gauss.solve(matrix);


        double x0 = answer[0];
        double x1 = answer[1];
        double x2 = answer[2];

        List<List<Double>> res = new ArrayList<>();
        List<Double> list = new ArrayList<>(Arrays.asList(x0, x1, x2,
                apprCounter.count(points, x -> (x0 + x1 * x + x2 * x * x))));
        res.add(list);
        res.add(apprCounter.phis(points, x -> (x0 + x1 * x + x2 * x * x)));
        res.add(apprCounter.epsilons(points, x -> (x0 + x1 * x + x2 * x * x)));
        return res;
    }
}
