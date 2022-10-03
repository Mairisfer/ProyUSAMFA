package com.usa.boxproy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.naming.spi.ResolveResult;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "score")
public class Score implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idScore;
    private String scoreVal;
    private String mesScore;

    private Integer idReservation;

    @OneToOne
    @JsonIgnoreProperties("score")
    private Reservation reservation;

    public Integer getIdScore() {
        return idScore;
    }

    public void setIdScore(Integer idScore) {
        this.idScore = idScore;
    }

    public String getScoreVal() {
        return scoreVal;
    }

    public void setScoreVal(String scoreVal) {
        this.scoreVal = scoreVal;
    }

    public String getMesScore() {
        return mesScore;
    }

    public void setMesScore(String mesScore) {
        this.mesScore = mesScore;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
