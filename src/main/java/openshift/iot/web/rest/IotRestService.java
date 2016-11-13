package openshift.iot.web.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.WebContext;

import openshift.iot.web.thymeleaf.ThymeleafAppUtil;

@Path("/iot")
public class IotRestService {

	private static final Logger log = LoggerFactory.getLogger(IotRestService.class);
	private Map<String, IotAction> actions = new HashMap<String, IotAction>();

	@GET
	@Path("/start")
	@Produces("text/html")
	public Response start(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		log.info("REST CALL devices");
		WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale());
		try {
			ThymeleafAppUtil.getServletTemplateEngine().process("iot/start", ctx, response.getWriter());
		} catch (IOException e) {
			log.warn("Cannot get devices");
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/device/{action}/{key}")
	@Produces("text/html")
	public Response device(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@PathParam("action") String action, @PathParam("key") String key) {
		log.info("REST CALL devices {} {}", action, key);
		if (!action.isEmpty()) {
			actions.put(action, new IotAction().setAction(action).setKey(key));
			WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale());
			try {
				ThymeleafAppUtil.getServletTemplateEngine().process("iot/start", ctx, response.getWriter());
			} catch (IOException e) {
				log.warn("Cannot get devices");
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/actions")
	@Produces("application/json")
	public List<IotAction> actions(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		log.info("REST CALL actions");
		return actions.values().stream().collect(Collectors.toList());
	}

	@GET
	@Path("/actions/view")
	@Produces("text/html")
	public Response actions_view(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		log.info("REST CALL actions_view");
		request.setAttribute("actions", actions.values().stream().collect(Collectors.toList()));
		WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale());
		try {
			ThymeleafAppUtil.getServletTemplateEngine().process("iot/actions", ctx, response.getWriter());
		} catch (IOException e) {
			log.warn("Cannot get actions_view");
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/trigger/{action}/{key}")
	@Produces("application/json")
	public Response trigger(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		log.info("REST CALL trigger");
		return Response.ok().build();
	}

}
