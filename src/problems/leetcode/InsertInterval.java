package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        List<int[]> intervalList = new ArrayList();
        int n = intervals.length;
        int[] curInterval = newInterval;
        intervalList.add(curInterval);

        int i = 0;
        for(;i < n; i++) {
            curInterval = intervalList.remove(intervalList.size()-1);
            int[] interval = intervals[i];
            if(curInterval[1] < interval[0]) {
                //insert curInterval
                //insert interval
                //probably break
                intervalList.add(curInterval);
                intervalList.add(interval);
                i++;
                break;
            } else if(curInterval[0] > interval[1]) {
                intervalList.add(interval);
            } else {
                merge(curInterval, interval);
            }
            intervalList.add(curInterval);
        }
        while(i<n) {
            //insert remaining intervals
            intervalList.add(intervals[i]);
            i++;
        }
        int[][] result = new int[intervalList.size()][2];
        i = 0;
        for(int[] a : intervalList) {
            result[i] = a;
            i++;
        }
        return result;
    }

    private void merge(int[] curInterval, int[] interval) {
        if(curInterval[0] < interval[0] && curInterval[1] <= interval[1]) {
            //merge curInterval and interval
            //make curInterval the result of merger
            curInterval[1] = interval[1];
        } else if(curInterval[0] >= interval[0] && curInterval[1] <= interval[1]) {
            //merge curInterval and interval
            //make curInterval the result of merger
            curInterval[0] = interval[0];
            curInterval[1] = interval[1];
        } else if(curInterval[0] < interval[0] && curInterval[1] > interval[1]) {
            //merge curInterval and interval
            //curInterval does not change after merger
        } else if(curInterval[0] <= interval[1] && curInterval[1] > interval[1]) {
            //merge curInterval and interval
            //make curInterval the result of merger
            curInterval[0] = interval[0];
        }
    }



    public static void main(String[] args) {
        InsertInterval obj = new InsertInterval();
        //int[][] res = obj.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8});
        int[][] res = obj.insert(new int[][]{}, new int[]{6,8});
        for(int i = 0; i < res.length; i++) {
            System.out.print("["+res[i][0]+","+res[i][1]+"],");
        }
    }
}
