package za.co.ee;

import github.GistClient;
import github.model.Gist;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Set;

@Path("/")
public class GistResource {

    @RestClient
    GistClient gistClient;

    @GET
    @Path("/{user}")
    public Set<Gist> getGists(
            @PathParam(value = "user")
            String user, @QueryParam("page") Integer page,
            @QueryParam("perpage") Integer perPage) {
        // Todo decouple this api from the gitHib API via a separate model and service
        return gistClient.getById(user, page, perPage);
    }
}
