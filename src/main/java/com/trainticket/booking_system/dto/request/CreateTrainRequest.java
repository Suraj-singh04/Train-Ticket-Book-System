package com.trainticket.booking_system.dto.request;

public class CreateTrainRequest {
    private String trainId;
    private String trainName;

    public CreateTrainRequest(){
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
