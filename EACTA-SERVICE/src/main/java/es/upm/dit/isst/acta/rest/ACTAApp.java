package es.upm.dit.isst.acta.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class ACTAApp extends ResourceConfig {

        public ACTAApp() {

                packages("es.upm.dit.isst.acta.rest");

        }

}