package problems.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FrogJump {
    public boolean canCross(int[] stones) {
        Map<Integer, Integer> map = new HashMap();
        for (int i : stones) {
            map.put(i, 0);
        }
        boolean res = map.containsKey(1) && jump(1, 1, stones[stones.length - 1], map);
        return res;
    }

    private boolean jump(int jumpLen, int curStone, int lastStone, Map<Integer, Integer> map) {
        if (curStone == lastStone) {
            map.put(lastStone, 1);
            return true;
        }
        if (map.get(curStone) != 0) {
            return map.get(curStone) == 1;
        }
        boolean result = false;
        if (jumpLen != 0 && map.containsKey(curStone + jumpLen)) {
            result = jump(jumpLen, curStone + jumpLen, lastStone, map);
        }
        if (!result && map.containsKey(curStone + jumpLen + 1)) {
            result = jump(jumpLen + 1, curStone + jumpLen + 1, lastStone, map);
        }
        if (!result && jumpLen - 1 != 0 && map.containsKey(curStone + jumpLen - 1)) {
            result = jump(jumpLen - 1, curStone + jumpLen - 1, lastStone, map);
        }

        map.put(curStone, result ? 1 : -1);
        return result;
    }

    @Test
    public void testFrogJump() {
        FrogJump obj = new FrogJump();
        int[] in = new int[]{0,1,3,6,10,13,15,18};
        boolean result = obj.canCross(in);
        assert result;
    }
}
