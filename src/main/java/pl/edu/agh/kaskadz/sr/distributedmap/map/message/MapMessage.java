package pl.edu.agh.kaskadz.sr.distributedmap.map.message;

import java.io.Serializable;

public class MapMessage implements Serializable {
    public final MessageType messageType;
    public final String key;
    public final Integer value;

    public MapMessage(MessageType messageType, String key) {
        this(messageType, key, null);
    }

    public MapMessage(MessageType messageType, String key, Integer value) {
        this.messageType = messageType;
        this.key = key;
        this.value = value;
    }
}
