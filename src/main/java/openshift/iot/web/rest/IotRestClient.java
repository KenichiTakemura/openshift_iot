package openshift.iot.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface IotRestClient {

	@PUT
	@Path("trigger/{action}/{key}")
	@Consumes("text/plain")
	void trigger(@PathParam("action") String action, @PathParam("key") String key);
}
