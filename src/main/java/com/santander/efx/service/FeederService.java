package com.santander.efx.service;

import java.util.List;

import com.santander.efx.dto.FeedDTO;

public interface FeederService {
	void updatePrices(FeedDTO dto);
	List<FeedDTO> getAll();
}
