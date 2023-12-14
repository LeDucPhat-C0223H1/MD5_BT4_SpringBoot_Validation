package ra.springboot_validation.exception;

public class UserNameAndPasswordException extends Exception {
    public UserNameAndPasswordException(String message) {
        super(message);
    }
}
