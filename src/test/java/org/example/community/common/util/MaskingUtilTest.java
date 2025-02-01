package org.example.community.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MaskingUtilTest {

  @Test
  void maskName() {
    assertThat(MaskingUtil.maskName("")).isEmpty();
    assertThat(MaskingUtil.maskName("김")).isEqualTo("김");
    assertThat(MaskingUtil.maskName("김모")).isEqualTo("김*");
    assertThat(MaskingUtil.maskName("김모모모")).isEqualTo("김***");
  }
}