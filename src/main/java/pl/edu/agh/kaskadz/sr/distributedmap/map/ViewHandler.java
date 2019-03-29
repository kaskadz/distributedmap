package pl.edu.agh.kaskadz.sr.distributedmap.map;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.MergeView;
import org.jgroups.View;

import java.util.List;

public class ViewHandler extends Thread {
    private final JChannel channel;
    private final MergeView view;

    public ViewHandler(JChannel channel, MergeView view) {
        this.channel = channel;
        this.view = view;
    }

    public void run() {
        List<View> subgroups = view.getSubgroups();
        View tmp_view = subgroups.get(0); // picks the first
        Address local_addr = channel.getAddress();

        if (!tmp_view.getMembers().contains(local_addr)) {
            System.out.printf("Not member of the new primary partition (%s), will re-acquire the state%n", tmp_view);
            try {
                channel.getState(null, 30000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.printf("Not member of the new primary partition (%s), will do nothing%n", tmp_view);
        }
    }
}
