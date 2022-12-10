package pl.inqoo.tripservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.inqoo.tripservice.model.Trip;
import pl.inqoo.tripservice.model.exception.ErrorMessage;
import pl.inqoo.tripservice.model.exception.NoTripFoundException;
import pl.inqoo.tripservice.model.exception.WrongTripPricesException;
import pl.inqoo.tripservice.service.TripService;


import java.net.URI;
import java.util.List;

@RestController
public class TripController {
    @Autowired
    private TripService tripService;
    @PostMapping(path = "/trips", consumes = "application/json")
    public ResponseEntity createTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);
        URI savedCityUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(trip.getId())
                .toUri();
        return ResponseEntity.created(savedCityUri).build();
    }
    @GetMapping(path = "/trips", produces = "application/json")
    public List<Trip> trips(@RequestParam(name = "nameFragment", required = false)String nameFragment) {
        return tripService.getAllTrips(nameFragment);
    }
    @GetMapping(path = "/tripsByPrice", produces = "application/json")
    public List<Trip> tripsByPrice(@RequestParam double priceFrom, @RequestParam double priceTo) {
            return tripService.getByPrice(priceFrom, priceTo);
    }
    @GetMapping(path = "/trips/{tripId}", produces = "application/json")
    public Trip tripsById(@PathVariable("tripId") Integer id){
        return tripService.getAllTrips(null).get(id);
    }
    // Obsługa wyjątku przy pomocy wewnętrznych mechanizmów Springa
    @ExceptionHandler(NoTripFoundException.class) // jaki wyjątek obsługujemy
    @ResponseStatus(HttpStatus.NOT_FOUND) // jaki kod HTTP zwrócimy
    public ResponseEntity<ErrorMessage> handleNoTripFoundException(NoTripFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body( new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
    @ExceptionHandler(WrongTripPricesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleWrongTripException(WrongTripPricesException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}