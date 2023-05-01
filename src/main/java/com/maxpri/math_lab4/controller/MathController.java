package com.maxpri.math_lab4.controller;

import com.maxpri.math_lab4.dto.Answer;
import com.maxpri.math_lab4.dto.Point;
import com.maxpri.math_lab4.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author max_pri
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class MathController {

    @Autowired
    private MathService service;

    @PostMapping("/submit")
    public Answer solve(@RequestBody List<Point> points) {
        Answer answer = service.solve(points);

        return answer;
    }
}
