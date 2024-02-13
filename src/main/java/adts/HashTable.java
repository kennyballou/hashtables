package adts;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
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
    private List<List<KV>> table;

    public HashTable() {
        this(10);
    }

    public HashTable(int size) {
        this.size = size;
        this.table = new ArrayList<>(this.size);
        for (int i = 0; i < this.size; i++) {
            this.table.add(new LinkedList<KV>());
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % this.size;
    }

    public void add(String key, int value) {
        int k = hash(key);
        table.get(k).add(new KV(key, value));
    }

    public Optional<Integer> get(String key) {
        int k = hash(key);
        for (KV kv : table.get(k)) {
            if (kv.key.equals(key)) {
                return Optional.of(kv.value);
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> remove(String key) {
        int k = hash(key);
        List<KV> list = table.get(k);
        for (KV kv : list) {
            if (kv.key.equals(key)) {
                list.remove(kv);
                return Optional.of(kv.value);
            }
        }
        return Optional.empty();
    }

    public boolean contains(String key) {
        int k = hash(key);
        for (KV kv : table.get(k)) {
            if (kv.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.table.stream()
            .filter(kv -> kv != null)
            .map(kv -> kv.toString())
            .collect(Collectors.joining(", ", "{", "}"));
    }
}
