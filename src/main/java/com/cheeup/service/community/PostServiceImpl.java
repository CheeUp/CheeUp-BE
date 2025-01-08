package com.cheeup.service.community;

import com.cheeup.apiPayload.code.error.codes.CommunityErrorCode;
import com.cheeup.apiPayload.code.error.codes.MemberErrorCode;
import com.cheeup.apiPayload.exception.handler.NotFoundException;
import com.cheeup.converter.community.PostMapper;
import com.cheeup.domain.community.Board;
import com.cheeup.domain.community.Post;
import com.cheeup.domain.member.Member;
import com.cheeup.repository.community.BoardRepository;
import com.cheeup.repository.community.PostRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.web.dto.community.CreatePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public void getPost() {

    }

    @Override
    public void getPostList() {

    }

    @Override
    public void createPost(CreatePostDto.RequestDto requestDto) {
        Post post = postMapper.toEntity(requestDto);

        //ToDO: 인증/인가 구현 이후 member 찾아서 넣어주기
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException(MemberErrorCode.MEMBER_NOT_FOUND));

        Board board = boardRepository.findById(requestDto.boardId())
                .orElseThrow(() -> new NotFoundException(CommunityErrorCode.BOARD_NOT_FOUND));

        post.setMember(member);
        post.setBoard(board);

        postRepository.save(post);
    }

    @Override
    public void updatePost() {

    }

    @Override
    public void deletePost() {

    }
}
