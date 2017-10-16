/*
 *   Created by zview@qq.com on 2017/10/15.
 */

/*
* cut[i] 表示s[i]之前一共能够分成几个部分的回文？
* cut[i]= min{cut[j]}+1,其中s[j..i-1]是回文的
* 因此最后的结果是cut[n]
* 这里巧妙的使用了一个计算回文的算法：
* ishuiwen[m][n]=ishuiwen[m+1][n-1]&&s[m]==s[n]
* 对于回文进行了一个预处理
*
* http://www.lintcode.com/en/problem/palindrome-partitioning-ii/
* */
public class PalindromePartitioning2 {

    boolean ishuiwen[][];

    void ishuiwen(String s){
        ishuiwen=new boolean[s.length()][s.length()];

        for(int i=0;i<s.length();i++)
            ishuiwen[i][i]=true;
        for (int i = 0; i < s.length()-1; i++) {
            ishuiwen[i][i+1]=s.charAt(i)==s.charAt(i+1);
        }

        for (int i = s.length() - 3; i >= 0; i--) {
            for (int j = i + 2; j < s.length(); j++) {
                ishuiwen[i][j] = ishuiwen[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
    }
    public int minCut(String s) {


        ishuiwen(s);
        int cut[]=new int[s.length()+1];
        cut[0]=0;
        cut[1]=1;

        for (int i = 2; i <=s.length() ; i++) {
            int min=Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if(ishuiwen[j][i-1]){
                    min=Math.min(cut[j],min);
                }
            }
            cut[i]=min+1;
        }

        return cut[s.length()]-1;
    }
}
