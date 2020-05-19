package az.itstep.crud.controller;

import az.itstep.crud.model.Employee;
import az.itstep.crud.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@ApplicationScoped
@Path("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    private EmployeeService employeeService;

    @POST
    public Response save(Employee employee){
        logger.debug("Request for saving employee: {}", employee);
        employeeService.save(employee);
        return Response.created(URI.create("/employees")).build();
    }

    @GET
    public Response findAll(){
        logger.debug("Request for all employees");
        List<Employee> employees = employeeService.findAll();

        String json = null;
        try {
            json = objectMapper.writeValueAsString(employees);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        logger.debug("Request for employee with id: {}", id);
        String json = null;

        Employee employee = employeeService.findById(id);
        try {
            json = objectMapper.writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") long id){
        logger.debug("Request for deleting employee with id: {}", id);
        employeeService.deleteById(id);
        return Response.noContent().build();
    }

    @PUT
    public Response update(Employee employee){
        employeeService.update(employee);
        return Response.accepted().build();
    }

}
