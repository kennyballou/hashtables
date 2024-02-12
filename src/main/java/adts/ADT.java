package adts;

import java.util.Optional;

public interface ADT {
    void add(String key, int value);
    Optional<Integer> get(String key);
    Optional<Integer> remove(String key);
    boolean contains(String key);
}
