package pl.inqoo.tripservice.model.exception;

public class WrongTripPricesException extends RuntimeException{
    public WrongTripPricesException(String message) {
        super(message);
    }
}
