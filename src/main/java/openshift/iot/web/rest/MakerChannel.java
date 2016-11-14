package openshift.iot.web.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakerChannel {

	private static final Logger log = LoggerFactory.getLogger(RestService.class);

	public String trigger(String action, String key) {
		log.info("Iot MakerChannel {} {}", action, key);
		Client client = ClientBuilder.newBuilder().build();
		log.info("client {}", client);
		WebTarget target = client.target("https://maker.ifttt.com/trigger/" + action
				+ "/with/key/" + key);
		log.debug("target {}",  target);
		Response response = target.request().get();
		String value = response.readEntity(String.class);
		response.close();
		log.info("response {}", value);
		return value;
	}
}
