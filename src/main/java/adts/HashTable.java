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

    @Override
    public String toString() {
        return Stream.of(this.table)
            .filter(kv -> kv != null)
            .map(kv -> kv.toString())
            .collect(Collectors.joining(", ", "{", "}"));
    }
}
