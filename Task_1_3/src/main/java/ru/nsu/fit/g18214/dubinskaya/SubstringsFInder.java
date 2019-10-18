package ru.nsu.fit.g18214.dubinskaya;

import java.io.IOException;
import java.io.Reader;

/*
 * class which receive string or file and substring which it should search,
 * and count and contain all substrings in the file or string.
 */
public class SubstringsFInder {

  private int numSubs;
  private int[] idEntry = new int[0];
  private int delta = 0;
  /*
   * Initialize class FindAllSubstring
   * @param frStr - File Reader that contain text
   * @param sub - String that class will find in frStr
   * if frStr or sub equals null func returnSubsID will return empty array
   */
  public SubstringsFInder(Reader frStr, String sub) throws IOException {
    if (frStr != null && sub != null) {
      char[] buffer = new char[sub.length() * 7];
      while (frStr.read(buffer, 0, buffer.length) > 0) {
        countNextStr(String.valueOf(buffer), sub);
        System.arraycopy(buffer, sub.length() * 6 + 1, buffer, 0, sub.length() - 1);
        delta -= sub.length();
      }
    } else {
      idEntry = new int[0];
      numSubs = 0;
    }
  }
  /*
   * Initialise class FindAllSubstring
   * @param str - String where substrings is finding
   * @param sub - String that class will find in frStr
   * if frStr or sub equals null func returnSubsID will return empty array
   */
  public SubstringsFInder(String str, String sub) {
    if (str != null && sub != null) countNextStr(str, sub);
    else{
      idEntry = new int[0];
      numSubs = 0;
    }
  }
  /*
   * @return int[] - array with id for each subs
   */
  public int[] returnSubsID() {
    return idEntry;
  }

  private int[] zFunction(String str, int subLen) {
    int[] zf;
    numSubs = 0;
    char[] s = str.toCharArray();
    int n = s.length;
    zf = new int[n];
    for (int i = 1; i < n; ++i) {
      while (i + zf[i] < n && s[zf[i]] == s[i + zf[i]]) zf[i]++;
      if (zf[i] >= subLen) numSubs++;
    }
    return zf;
  }

  private int[] findSubstr(int subLen, int sLen, int[] zf) {
    int[] subStr = new int[numSubs];
    int pos = 0;
    for (int i = subLen; i < sLen; ++i) {
      if (zf[i] >= subLen) subStr[pos++] = i - subLen + delta;
    }
    return subStr;
  }

  private void countNextStr(String str, String subStr) {
    String zfStr = subStr.concat(str);
    int[] ret = findSubstr(subStr.length(), zfStr.length(), zFunction(zfStr, subStr.length()));
    delta += str.length();
    add(ret);
  }

  private void add(int[] ret) {
    int[] array1and2 = new int[ret.length + idEntry.length];
    System.arraycopy(ret, 0, array1and2, idEntry.length, ret.length);
    System.arraycopy(idEntry, 0, array1and2, 0, idEntry.length);
    idEntry = array1and2;
  }
}
