package adts;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class AssocData implements ADT {

    private class AD {
        final String key;
        final int value;

        AD(String key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s → %d", key, value);
        }
    }

    private List<AD> assoc;

    public AssocData() {
        this.assoc = new LinkedList<>();
    }

    public void add(String key, int value) {
        this.assoc.add(new AD(key, value));
    }

    public Optional<Integer> get(String key) {
        for (AD ad : this.assoc) {
            if (ad.key.equals(key)) {
                return Optional.of(ad.value);
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> remove(String key) {
        for (AD ad : this.assoc) {
            if (ad.key.equals(key)) {
                this.assoc.remove(ad);
                return Optional.of(ad.value);
            }
        }
        return Optional.empty();
    }

    public boolean contains(String key) {
        for (AD ad : this.assoc) {
            if (ad.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.assoc
            .stream()
            .map(ad -> ad.toString())
            .collect(Collectors.joining(", ", "{", "}"));
    }
}
