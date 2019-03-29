package pl.edu.agh.kaskadz.sr.distributedmap.maprunner.operations;

import pl.edu.agh.kaskadz.sr.distributedmap.map.DistributedMap;

public class RemoveOperation implements DistributedMapOperation {
    @Override
    public boolean matches(String input) {
        return input.startsWith("remove");
    }

    @Override
    public void execute(DistributedMap map, String input) {
        map.remove(input.split("\\s+")[1]);
    }
}
