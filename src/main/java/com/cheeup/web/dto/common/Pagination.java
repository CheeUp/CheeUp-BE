package com.cheeup.web.dto.common;

import lombok.Builder;

@Builder
public record Pagination(
        int currentPage,
        int pageSize,
        long totalCount,
        int totalPages
) {
}
