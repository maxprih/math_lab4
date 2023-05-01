package com.maxpri.math_lab4.service;

import com.maxpri.math_lab4.dto.Answer;
import com.maxpri.math_lab4.dto.Point;
import com.maxpri.math_lab4.methods.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author max_pri
 */
@Component
public class MathService {

    @Autowired
    private LinearMethod linearMethod;
    @Autowired
    private SquareMethod squareMethod;
    @Autowired
    private CubicMethod cubicMethod;
    @Autowired
    private ExponentialMethod exponentialMethod;
    @Autowired
    private LogarithmicMethod logarithmicMethod;
    @Autowired
    private PowerMethod powerMethod;

    public Answer solve(List<Point> points) {
        List<Point> copyPoints = new ArrayList<>();
        for (Point point : points) {
            copyPoints.add(new Point(point.getX(), point.getY()));
        }
        List<List<Double>> linearList = linearMethod.solve(points);

        double linearSredOtkl = linearList.get(0).remove(linearList.get(0).size() - 1);
        double pirson = linearList.get(0).remove(linearList.get(0).size() - 1);

        List<Double> linear = linearList.get(0);
        List<Double> linearPhis = linearList.get(1);
        List<Double> linearEps = linearList.get(2);
        List<List<Double>> squareList = squareMethod.solve(points);

        double squareSredOtkl = squareList.get(0).remove(squareList.get(0).size() - 1);

        List<Double> square = squareList.get(0);
        List<Double> squarePhis = squareList.get(1);
        List<Double> squareEps = squareList.get(2);

        List<List<Double>> cubicList = cubicMethod.solve(points);

        double cubicSredOtkl = cubicList.get(0).remove(cubicList.get(0).size() - 1);

        List<Double> cubic = cubicList.get(0);
        List<Double> cubicPhis = cubicList.get(1);
        List<Double> cubicEps = cubicList.get(2);


        List<Double> exponential;
        Double expSredOtkl;
        List<Double> expPhis;
        List<Double> expEps;
        List<List<Double>> exponentialList = exponentialMethod.solve(points, linearMethod);
        if (exponentialList != null) {
            expSredOtkl = exponentialList.get(0).remove(exponentialList.get(0).size() - 1);

            exponential = exponentialList.get(0);
            expPhis = exponentialList.get(1);
            expEps = exponentialList.get(2);
        } else {
            exponential = null;
            expSredOtkl = null;
            expPhis = null;
            expEps = null;
        }


        List<Double> logarithmic;
        Double logarithmicSredOtkl;
        List<Double> logarithmicPhis;
        List<Double> logarithmicEps;
        List<List<Double>> logarithmicList = logarithmicMethod.solve(points, linearMethod);
        if (logarithmicList != null) {
            logarithmicSredOtkl = logarithmicList.get(0).remove(logarithmicList.get(0).size() - 1);

            logarithmic = logarithmicList.get(0);
            logarithmicPhis = logarithmicList.get(1);
            logarithmicEps = logarithmicList.get(2);
        } else {
            logarithmic = null;
            logarithmicSredOtkl = null;
            logarithmicPhis = null;
            logarithmicEps = null;
        }


        List<List<Double>> powerList = powerMethod.solve(points, linearMethod);

        List<Double> power;
        Double powerSredOtkl;
        List<Double> powerPhis;
        List<Double> powerEps;
        if (powerList != null) {
            powerSredOtkl = powerList.get(0).remove(powerList.get(0).size() - 1);

            power = powerList.get(0);
            powerPhis = powerList.get(1);
            powerEps = powerList.get(2);
        } else {
            power = null;
            powerSredOtkl = null;
            powerPhis = null;
            powerEps = null;
        }

        String best = null;
        List<Double> bestPhis = null;
        List<Double> bestEps = null;
        double[] array = {linearSredOtkl, squareSredOtkl, cubicSredOtkl,
                expSredOtkl == null ? Double.MAX_VALUE : expSredOtkl,
                logarithmicSredOtkl == null ? Double.MAX_VALUE : logarithmicSredOtkl,
                powerSredOtkl == null ? Double.MAX_VALUE : powerSredOtkl,
                };
        array = Arrays.stream(array).sorted().toArray();
        if (array[0] == linearSredOtkl) {
            best = "linear";
            bestPhis = linearPhis;
            bestEps = linearEps;
        } else if (array[0] == squareSredOtkl) {
            best = "square";
            bestPhis = squarePhis;
            bestEps = squareEps;
        } else if (array[0] == cubicSredOtkl) {
            best = "cubic";
            bestPhis = cubicPhis;
            bestEps = cubicEps;
        } else if (array[0] == expSredOtkl) {
            best = "exponential";
            bestPhis = expPhis;
            bestEps = expEps;
        } else if (array[0] == logarithmicSredOtkl) {
            best = "logarithmic";
            bestPhis = logarithmicPhis;
            bestEps = logarithmicEps;
        } else if (array[0] == powerSredOtkl) {
            best = "power";
            bestPhis = powerPhis;
            bestEps = powerEps;
        }
        System.out.println("Среднеквадратичные отклонения:");
        System.out.println("Линейное: " + linearSredOtkl);
        System.out.println("Квадратичное: " + squareSredOtkl);
        System.out.println("Кубическое: " + cubicSredOtkl);
        System.out.println("Экспоненциальное: " + expSredOtkl);
        System.out.println("Логарифмическое: " + logarithmicSredOtkl);
        System.out.println("Степенное: " + powerSredOtkl);
        return new Answer(best, linear, square, cubic, exponential, logarithmic, power, copyPoints, bestPhis, bestEps, array[0], pirson);
    }
}
