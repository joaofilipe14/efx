package com.santander.efx.dto;

import com.santander.efx.entity.Feeder;

public class FeedDTO {
	private Integer id;
	private String currency;
	private Float sell;
	private Float ask;
	private String timestamp;

	/**
     * @param account 
     * @return
     */
    public static FeedDTO entityToDto(Feeder feeder) {
    	FeedDTO dto = new FeedDTO();
    	dto.setId(feeder.getId());
    	dto.setCurrency(feeder.getCurrency());
    	dto.setAsk(feeder.getAsk());
    	dto.setSell(feeder.getSell());
    	dto.setTimestamp(feeder.getTimestamp());
        return dto;
    }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Float getSell() {
		return sell;
	}

	public void setSell(Float sell) {
		this.sell = sell;
	}

	public Float getAsk() {
		return ask;
	}

	public void setAsk(Float ask) {
		this.ask = ask;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
