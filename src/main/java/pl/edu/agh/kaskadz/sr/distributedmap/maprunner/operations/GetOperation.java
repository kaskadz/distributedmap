package pl.edu.agh.kaskadz.sr.distributedmap.maprunner.operations;

import pl.edu.agh.kaskadz.sr.distributedmap.map.DistributedMap;

public class GetOperation implements DistributedMapOperation {
    @Override
    public boolean matches(String input) {
        return input.startsWith("get");
    }

    @Override
    public void execute(DistributedMap map, String input) {
        String key = input.split("\\s+")[1];
        System.out.printf("GET [%s] -> %d%n", key, map.get(key));
    }
}
