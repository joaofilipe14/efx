package com.santander.efx;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.efx.dto.FeedDTO;
import com.santander.efx.service.FeederService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@EnableConfigurationProperties
public class MessageHandlerTest {
	@Autowired
    private MessageHandler mh;
    @Autowired
    private FeederService feederService;

	@Test
	public void onMessageTest() {
		mh.onMessage("1, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");
		List<FeedDTO> lst = feederService.getAll();
		Assert.assertEquals(1, lst.size());
		mh.onMessage("2, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:0011");
		lst = feederService.getAll();
		Assert.assertEquals(2, lst.size());
		mh.onMessage("3, ExUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");
		lst = feederService.getAll();
		Assert.assertEquals(2, lst.size());
		mh.onMessage("4, EUR/USD, 1.1000,1.2000,01-06-2020 12:02:01:001");
		lst = feederService.getAll();
		Assert.assertEquals(2, lst.size());
		FeedDTO feeder = lst.get(0);
		Assert.assertEquals("01-06-2020 12:02:01:001", feeder.getTimestamp());
	}
}
