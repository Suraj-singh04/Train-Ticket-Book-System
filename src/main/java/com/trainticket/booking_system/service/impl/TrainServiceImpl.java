package com.trainticket.booking_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trainticket.booking_system.dto.request.CreateTrainRequest;
import com.trainticket.booking_system.dto.response.TrainResponse;
import com.trainticket.booking_system.entity.Train;
import com.trainticket.booking_system.repository.TrainRepository;
import com.trainticket.booking_system.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService{
    private final TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public TrainResponse createTrain(CreateTrainRequest request) {
        Train train = new Train(
            request.getTrainId(),
            request.getTrainName()
        );

        Train savedTrain = trainRepository.save(train);

        return new TrainResponse(
            savedTrain.getTrainId(),
            savedTrain.getTrainName()
        );
    }

    @Override
    public List<TrainResponse> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        return trains.stream()
                .map(train -> new TrainResponse(
                    train.getTrainId(),
                    train.getTrainName()))
                .toList();
    }
}
