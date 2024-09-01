package com.example.demo.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Tb_Game")
public class GameModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID gameId;
    private String gameName;
    private Integer gameYear;

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getGameYear() {
        return gameYear;
    }

    public void setGameYear(Integer gameYear) {
        this.gameYear = gameYear;
    }
}
