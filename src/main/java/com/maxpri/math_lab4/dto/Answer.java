package com.maxpri.math_lab4.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author max_pri
 */
@Getter
@Setter
public class Answer {
    private String best;
    private List<Double> linear;
    private List<Double> square;
    private List<Double> cubic;
    private List<Double> exponential;
    private List<Double> logarithmic;
    private List<Double> power;
    private List<Point> points;
    private List<Double> phis;
    private List<Double> epsilons;
    private Double sredneKvOtkl;
    private Double pirson;

    public Answer(String best, List<Double> linear, List<Double> square, List<Double> cubic, List<Double> exponential, List<Double> logarithmic, List<Double> power, List<Point> points, List<Double> phis, List<Double> epsilons, Double sredneKvOtkl, Double pirson) {
        this.best = best;
        this.linear = linear;
        this.square = square;
        this.cubic = cubic;
        this.exponential = exponential;
        this.logarithmic = logarithmic;
        this.power = power;
        this.points = points;
        this.phis = phis;
        this.epsilons = epsilons;
        this.sredneKvOtkl = sredneKvOtkl;
        this.pirson = pirson;
    }
}
