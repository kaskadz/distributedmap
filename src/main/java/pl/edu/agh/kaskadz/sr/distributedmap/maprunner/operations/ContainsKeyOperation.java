package pl.edu.agh.kaskadz.sr.distributedmap.maprunner.operations;

import pl.edu.agh.kaskadz.sr.distributedmap.map.DistributedMap;

public class ContainsKeyOperation implements DistributedMapOperation {
    @Override
    public boolean matches(String input) {
        return input.startsWith("containsKey") || input.startsWith("contains");
    }

    @Override
    public void execute(DistributedMap map, String input) {
        String key = input.split("\\s+")[1];
        System.out.printf("CONTAINS [%s]: %s%n", key, map.containsKey(key));
    }
}
