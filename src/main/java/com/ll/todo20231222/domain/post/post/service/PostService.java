package com.ll.todo20231222.domain.post.post.service;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.post.post.entity.Post;
import com.ll.todo20231222.domain.post.post.repository.PostRepository;
import com.ll.todo20231222.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public boolean canRead(Member actor, Post post) {
        return post.isPublished() || actor.equals(post.getAuthor());
    }

    public List<Post> findByPublished(boolean b) {
        return postRepository.findByPublishedOrderByIdDesc(b);
    }

    public List<Post> findByAuthor(Member author) {
        return postRepository.findByAuthorOrderByIdDesc(author);
    }
}
