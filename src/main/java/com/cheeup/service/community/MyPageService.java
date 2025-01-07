package com.cheeup.service.community;

import com.cheeup.web.dto.community.ReadMyPostsDto;

public interface MyPageService {

    ReadMyPostsDto.Response getMyPosts(long id, int page, int limit);
}
