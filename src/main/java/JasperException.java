public class JasperException extends Exception {
    public JasperException(String message) {
        super("Something is amiss.. " + message);
    }
}
