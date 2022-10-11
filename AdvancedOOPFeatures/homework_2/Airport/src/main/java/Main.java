import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должен вернуть список рейсов вылетающих в ближайшие два часа.
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        simpleDateFormat.format(currentDate);
        List<Flight> requiredPlanes = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .filter(a -> a.getDate().toInstant().isBefore(Instant.now().plusSeconds(7200)))
                .filter(a -> a.getDate().toInstant().isAfter(Instant.now()))
                .distinct()
                .collect(Collectors.toList());

        return requiredPlanes;
    }
}