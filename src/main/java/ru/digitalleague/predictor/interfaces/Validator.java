package ru.digitalleague.predictor.interfaces;

public interface Validator<E extends RuntimeException> {
    boolean validate(String command) throws E;
}