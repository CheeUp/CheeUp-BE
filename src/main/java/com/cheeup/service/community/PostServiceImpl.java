package com.cheeup.service.community;

import com.cheeup.apiPayload.code.error.codes.CommunityErrorCode;
import com.cheeup.apiPayload.code.error.codes.MemberErrorCode;
import com.cheeup.apiPayload.exception.handler.NotFoundException;
import com.cheeup.converter.community.CommentMapper;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    @Override
    public ReadPostDto.DetailResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommunityErrorCode.POST_NOT_FOUND));
//        List<Comment> commentList = post.getCommentList();
//        List<ReadCommentDto.ResponseDto> commentDtoList = new ArrayList<>();
//        for (Comment comment : commentList) {
//            commentDtoList.add(commentMapper.toCommentDto(comment));
//        }
//        post.setCommentList(commentList);
        return postMapper.toDetailResponseDto(post);
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
