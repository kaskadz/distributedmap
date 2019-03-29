package pl.edu.agh.kaskadz.sr.distributedmap.maprunner.operations;

import pl.edu.agh.kaskadz.sr.distributedmap.map.DistributedMap;

public interface DistributedMapOperation {
    boolean matches(String input);
    void execute(DistributedMap map, String input);
}
