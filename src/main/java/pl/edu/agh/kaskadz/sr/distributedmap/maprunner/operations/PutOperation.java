package pl.edu.agh.kaskadz.sr.distributedmap.maprunner.operations;

import pl.edu.agh.kaskadz.sr.distributedmap.map.DistributedMap;

public class PutOperation implements DistributedMapOperation {
    @Override
    public boolean matches(String input) {
        return input.startsWith("put");
    }

    @Override
    public void execute(DistributedMap map, String input) {
        String key = input.split("\\s+")[1];
        Integer value = Integer.parseInt(input.split("\\s+")[2]);
        map.put(key, value);
    }
}
