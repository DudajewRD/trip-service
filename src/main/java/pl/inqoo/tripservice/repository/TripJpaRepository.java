package pl.inqoo.tripservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import pl.inqoo.tripservice.model.Trip;


import java.util.List;

public interface TripJpaRepository extends JpaRepository<Trip,Integer>, RevisionRepository<Trip,Integer,Integer> {
    List<Trip> findAllByPriceEurBetween(double from, double to);
}
