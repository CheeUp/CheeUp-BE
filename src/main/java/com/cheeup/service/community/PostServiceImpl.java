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
import com.cheeup.web.dto.community.ReadPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    @Override
    public ReadPostDto.DetailResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityErrorCode.POST_NOT_FOUND));
        return postMapper.toDetailResponseDto(post);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ReadPostDto.ListResponseDto> getPostList(Long boardId, int page, int limit) {
        // 존제하는 게시판인지 확인
        if (!boardRepository.existsById(boardId)) {
            throw new NotFoundException(CommunityErrorCode.BOARD_NOT_FOUND);
        }

        // 페이지네이션을 위한 Pageable 객체 생성
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Post> postPage = postRepository.findAllByBoardId(boardId, pageable);

        // Page에서 content (현재 페이지의 게시글 목록)을 가져와서 변환
        List<ReadPostDto.ListResponseDto> responseDtoList = postPage.getContent().stream()
                .map(post -> postMapper.toListResponseDto(post, post.getCommentList().size()))
                .collect(Collectors.toList());

        // 반환 타입을 Page로 감싸서 반환
        return new PageImpl<>(responseDtoList, pageable, postPage.getTotalElements());
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
