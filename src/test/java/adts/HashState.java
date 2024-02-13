package adts;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.*;

@State(Scope.Thread)
public class HashState {
    Random rand = new Random(1234567890);
    HashTable table;

    @Setup
    public void prepare() {
        this.table = new HashTable(100);
        for (int i = 0; i < 100; i++) {
            this.table.add(String.format("%c%d", rand.nextInt(26) + 97, rand.nextInt(100)),
                rand.nextInt(1024));
        }
        this.table.add("foobar", 42);
    }
}
