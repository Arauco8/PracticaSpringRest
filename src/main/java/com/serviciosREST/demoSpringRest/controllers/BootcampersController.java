package com.serviciosREST.demoSpringRest.controllers;

import com.serviciosREST.demoSpringRest.models.Bootcamper;
import com.serviciosREST.demoSpringRest.services.BootcamperService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@Path("/")
public class BootcampersController {
//    @GET
//    public String hola(){
//        return "Hola";
//    }

    private final BootcamperService bootcamperService; // un servicio comunica un controlador con el modelo

    public BootcampersController(BootcamperService bootcamperService) {
        this.bootcamperService = bootcamperService;

        this.bootcamperService.add(new Bootcamper ("uno", Math.random()));
        this.bootcamperService.add(new Bootcamper("dos", Math.random()));
        this.bootcamperService.add(new Bootcamper("tres", Math.random()));
        this.bootcamperService.add(new Bootcamper("cuatro", Math.random()));
        this.bootcamperService.add(new Bootcamper("cinco", Math.random()));
    }

    @GET
    @Path("/bootcampers")
    @Produces("application/json")
    public List<Bootcamper> listarTodos(){
        return bootcamperService.getAll();
    }

    @GET
    @Path("/bootcampers/{nombre}")
    @Produces("application/json")
    public Bootcamper listarUno(@PathParam("nombre") String nombre){
        return bootcamperService.get(nombre);
    }

    @POST
    @Path("/bootcampers")
    @Produces("application/json")
    @Consumes("application/json")
    public Response meterBootcamper(Bootcamper bootcamper){
        bootcamperService.add(bootcamper);

        return Response.created(URI.create("/bootcampers/" + bootcamper.getNombre())).build();
    }
}
