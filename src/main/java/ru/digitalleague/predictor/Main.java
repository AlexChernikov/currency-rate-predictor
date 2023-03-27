package ru.digitalleague.predictor;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.controllers.MainController;

@Slf4j
public class Main {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        while (true) {
            mainController.run();
        }
    }
}