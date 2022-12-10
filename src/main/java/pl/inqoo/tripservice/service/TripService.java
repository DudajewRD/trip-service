package pl.inqoo.tripservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inqoo.tripservice.model.Trip;
import pl.inqoo.tripservice.model.exception.NoTripFoundException;
import pl.inqoo.tripservice.model.exception.WrongTripPricesException;
import pl.inqoo.tripservice.repository.TripJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
    @Autowired
    private TripJpaRepository tripJpaRepository;
    public void saveTrip(Trip trip) {
        tripJpaRepository.save(trip);
    }

    public List<Trip> getAllTrips(String nameFragment) throws NoTripFoundException {
        List<Trip> result = tripJpaRepository.findAll();
        if (nameFragment != null) {
            result = result.stream()
                    .filter(c -> c.getDestination().contains(nameFragment))
                    .collect(Collectors.toList());
        }
        if (result.isEmpty()){
            throw new NoTripFoundException("No trip found with destination " + nameFragment);
        }
        return result;
    }
    public List<Trip> getByPrice(double priceFrom, double priceTo) throws NoTripFoundException, WrongTripPricesException {
        if (priceFrom>priceTo){
            throw new WrongTripPricesException("Wrong price input"+priceFrom+ "and"+ priceTo);
        }
        List<Trip> tripsByPrice = tripJpaRepository.findAllByPriceEurBetween(priceFrom, priceTo);
        if (tripsByPrice.isEmpty()) {
            throw new NoTripFoundException("No trip with price between "+priceFrom+" and "+priceTo);
        }
        return tripsByPrice;
    }
}
