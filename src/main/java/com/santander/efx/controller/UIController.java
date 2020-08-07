package com.santander.efx.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.efx.dto.FeedDTO;
import com.santander.efx.service.FeederService;

@RequestMapping("/ui")
@RestController
public class UIController {
    private final static Logger logger = LoggerFactory.getLogger(UIController.class);
    @Autowired
    private FeederService feederService;
    
    @GetMapping(path = "/")
    public List<FeedDTO> getAll() {
        try {
            return feederService.getAll();
        } catch (Exception e) {
            logger.error("Error on get all account information: " + e.getMessage(), e);
        }
        return new ArrayList<>();
    }
}
