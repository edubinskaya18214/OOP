package ru.nsu.fit.g18214.dubinskaya;

public class zFunction {
  static private int numSubs;

  static public int[] zFunction(String str, int subLen) {
    int[] zf;
    numSubs = 0;
    char[] s = str.toCharArray();
    int n = s.length;
    zf = new int[n];
    for (int i = 1; i < n; ++i) {
      while (i + zf[i] < n && s[zf[i]] == s[i + zf[i]])
        zf[i]++;
      if (zf[i] >= subLen) numSubs++;
    }
    return zf;
  }

  static private int[] findSubstr(int subLen, int sLen, int[] zf, int delta) {
    int[] subStr = new int[numSubs];
    int pos = 0;
    for (int i = subLen; i < sLen; ++i) {
      if (zf[i] >= subLen) subStr[pos++] = i - subLen + delta;
    }
    return subStr;
  }

  static public int[] returnNumSubs(String str, String subStr, int delta) {
    String zfStr = subStr.concat(str);
    return (findSubstr(subStr.length(), zfStr.length(), zFunction(zfStr, subStr.length()),delta));
  }

  static public int[] returnNumSubs(String str, String subStr) {
    String zfStr = subStr.concat(str);
    return (findSubstr(subStr.length(), zfStr.length(), zFunction(zfStr, subStr.length()), 0));
  }
}
