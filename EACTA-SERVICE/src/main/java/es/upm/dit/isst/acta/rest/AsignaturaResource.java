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


import es.upm.dit.isst.acta.dao.AsignaturaDAOImplementation;
import es.upm.dit.isst.acta.model.Asignatura;



@Path("/Asignatura")
public class AsignaturaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Asignatura> readAll () {
	        return AsignaturaDAOImplementation.getInstance().readAll();
	}
	
	@GET
	@Path("professor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Asignatura readAll (@PathParam("id") String id) {
	        return AsignaturaDAOImplementation.getInstance().readAll(id);

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Asignatura anew) throws URISyntaxException {
		Asignatura a = AsignaturaDAOImplementation.getInstance().create(anew);
	    if (a != null) {
	            URI uri = new URI("/ACTA-SERVICE/rest/Asignatura/" + a.getNombre());
	            return Response.created(uri).build();
	    }
	    return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") String id) {
		Asignatura t = AsignaturaDAOImplementation.getInstance().read(id);
        if (t == null)
          return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(t, MediaType.APPLICATION_JSON).build();
    }        

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") String id, Asignatura t) {
            System.out.println("Update request for" + id + " " + t.toString());
            Asignatura told = AsignaturaDAOImplementation.getInstance().read(id);
        if ((told == null) || (! told.getNombre().contentEquals(t.getNombre())))
          return Response.notModified().build();
        AsignaturaDAOImplementation.getInstance().update(t);
        return Response.ok().build();                
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String  id) {
    	Asignatura rold = AsignaturaDAOImplementation.getInstance().read(id);
        if (rold == null)
            return Response.notModified().build();
        AsignaturaDAOImplementation.getInstance().delete(rold);
        return Response.ok().build();
    }
}
