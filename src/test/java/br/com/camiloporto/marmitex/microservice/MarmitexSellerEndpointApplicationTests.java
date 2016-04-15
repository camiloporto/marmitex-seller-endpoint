package br.com.camiloporto.marmitex.microservice;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = MarmitexSellerEndpointApplication.class)
@WebAppConfiguration
public class MarmitexSellerEndpointApplicationTests extends AbstractTestNGSpringContextTests {

	@Test
	public void contextLoads() {
	}

}
