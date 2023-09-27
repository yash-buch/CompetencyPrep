package utilities.datastructures.tests;

import org.junit.Test;
import utilities.datastructures.LRUCache;

public class LRUCacheTest {
    @Test
    public void testInsertSuccessI() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.insert(0, "abc");
        assert cache.size() == 1;
        String val = cache.get(0);
        assert "abc".equals(val);
    }

    @Test
    public void testInsertSuccessII() {
        LRUCache<Integer, String> cache = new LRUCache<>(1);
        cache.insert(0, "abc");
        cache.insert(1, "def");
        assert cache.size() == 1;
        String val = cache.get(0);
        assert null == val;
        val = cache.get(1);
        assert "def".equals(val);
    }

    @Test
    public void testInsertSuccessIII() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.insert(0, "abc");
        cache.insert(1, "def");
        assert cache.size() == 2;
        String val = cache.get(1);
        assert "def".equals(val);
        val = cache.get(0);
        assert "abc".equals(val);
    }
}
