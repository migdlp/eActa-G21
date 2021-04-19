package es.upm.dit.isst.acta.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.upm.dit.isst.acta.dao.ACTADAOImplementation;
import es.upm.dit.isst.acta.model.Acta;

@Path("/ACTAs")
public class ACTAResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acta> readAll () {
	        return ACTADAOImplementation.getInstance().readAll();
	}
	
	@GET
	@Path("professor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acta> readAll (@PathParam("id") String id) {
	        return ACTADAOImplementation.getInstance().readAll(id);

	}
	
	@GET
	@Path("alumno/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acta> read1 (@PathParam("id") String id) {
	        return ACTADAOImplementation.getInstance().read1(id);

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Acta tnew) throws URISyntaxException {
	    Acta t = ACTADAOImplementation.getInstance().create(tnew);
	    if (t != null) {
	            URI uri = new URI("/ACTA-SERVICE/rest/ACTAs/" + t.getEmailalumno());
	            return Response.created(uri).build();
	    }
	    return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") String id) {
        Acta t = ACTADAOImplementation.getInstance().read(id);
        if (t == null)
          return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(t, MediaType.APPLICATION_JSON).build();
    }        

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") String id, Acta t) {
            System.out.println("Update request for" + id + " " + t.toString());
        Acta told = ACTADAOImplementation.getInstance().read(id);
        if ((told == null) || (! told.getEmailalumno().contentEquals(t.getEmailalumno())))
          return Response.notModified().build();
        ACTADAOImplementation.getInstance().update(t);
        return Response.ok().build();                
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String  id) {
        Acta rold = ACTADAOImplementation.getInstance().read(id);
        if (rold == null)
            return Response.notModified().build();
        ACTADAOImplementation.getInstance().delete(rold);
        return Response.ok().build();
    }
}