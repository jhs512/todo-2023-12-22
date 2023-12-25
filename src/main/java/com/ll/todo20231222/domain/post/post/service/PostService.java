package com.ll.todo20231222.domain.post.post.service;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.post.post.entity.Post;
import com.ll.todo20231222.domain.post.post.repository.PostRepository;
import com.ll.todo20231222.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findByAuthor(Member author) {
        return postRepository.findByAuthor(author);
    }

    @Transactional
    public RsData<Post> write(Member author, String title, String body, boolean published) {
        Post post = Post.builder()
                .author(author)
                .title(title)
                .body(body)
                .published(published)
                .build();

        postRepository.save(post);

        return RsData.of("200-1", "글 작성 성공", post);
    }

    public List<Post> findByIsPublished(boolean published) {
        return postRepository.findByPublished(published);
    }
}
