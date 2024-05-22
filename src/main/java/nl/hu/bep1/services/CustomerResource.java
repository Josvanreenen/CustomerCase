package nl.hu.bep1.services;

import nl.hu.bep1.domain.Company;
import nl.hu.bep1.domain.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("customer")
public class CustomerResource {

    @GET
    @Path("{customerid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("customerid") long id){
        Customer found = Company.getCompany().getCustomerByID(id);
        if(found==null) return Response.status(Response.Status.NOT_FOUND).entity(Map.of("error", "no customer found with that id")).build();
        return Response.ok(found).build(); //let op, hier geven we het customerobject direct terug
    }

    @PUT
    @Path("{customerid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCustomer(@PathParam("customerid") long id, CustomerRequest customerRequest){
        Customer replaced = Company.getCompany().updateCustomer(
                id,
                customerRequest.name,
                customerRequest.zipcode,
                customerRequest.city,
                customerRequest.dateofbirth);
        if(replaced == null)
            return Response.status(Response.Status.EXPECTATION_FAILED)
                    .entity(Map.of("error", "unable to update or create customer"))
                    .build();
        return Response.ok(Company.getCompany().getCustomerByID(id)).build();
    }
    @PATCH
    @Path("{customerid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response patchCustomer(@PathParam("customerid") long id, CustomerPatchRequest customerPatchRequest){
        Customer replaced = Company.getCompany().updateCustomer(
                id,
                customerPatchRequest.name);
        if(replaced == null)
            return Response.status(Response.Status.EXPECTATION_FAILED)
                    .entity(Map.of("error", "unable to update customer"))
                    .build();
        return Response.ok(Company.getCompany().getCustomerByID(id)).build();
    }

    @DELETE
    @Path("{customerid}")
    public Response deleteCustomer(@PathParam("customerid") long id){
        return Company.getCompany().removeCustomer(id)
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
