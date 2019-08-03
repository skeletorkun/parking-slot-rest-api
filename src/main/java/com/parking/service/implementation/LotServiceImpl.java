package com.parking.service.implementation;


import com.parking.repository.LotRepository;
import com.parking.service.LotService;
import com.parking.model.entity.Lot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotServiceImpl implements LotService {

    private static final Logger LOG = LoggerFactory.getLogger(LotServiceImpl.class);

    @Autowired
    LotRepository lotRepository;


    public Lot findById(long lotId) {
        return lotRepository.findOne(lotId);
    }
}
