package org.example.community.common.page;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Schema(description = "페이지 응답")
@Getter
@AllArgsConstructor
public class PageResponse<T> {
  private List<T> content;
  private int page;
  private int size;
  private int totalPages;
  private long totalElements;
  private boolean hasNext;

  public PageResponse(List<T> content, Page<?> page) {
    this.content = content;
    this.page = page.getNumber();
    this.size = page.getSize();
    this.totalPages = page.getTotalPages();
    this.totalElements = page.getTotalElements();
    this.hasNext = page.hasNext();
  }
}
