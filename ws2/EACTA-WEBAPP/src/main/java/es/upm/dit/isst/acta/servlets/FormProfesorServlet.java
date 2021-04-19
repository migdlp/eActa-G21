package es.upm.dit.isst.acta.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import es.upm.dit.isst.acta.model.Acta;

/**
 * Servlet implementation class FormProfesorServlet
 */
@WebServlet("/FormProfesorServlet")
public class FormProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)

	               throws ServletException, IOException {

	  // autorizacion

	  String email = req.getParameter("actaemail");

	  Client client = ClientBuilder.newClient(new ClientConfig());

	  Acta acta = null;

	  try { acta = client.target(URLHelper.getURL() + "/" + email)

	              .request().accept(MediaType.APPLICATION_JSON).get(Acta.class);}

	  catch(Exception e) {}

	  if (acta != null) {

	    // autorizacion

	    if (acta.getStatus() == 1 || acta.getStatus() == 4 || acta.getStatus() == 6) {

	        acta.setStatus(acta.getStatus()+1);

	        if (req.getParameter("notaprovisional") != null) {

	                String m = req.getParameter("notaprovisional").replace(",", ".");

	                acta.setNotaprovisional(Double.parseDouble(m));

	        }

	        client.target(URLHelper.getURL()+ "/" + email).request()

	           .post(Entity.entity(acta, MediaType.APPLICATION_JSON), Response.class);

	    }

	    List<Acta> actas = client.target(URLHelper.getURL() + "/professor/"+ acta.getEmailcoordinador())

	                                .request().accept(MediaType.APPLICATION_JSON)

	                              .get(new GenericType<List<Acta>>() {});

	    req.setAttribute("actas", actas);

	  }

	  getServletContext().getRequestDispatcher("/Professor.jsp").forward(req,resp);

	}
}