package problems.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while(index < words.length) {
            List<String> wordList = new ArrayList<>();
            int minSpaceCount = 1;
            int charUsed = 0;
            for(int i = index; i < words.length; i++, index++) {
                charUsed += words[i].length() + minSpaceCount;
                if(charUsed > maxWidth+1) {
                    result.add(formJustifiedString(wordList, maxWidth, false));
                    break;
                }
                wordList.add(words[i]);
                if(i == words.length-1) {
                    result.add(formJustifiedString(wordList, maxWidth, true));
                }
            }
        }

        return result;
    }

    private String formJustifiedString (List<String> words, int maxWidth, boolean isLastLine) {
        boolean shouldLeftAlign = words.size() == 1 || isLastLine;
        StringBuffer sb = new StringBuffer();
        if(shouldLeftAlign) {
            for(String word : words) {
                sb.append(word);
                sb.append(" ");
            }
            sb = new StringBuffer(sb.substring(0, sb.length()-1));
            for(int i = sb.length(); i < maxWidth; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        int numWords = words.size();
        int totalWordsLength = 0;
        for(String word : words) {
            totalWordsLength += word.length();
        }
        int numSpaces = maxWidth - totalWordsLength;
        List<String> spaces = buildSpaceArray(numSpaces, numWords);
        sb.append(words.get(0));
        sb.append(spaces.get(0));
        for(int i = 1; i < numWords-1; i++) {
            sb.append(words.get(i));
            sb.append(spaces.get(i));
        }
        sb.append(words.get(numWords-1));
        return sb.toString();
    }

    private List<String> buildSpaceArray(int maxSpaces, int size) {
        int actualSize = size-1;
        List<String> result = new ArrayList<>();
        List<StringBuffer> temp = new ArrayList<>();
        int index = 0;
        for(int i = 0; i < maxSpaces; i++, index = (index+1)%actualSize) {
            if(index >= temp.size()) {
                StringBuffer sb = new StringBuffer();
                sb.append(" ");
                temp.add(index, sb);
            } else {
                temp.get(index).append(" ");
            }
        }
        for(StringBuffer sb : temp) {
            result.add(sb.toString());
        }
        return result;
    }


    public static void main(String[] args) {
        TextJustification obj = new TextJustification();
        int maxWidth = 16;
        String[] words = new String[]{"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
        List<String> result = obj.fullJustify(words, maxWidth);
        for(String str : result) {
            System.out.println("|"+str+"|"+" "+str.length());
        }
    }

    @Test
    public void test_formJustifiedString() {
        TextJustification obj = new TextJustification();
        List<String> words = new ArrayList<>();
        int maxWidth = 16;
        boolean isLastLine = true;
        words.add("This");
        words.add("is");
        words.add("an");
        words.add("example");
        words.add("of");
        words.add("text");
        words.add("justification.");
        String result = obj.formJustifiedString(words, maxWidth, isLastLine);
        assert("justification.  ".equals(result));
    }

    @Test
    public void test_spaceArray() {
        TextJustification obj = new TextJustification();
        List<String> spaces = obj.buildSpaceArray(5, 4);
        assert(2 == spaces.get(0).length());
        assert(2 == spaces.get(1).length());
        assert(1 == spaces.get(2).length());
    }
}