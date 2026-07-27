package com.trainticket.booking_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTrainRequest {

    @NotBlank(message = "Train number is required")
    @Size(min = 3, max = 10, message = "Train number must be between 3 and 10 characters")
    private String trainNumber;

    @NotBlank(message = "Train name is required")
    @Size(min = 3, max = 100, message = "Train name must be between 3 and 100 characters")
    private String trainName;

    public CreateTrainRequest(){
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
