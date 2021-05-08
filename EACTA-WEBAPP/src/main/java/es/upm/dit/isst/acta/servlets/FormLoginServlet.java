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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import es.upm.dit.isst.acta.model.Acta;

/**
 * Servlet implementation class FormLoginServlet
 */

@WebServlet("/FormLoginServlet")
public class FormLoginServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private final String ADMIN_EMAIL = "root";
        private final String ADMIN_PASSWORD = "root";        
        
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                           throws ServletException, IOException {

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Client client = ClientBuilder.newClient(new ClientConfig());

            // autenticacion1

            if( ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) {        
                 req.getSession().setAttribute("admin", true);
                 List<Acta> actas  = client.target(URLHelper.getURL())
                        .request().accept(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<Acta>>() {});
                 req.setAttribute("actas", actas);
               getServletContext().getRequestDispatcher("/Admin.jsp")
                                  .forward(req,resp);
                return;
            }

            // autenticacion2
            if ( email.indexOf("@upm.es") > -1) {
                    req.getSession().setAttribute("profesor", email);
                    List<Acta> actas  = client.target(URLHelper.getURL()
                                         + "/professor/" + email)
                             .request().accept(MediaType.APPLICATION_JSON)
                             .get(new GenericType<List<Acta>>() {});
                    req.setAttribute("actas", actas);
                    getServletContext().getRequestDispatcher("/Professor.jsp")
                                  .forward(req,resp);
                    return;
            }            

            // autenticacion3
            Acta acta = null;
            try { acta = client.target(URLHelper.getURL() + "/" + email)
                            .request().accept(MediaType.APPLICATION_JSON).get(Acta.class);
            }catch (Exception e) {
            }
            if ( null != acta ) {
                    req.getSession().setAttribute("acta", acta);
                    getServletContext().getRequestDispatcher("/Acta.jsp").forward(req,resp);
                    return;
            }
            getServletContext().getRequestDispatcher("/index.html").forward(req,resp);
        }
}