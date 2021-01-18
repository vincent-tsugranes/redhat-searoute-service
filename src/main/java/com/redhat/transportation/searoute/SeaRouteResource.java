package com.redhat.transportation.searoute;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eu.europa.ec.eurostat.searoute.GeoDistanceUtil;
import eu.europa.ec.eurostat.jgiscotools.feature.Feature;
import eu.europa.ec.eurostat.searoute.SeaRouting;
import org.locationtech.jts.geom.MultiLineString;

@Path("/route")
public class SeaRouteResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(
            @QueryParam("startLat") double startLat,
            @QueryParam("startLon") double startLon,
            @QueryParam("endLat") double endLat,
            @QueryParam("endLon") double endLon
    ) {
        if ((startLat == 0) ||  (startLon == 0) || (endLat == 0) ||  (endLon == 0)) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("missing coordinates")
                            .build()
            );
        }

        SeaRouting sr = new SeaRouting(20);

        //get the route between start and end coordinates
        Feature route = sr.getRoute(startLon, startLat, endLon, endLat);

        //compute the route distance in km
        MultiLineString routeGeom = (MultiLineString) route.getGeometry();
        String rgj = SeaRouting.toGeoJSON(routeGeom);

        return rgj;
    }
}