package pl.inqoo.tripservice.model.exception;

/**
 * Klasa opisuje błąd zwracany przez aplikację
 */
public class ErrorMessage {
    private String message; // opis tekstowy błędu

    private Integer httpResponseCode; // kod HTTP

    public ErrorMessage(String message, Integer httpResponseCode) {
        this.message = message;
        this.httpResponseCode = httpResponseCode;
    }

    public String getMessage() {
        return message;
    }

    public Integer getHttpResponseCode() {
        return httpResponseCode;
    }
}
