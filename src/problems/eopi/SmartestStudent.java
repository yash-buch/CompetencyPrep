package problems.eopi;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SmartestStudent {
    public String smartStudent(String[] ids, int[] scores) {
        String id = null;
        double avg = 0;
        Map<String, int[]> map = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            String _id = ids[i];
            int score = scores[i];

            if (!map.containsKey(_id)) {
                add(map, _id, score);
            } else {
                double _avg = update(map, _id, score);
                if (_avg > 0) {
                    if (_avg > avg) {
                        avg = _avg;
                        id = _id;
                    }
                }
            }
        }

        return id;
    }

    private void add(Map<String, int[]> map, String id, int score) {
        int[] arr = new int[] {score, -1, -1};
        map.put(id, arr);
    }

    private double update(Map<String, int[]> map, String id, int score) {
        int[] arr = map.get(id);
        int min = score;
        int idx = -1;
        for(int i = 0; i < 3; i++) {
            if (arr[i] < min) {
                min = arr[i];
                idx = i;
            }
        }

        if (idx != -1) {
            arr[idx] = score;
        }

        double avg = 0;
        for(int i = 0; i < 3; i++) {
            if (arr[i] == -1) return -1;
            avg += arr[i];
        }

        return avg/3;
    }


    @Test
    public void testSmartStudent() {
        SmartestStudent obj = new SmartestStudent();

        String[] str = new String[]{"a", "b", "a", "a", "c", "c", "c"};
        int[] arr = new int[]{0, 23, 100, 50, 50, 50, 51};

        String ret = obj.smartStudent(str, arr);

        assert ret.equals("c");
    }
}
