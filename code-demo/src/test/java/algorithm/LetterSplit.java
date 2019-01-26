package algorithm;

import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author ulei
 * @date 2019/1/26
 */
public class LetterSplit {

    //    private static final char[] letter = "vue...mysql..java.spring.....boot".toCharArray();
    private static final char[] letter = ".a.vue...mysql..java.spring.....b..a".toCharArray();
    private static final int aAscii = 97;
    private static final int zAscii = 122;

    public static void main(String[] args) {
        List<String> wordList = Lists.newArrayList();
        int start = 0;
        for (int i = 0; i < letter.length; i++) {
            if (isAToZ(letter[i])) {
                if (i > 0 && !isAToZ(letter[i - 1])) {
                    char[] word = ArrayUtils.subarray(letter, start, i);
                    wordList.add(String.valueOf(word));
                    start = i;
                } else {
                    continue;
                }
            }
            if (!isAToZ(letter[i])) {
                if (i > 0 && isAToZ(letter[i - 1])) {
                    char[] word = ArrayUtils.subarray(letter, start, i);
                    wordList.add(String.valueOf(word));
                    start = i;
                } else {
                    continue;
                }
            }
            if (i == letter.length - 1) {
                char[] lastWord = ArrayUtils.subarray(letter, start, letter.length);
                wordList.add(String.valueOf(lastWord));
                start = i;
            }

        }
        System.out.println(Arrays.toString(wordList.toArray()));
        for (int i = wordList.size() - 1; i >= 0; i--) {
            System.out.println(wordList.get(i));
        }
    }

    private static boolean isAToZ(char a) {
        return a >= aAscii && a <= zAscii;
    }
}
