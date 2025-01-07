package com.cheeup.service.community;

import com.cheeup.apiPayload.code.error.codes.MemberErrorCode;
import com.cheeup.apiPayload.exception.handler.MemberException;
import com.cheeup.converter.community.MyPostMapper;
import com.cheeup.domain.community.Post;
import com.cheeup.domain.member.Member;
import com.cheeup.repository.comunity.MyPageRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.web.dto.common.Pagination;
import com.cheeup.web.dto.community.ReadMyPostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;
    private final MyPostMapper myPostMapper;

    @Override
    public ReadMyPostsDto.Response getMyPosts(long id, int page, int limit) {

        Page<Post> posts = getPostPagesByMemberId(id, page, limit);
        List<ReadMyPostsDto.PostResponse> list = posts.getContent().stream().map(
                post -> {
                    Member author = post.getMember();
                    ReadMyPostsDto.AuthorResponse authorDto = myPostMapper.toAuthorDto(author);

                    return myPostMapper.toPostDto(
                            post,
                            post.getBoard().getId(),
                            post.getCommentList().size(),
                            authorDto
                    );
                }
        ).toList();

        Pagination pagination = Pagination.builder()
                .currentPage(page)
                .totalPages(posts.getTotalPages())
                .totalCount(posts.getTotalElements())
                .pageSize(limit)
                .build();

        return myPostMapper.toDto(list, pagination);
    }

    private Page<Post> getPostPagesByMemberId(long id, int page, int limit) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        return myPageRepository.findAllByMember(
                member,
                PageRequest.of(page, limit)
        );
    }
}
