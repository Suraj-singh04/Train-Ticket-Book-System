package com.trainticket.booking_system.dto.response;

public class TrainResponse {
    private String trainId;
    private String trainNumber;
    private String trainName;

    public TrainResponse() {
    }

    public TrainResponse(String trainId, String trainNumber, String trainName) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }
}