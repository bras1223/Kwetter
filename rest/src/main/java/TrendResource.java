import dto.TrendDTO;
import nl.luukhermans.domain.Trend;
import nl.luukhermans.service.TrendService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("trends")
@Stateless
public class TrendResource {

    @Inject
    TrendService trendService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrendDTO> getAll() {
        List<TrendDTO> trends = new LinkedList<TrendDTO>();
        for (Trend t : trendService.getAllTrends()) {
            trends.add(new TrendDTO(t));
        }

        return trends;
    }

}
