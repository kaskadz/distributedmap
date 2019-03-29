package pl.edu.agh.kaskadz.sr.distributedmap.map;

import org.jgroups.*;
import org.jgroups.util.Util;
import pl.edu.agh.kaskadz.sr.distributedmap.channel.JChannelFactory;
import pl.edu.agh.kaskadz.sr.distributedmap.map.message.MapMessage;
import pl.edu.agh.kaskadz.sr.distributedmap.map.message.MessageFactory;
import pl.edu.agh.kaskadz.sr.distributedmap.map.message.MulticastMessageFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class DistributedMap implements SimpleStringMap, Receiver {
    private final JChannel channel;
    private final Map<String, Integer> map;
    private final MessageFactory messageFactory;

    public static DistributedMap Create(String channelName, JChannelFactory channelFactory) throws DistributedMapCreationException {
        try {
            JChannel channel = channelFactory.create();
            channel.connect(channelName);
            DistributedMap map = new DistributedMap(channel);
            channel.setReceiver(map);
            channel.getState(null, 0);
            return map;
        } catch (Exception e) {
            throw new DistributedMapCreationException("Creating map instance failed.", e);
        }
    }

    private DistributedMap(JChannel channel) {
        this.channel = channel;
        map = new HashMap<>();
        messageFactory = new MulticastMessageFactory();
    }

    /*
     * SimpleStringMap behavior
     */

    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    @Override
    public Integer get(String key) {
        return map.get(key);
    }

    @Override
    public void put(String key, Integer value) {
        try {
            channel.send(messageFactory.createPutMessage(key, value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer remove(String key) {
        Integer removedKey = map.get(key);

        try {
            channel.send(messageFactory.createRemoveMessage(key));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return removedKey;
    }

    public void printContents() {
        System.out.printf("Map contains %d entries:\n", map.size());
        map.forEach((k, v) -> System.out.printf("[%s] -> %d\n", k, v));
    }

    public void close() {
        channel.close();
    }

    /*
     * Receiver behavior
     */

    @Override
    public void receive(Message msg) {
        MapMessage message = (MapMessage) msg.getObject();
        switch (message.messageType) {
            case PUT:
                map.put(message.key, message.value);
                break;
            case REMOVE:
                map.remove(message.key);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message.messageType);
        }

        printContents();
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (map) {
            Util.objectToStream(map, new DataOutputStream(output));
        }
    }

    @Override
    public void setState(InputStream input) throws Exception {
        Map<String, Integer> state = (Map<String, Integer>) Util.objectFromStream(new DataInputStream(input));

        synchronized (map) {
            map.clear();
            map.putAll(state);
        }

        printContents();
    }

    @Override
    public void viewAccepted(View new_view) {
        handleView(channel, new_view);
    }

    private static void handleView(JChannel ch, View new_view) {
        if (new_view instanceof MergeView) {
            ViewHandler handler = new ViewHandler(ch, (MergeView) new_view);
            handler.start();
        }
    }

    @Override
    public void suspect(Address suspected_mbr) {

    }

    @Override
    public void block() {

    }

    @Override
    public void unblock() {

    }
}
