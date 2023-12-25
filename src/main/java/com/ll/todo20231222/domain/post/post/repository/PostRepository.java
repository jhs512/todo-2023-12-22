package com.ll.todo20231222.domain.post.post.repository;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Member author);

    List<Post> findByPublished(boolean published);
}
