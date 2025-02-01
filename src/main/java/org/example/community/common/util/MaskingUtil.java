package org.example.community.common.util;

public class MaskingUtil {
  private MaskingUtil() {}

  public static String maskName(String name) {
    // 두 글자 이상일 경우만 마스킹 처리
    if (name == null || name.length() < 2) {
      return name;
    }

    int len = name.length();
    return name.charAt(0) + "*".repeat(len - 1);
  }
}
