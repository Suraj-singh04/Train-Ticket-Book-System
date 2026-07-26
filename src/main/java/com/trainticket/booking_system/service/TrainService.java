package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.request.CreateTrainRequest;
import com.trainticket.booking_system.dto.response.TrainResponse;

public interface TrainService {
    TrainResponse createTrain(CreateTrainRequest request);

    List<TrainResponse> getAllTrains();

    TrainResponse getTrainById(String trainId);

    TrainResponse updateTrain(String trainId, CreateTrainRequest request);

    void deleteTrain(String trainId);
}
