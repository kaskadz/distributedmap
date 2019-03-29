package pl.edu.agh.kaskadz.sr.distributedmap.map.message;

import org.jgroups.Message;

public interface MessageFactory {
    Message createPutMessage(String key, Integer value);
    Message createRemoveMessage(String key);
}
