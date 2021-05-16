package es.upm.dit.isst.acta.servlets;
public class URLHelper {

    public static String getURL() {
            String envValue = System.getenv("ACTASERVICE_SRV_SERVICE_HOST");
            if(envValue == null)
                    return "http://localhost:8080/EACTA-SERVICE/rest/ACTAs";
            else
                    return envValue;
    }
    
    public static String getURL_asignatura() {
        String envValue = System.getenv("ACTASERVICE_SRV_SERVICE_HOST");
        if(envValue == null)
                return "http://localhost:8080/EACTA-SERVICE/rest/Asignatura";
        else
                return envValue;
}
}