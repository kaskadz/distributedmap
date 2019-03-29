package pl.edu.agh.kaskadz.sr.distributedmap.map;

public interface SimpleStringMap {
    boolean containsKey(String key);

    Integer get(String key);

    void put(String key, Integer value);

    Integer remove(String key);
}
