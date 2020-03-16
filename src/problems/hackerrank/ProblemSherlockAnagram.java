package problems.hackerrank;


import java.io.IOException;
import java.util.*;

public class ProblemSherlockAnagram {
    static Map<String, Integer> map = new HashMap<>();

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j+i <= s.length(); j++) {
                String subStr = s.substring(j, j + i);
                if (!map.containsKey(subStr)) {
                    map.put(subStr, 0);
                } else {
                    map.put(subStr, map.get(subStr) + 1);
                }
            }
        }
        //printMap(map);
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            result += printPermutn(entry.getKey(), "");
            result += entry.getValue();
        }
        return result;
    }

    static int printPermutn(String str, String ans)
    {

        // If string is empty
        if (str.length() == 0) {
            //System.out.print(ans + " ");
            return map.get(ans) == null ? 0 : map.get(ans);
        }
        int res = 0;

        for (int i = 0; i < str.length(); i++) {

            // ith character of str
            char ch = str.charAt(i);

            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) +
                    str.substring(i + 1);

            // Recurvise call
            res += printPermutn(ros, ans + ch);
        }
        return res;
    }

    private static void printMap(Map<String, Integer> map) {
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(""+entry.getKey()+":"+entry.getValue());
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();*/
        System.out.print(sherlockAndAnagrams("ifailuhkqq"));

        scanner.close();
    }
}

