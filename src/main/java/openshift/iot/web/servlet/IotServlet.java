package openshift.iot.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.WebContext;

import openshift.iot.web.thymeleaf.ThymeleafAppUtil;

@WebServlet(name = "IotServlet", urlPatterns = "/iot/*", loadOnStartup = 1)
public class IotServlet extends BaseServlet {
	private static final Logger log = LoggerFactory.getLogger(IotServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init();
		log.info("Init done");
	}

	@Override
	public void destroy() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("doGet starts");
		WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale());
		ThymeleafAppUtil.getServletTemplateEngine().process("iot", ctx, response.getWriter());
		log.debug("doGet completes.");
	}

}
