package ovh.jakubk.shooting.range.management.exceptions;

public class AmmoOnStockException extends RuntimeException {
    public AmmoOnStockException(String message) {
        super(message);
    }
}
