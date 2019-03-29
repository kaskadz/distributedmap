package pl.edu.agh.kaskadz.sr.distributedmap.maprunner;

import pl.edu.agh.kaskadz.sr.distributedmap.channel.JChannelFactory;
import pl.edu.agh.kaskadz.sr.distributedmap.map.DistributedMap;
import pl.edu.agh.kaskadz.sr.distributedmap.maprunner.operations.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DistributedMapRunner implements Runnable {
    private final JChannelFactory channelFactory;
    private final String channelName;

    private DistributedMap map;

    public DistributedMapRunner(String channelName, JChannelFactory channelFactory) {
        this.channelName = channelName;
        this.channelFactory = channelFactory;
    }

    @Override
    public void run() {
        try {
            map = DistributedMap.Create(channelName, channelFactory);

            eventLoop();

            map.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        List<DistributedMapOperation> operations = Arrays.asList(
                new GetOperation(),
                new PutOperation(),
                new ContainsKeyOperation(),
                new RemoveOperation(),
                new PrintOperation()
        );

        while (true) {
            try {
                System.out.print("#> ");
                System.out.flush();

                String preInput = in.readLine();
                if (preInput == null) {
                    break;
                }
                String input = preInput.toLowerCase();

                if (input.startsWith("quit") || input.startsWith("exit")) {
                    break;
                }

                operations
                        .stream()
                        .sequential()
                        .filter(x -> x.matches(input))
                        .findFirst()
                        .ifPresent(x -> x.execute(map, input));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
