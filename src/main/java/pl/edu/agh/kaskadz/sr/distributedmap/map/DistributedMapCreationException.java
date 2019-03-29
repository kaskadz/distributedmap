package pl.edu.agh.kaskadz.sr.distributedmap.map;

public class DistributedMapCreationException extends Exception {
    public DistributedMapCreationException() {
    }

    public DistributedMapCreationException(String message) {
        super(message);
    }

    public DistributedMapCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
