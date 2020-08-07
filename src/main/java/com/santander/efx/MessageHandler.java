package com.santander.efx;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.efx.dto.FeedDTO;
import com.santander.efx.service.FeederService;

@Service
public class MessageHandler {
    private final static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
	@Autowired
    private FeederService feederService;
	private SimpleDateFormat sdf;
	
	public MessageHandler() {
		sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
	}

	/**
	 * This Method handle the message and make some input validation.
	 *  - Check if the currency is 3 letters backslash and more 3 letters.
	 * @param message
	 */
	public void onMessage(String message) {
		try {
			StringTokenizer st = new StringTokenizer(message, ",");
			FeedDTO dto = new FeedDTO();
			dto.setId(Integer.parseInt((String) st.nextElement()));
			String currency = ((String) st.nextElement()).trim();
			if(!currency.matches("\\w{3}\\/\\w{3}")) {
				throw new Exception("Currency not right format.");
			}
			dto.setCurrency(currency);
			dto.setAsk(Float.valueOf((String) st.nextElement()));
			dto.setSell(Float.valueOf((String) st.nextElement()));
			String timestamp = ((String) st.nextElement()).trim();
			if(sdf.parse(timestamp) == null) {
				throw new Exception("Timestamp not right format.");
			}
			dto.setTimestamp(timestamp);
			feederService.updatePrices(dto);
		} catch (Exception e) {
			logger.error("Error on message handler: "+e.getMessage(), e);
		}
	}
}
