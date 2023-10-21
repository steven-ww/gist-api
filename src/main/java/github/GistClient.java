package github;

import github.model.Gist;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Set;

@Path("/")
@RegisterRestClient
public interface GistClient {

    @GET
    @Path("/{userId}/gists")
    @Retry(maxRetries = 3)
    @Timeout(250)
    Set<Gist> getById(
            @PathParam("userId") String userId,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer perPage);
}
