package ru.digitalleague.predictor.bot.enums;

public enum BotCommandEnum {
    START {
        public String getDescription() {
            return "get a welcome message";
        }
    },
    HELP{
        public String getDescription() {
            return "info about commands";
        }
    },
    INFO{
        public String getDescription() {
            return "info how to use bot";
        }
    },
    RATE{
        public String getDescription() {
            return "start currency rate predication";
        }
    };

    public String getDescription() {
        return this.getDescription();
    }

}
