package pl.edu.agh.kaskadz.sr.distributedmap;

import pl.edu.agh.kaskadz.sr.distributedmap.channel.DefaultStackWithGivenMulticastAddressJChannelFactory;
import pl.edu.agh.kaskadz.sr.distributedmap.channel.JChannelFactory;
import pl.edu.agh.kaskadz.sr.distributedmap.maprunner.DistributedMapRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("java.net.preferIPv4Stack","true");

        InetAddress multicastAddress = InetAddress.getByName("230.100.200.8");
        JChannelFactory channelFactory = new DefaultStackWithGivenMulticastAddressJChannelFactory(multicastAddress);
        String channelName = "channelbykasper";

        DistributedMapRunner runner = new DistributedMapRunner(channelName, channelFactory);
        runner.run();
    }
}
