package com.cheeup.service.community;

import com.cheeup.web.dto.community.ReadMyPostsDto;

public interface MyPageService {

    ReadMyPostsDto.ResponseDto getMyPosts(long id, int page, int limit);
}
