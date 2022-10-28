import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> route = new ArrayList<>();
    StationIndex metroMap = new StationIndex();

    @Override
    protected void setUp() throws Exception {
        List<Station> connection1to2 = new ArrayList<>();
        List<Station> connection2to3 = new ArrayList<>();

        Line line1 = new Line(1, "firstLine");
        Line line2 = new Line(2, "secondLine");
        Line line3 = new Line(3, "thirdLine");

        metroMap.addLine(line1);
        metroMap.addLine(line2);
        metroMap.addLine(line3);

        metroMap.addStation(new Station("Goldshire", line1));
        metroMap.addStation(new Station("Stormwind", line1));
        metroMap.addStation(new Station("Ironforge", line2));
        metroMap.addStation(new Station("Lordaeron", line2));
        metroMap.addStation(new Station("Gilneas", line3));
        metroMap.addStation(new Station("Silvermoon", line3));

        line1.addStation(metroMap.getStation("Goldshire"));
        line1.addStation(metroMap.getStation("Stormwind"));
        line2.addStation(metroMap.getStation("Ironforge"));
        line2.addStation(metroMap.getStation("Lordaeron"));
        line3.addStation(metroMap.getStation("Gilneas"));
        line3.addStation(metroMap.getStation("Silvermoon"));

        connection1to2.add(metroMap.getStation("Stormwind"));
        connection1to2.add(metroMap.getStation("Ironforge"));
        connection2to3.add(metroMap.getStation("Lordaeron"));
        connection2to3.add(metroMap.getStation("Gilneas"));

        metroMap.addConnection(connection1to2);
        metroMap.addConnection(connection2to3);

        route.add(metroMap.getStation("Goldshire"));
        route.add(metroMap.getStation("Stormwind"));
        route.add(metroMap.getStation("Ironforge"));
        route.add(metroMap.getStation("Lordaeron"));
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute() {
        RouteCalculator routeCalculator = new RouteCalculator(metroMap);
        List<Station> actual = routeCalculator.getShortestRoute(metroMap.getStation("Goldshire"),
                metroMap.getStation("Lordaeron"));
        List<Station> expected = List.of(metroMap.getStation("Goldshire"), metroMap.getStation("Stormwind"),
                metroMap.getStation("Ironforge"), metroMap.getStation("Lordaeron"));
        assertEquals(expected, actual);
    }

}
