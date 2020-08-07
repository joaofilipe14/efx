package com.santander.efx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.efx.dto.FeedDTO;
import com.santander.efx.entity.Feeder;
import com.santander.efx.repository.FeederRepository;
import com.santander.efx.service.FeederService;

/**
 * @author jFilipe
 *
 */
@Service
public class FeederServiceImpl implements FeederService {
	@Autowired
	private FeederRepository feederRepository;

	/**
	 *
	 */
	public void updatePrices(FeedDTO dto) {
		List<Feeder> lst = feederRepository.findByCurrency(dto.getCurrency());
		if(lst.size()==1) {
			Feeder feeder = lst.get(0);
			feeder.setCurrency(dto.getCurrency());
			feeder.setAsk(dto.getAsk());
			feeder.setSell(dto.getSell());
			feeder.setTimestamp(dto.getTimestamp());
			feederRepository.save(feeder);
		} else {

			Feeder feeder = new Feeder();
			feeder.setId(dto.getId());
			feeder.setCurrency(dto.getCurrency());
			feeder.setAsk(dto.getAsk());
			feeder.setSell(dto.getSell());
			feeder.setTimestamp(dto.getTimestamp());
			feederRepository.save(feeder);
		}
	}

	public List<FeedDTO> getAll() {
		List<FeedDTO> lst = new ArrayList<>();
		for (Feeder feeder : feederRepository.findAll()) {
			lst.add(FeedDTO.entityToDto(feeder));
		}
		return lst;
	}
}
