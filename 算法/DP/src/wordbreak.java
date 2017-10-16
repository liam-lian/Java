import java.util.Set;

/*
 *   Created by zview@qq.com on 2017/10/15.
 */


/*
* 给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。
*
* 这里breaks[i] 表示s[i]之前的字符串能否被切分
* 那么 breaks[i]=breaks[j] && s[j..i-1]在字典中
* 注意这里的内层循环只需要查找最长的单词长度就可以了
* 因此算法复杂度是  O(N*L) L是最大的单词长度
* */
public class wordbreak {

    public boolean wordBreak(String s, Set<String> dict) {


        int Maxlen = 0;
        for (String dic : dict) {
            if (dic.length() > Maxlen) {
                Maxlen = dic.length();
            }
        }

        int n = s.length();
        boolean[] breaks = new boolean[s.length() + 1];

        breaks[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = (i - Maxlen) > 0 ? i - Maxlen : 0; j < n; j++) {
                if (breaks[j] && dict.contains(s.substring(j, i))) {
                    breaks[i] = true;
                    break;
                }
            }

        }
        return breaks[n];
    }

}
