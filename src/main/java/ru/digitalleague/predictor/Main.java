package ru.digitalleague.predictor;

import ru.digitalleague.predictor.controllers.MainController;

public class Main {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.run();
    }
}