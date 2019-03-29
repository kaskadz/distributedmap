package pl.edu.agh.kaskadz.sr.distributedmap.maprunner.operations;

import pl.edu.agh.kaskadz.sr.distributedmap.map.DistributedMap;

public class PrintOperation implements DistributedMapOperation {
    @Override
    public boolean matches(String input) {
        return input.startsWith("print");
    }

    @Override
    public void execute(DistributedMap map, String input) {
        map.printContents();
    }
}
