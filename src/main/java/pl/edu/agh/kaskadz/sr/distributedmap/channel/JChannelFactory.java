package pl.edu.agh.kaskadz.sr.distributedmap.channel;

import org.jgroups.JChannel;

public interface JChannelFactory {
    JChannel create() throws Exception;
}
