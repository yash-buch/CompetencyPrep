package utilities.datastructures;

import java.util.Objects;

public class BincMap<K, V> {
    private Entry<K, V>[] data;
    private int size = 0;

    public V put(K key, V value) {
        if(key == null) {} // TODO handle this case
        int hash = key.hashCode();
        int i = indexFor(hash, data.length);
        for(Entry<K, V> e = data[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        //TODO: Add the item to data
        return null;
    }

    public V get(K key) {
        // TODO: Implementation pending
        return null;
    }

    public boolean contains(V value){
        // TODO: Implementation pending
        return false;
    }

    public boolean containsKey(K key) {
        // TODO: Implementation pending
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(int h) {
        h ^= (h >>>20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int indexFor(int h, int length) {
        return h & (length-1);
    }

    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        final int hash;

        Entry(K key, V value, Entry<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Entry) {
                if (Objects.equals(key, ((Entry<?,?>)o).getKey()) &&
                        Objects.equals(value, ((Entry<?,?>)o).getValue()))
                    return true;
            }
            return false;
        }
    }

    // TODO: Make this map iterable
}
