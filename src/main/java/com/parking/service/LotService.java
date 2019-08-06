package com.parking.service;


import com.parking.model.entity.Lot;

import java.util.Optional;

public interface LotService {

    Optional<Lot> findById(long lotId);
}
