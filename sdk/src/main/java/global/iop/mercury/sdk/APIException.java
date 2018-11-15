package global.iop.mercury.sdk;

public class APIException extends RuntimeException {
    APIException(String message) {
        super(message);
    }

    APIException(Exception e) {
        super(e);
    }
}
