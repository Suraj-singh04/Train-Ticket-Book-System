package com.trainticket.booking_system.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String trainId;

    @Column(nullable = false)
    private String trainName;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coach> coaches = new ArrayList<>();

    protected Train() {}    

    public Train(String trainId, String trainName) {
        this.trainId = trainId;
        this.trainName = trainName;
    }


    public String getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

}