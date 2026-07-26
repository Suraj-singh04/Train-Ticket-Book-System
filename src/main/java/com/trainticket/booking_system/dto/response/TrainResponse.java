package com.trainticket.booking_system.dto.response;

public class TrainResponse {
    private String trainId;
    private String trainName;

    public TrainResponse() {
    }

    public TrainResponse(String trainId, String trainName) {
        this.trainId = trainId;
        this.trainName = trainName;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }
}