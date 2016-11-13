package openshift.iot.web.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("/rest")
public class RestService extends Application {
	private static final Logger log = LoggerFactory.getLogger(RestService.class);
	private Set<Object> singletons = new HashSet<Object>();

	public RestService() {
		log.info("Produces Application");
		singletons.add(new IotRestService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
