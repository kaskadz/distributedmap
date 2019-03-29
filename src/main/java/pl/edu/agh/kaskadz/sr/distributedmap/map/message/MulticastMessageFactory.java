package pl.edu.agh.kaskadz.sr.distributedmap.map.message;

import org.jgroups.Message;

public class MulticastMessageFactory implements MessageFactory {
    @Override
    public Message createPutMessage(String key, Integer value) {
        return new Message(null, new MapMessage(MessageType.PUT, key, value));
    }

    @Override
    public Message createRemoveMessage(String key) {
        return new Message(null, new MapMessage(MessageType.REMOVE, key));
    }
}
