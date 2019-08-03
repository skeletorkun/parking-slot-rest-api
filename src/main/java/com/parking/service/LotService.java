package com.parking.service;


import com.parking.model.entity.Lot;

public interface LotService {

    Lot findById(long lotId);
}
