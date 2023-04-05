package ru.digitalleague.predictor.entity;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class PredictionResult {
    private final String textPrediction;
    private final BufferedImage imagePrediction;

    public PredictionResult(String textPrediction, BufferedImage imagePrediction) {
        this.textPrediction = textPrediction;
        this.imagePrediction = imagePrediction;
    }
}
