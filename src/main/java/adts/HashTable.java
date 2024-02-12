package adts;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class HashTable implements ADT {

    class KV {
        final String key;
        final int value;

        KV(String key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof KV)) {
                return false;
            } else {
                return equals((KV)o);
            }
        }

        public boolean equals(KV o) {
            return o != null && this.key.equals(o.key);
        }

        @Override
        public String toString() {
            return String.format("%s → %d", key, value);
        }
    }

    private final int size;
    private KV[] table;

    public HashTable() {
        this(10);
    }

    public HashTable(int size) {
        this.size = size;
        this.table = new KV[size];
    }

    private int hash(String key) {
        return key.hashCode() % this.size;
    }

    public void add(String key, int value) {
        int k = hash(key);
        table[k] = new KV(key, value);
    }

    public Optional<Integer> get(String key) {
        int k = hash(key);
        return Optional.ofNullable(table[k])
            .map(kv -> kv.value);
    }

    public Optional<Integer> remove(String key) {
        int k = hash(key);
        Optional<Integer> value = Optional.ofNullable(table[k])
            .map(kv -> kv.value);
        table[k] = null;
        return value;
    }

    public boolean contains(String key) {
        int k = hash(key);
        return table[k] != null;
    }

    @Override
    public String toString() {
        return Stream.of(this.table)
            .filter(kv -> kv != null)
            .map(kv -> kv.toString())
            .collect(Collectors.joining(", ", "{", "}"));
    }
}
