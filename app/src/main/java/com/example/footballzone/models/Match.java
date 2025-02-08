package com.example.footballzone.models;

public class Match {
    private String matchDate; // Дата матча
    private String homeTeam;  // Домашняя команда
    private String awayTeam;  // Гостевая команда

    // Геттеры для полей
    public String getMatchDate() {
        return matchDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    // Вы можете добавить дополнительные поля и методы по мере необходимости
}
