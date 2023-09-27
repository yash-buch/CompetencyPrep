package utilities.datastructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    Map<K, Item<K,V>> map;
    Item<K,V> head = null;
    Item<K,V> tail = null;
    int capacity = 0;

    public LRUCache(int maxCapacity) {
        capacity = maxCapacity;
        map = new HashMap<>();
    }

    public void insert(K key, V val) {
        if (map.containsKey(key)) {
            Item<K,V> item = map.get(key);
            item.val = val;
            remove(item);
            add(item);
        } else {
            if (map.size() == capacity) {
                map.remove(tail.key);
                remove(tail);
            }
            Item<K,V> item = new Item<>(key, val);
            map.put(key, item);
            add(item);
        }
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Item<K,V> item = map.get(key);
        remove(item);
        add(item);
        return item.val;
    }

    public int size() {
        return map.size();
    }

    private void remove(Item<K,V> item) {
        if (item.prev != null) {
            item.prev.next = item.next;
        }
        if (item.next != null) {
            item.next.prev = item.prev;
        } else {
            tail = item.prev;
        }
        if (item.key == head.key) {
            head = item.next;
        }
        item.prev = null;
        item.next = null;
    }

    private void add(Item<K,V> item) {
        if (head == null) {
            head = item;
            tail = item;
        } else {
            item.next = head;
            head.prev = item;
            item.prev = null;
        }
    }

    class Item<K,V> {
        K key;
        V val;
        Item<K,V> prev;
        Item<K,V> next;
        public Item(K _key, V _val) {
            key = _key;
            val = _val;
        }
    }
}
