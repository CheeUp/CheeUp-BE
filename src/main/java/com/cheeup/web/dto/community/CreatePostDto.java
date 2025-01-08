package com.cheeup.web.dto.community;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostDto() {
	public record RequestDto(
		@NotNull
		Long boardId,
		@Size(min = 1, max = 100) // 현재 sql 기준(너무 길어보임)
		String title,
		@Size(min = 1, max = 1000) // content 길이는 몇자로 제한하는지
		String content,
		@NotNull
		Boolean isAnonymous
	) {
	}
}
