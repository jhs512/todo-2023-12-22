package com.ll.todo20231222.domain.post.post.dto;

import com.ll.todo20231222.domain.post.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString(callSuper = true)
public class PostDto extends PostBaseDto {
    private String body;

    public PostDto(Post post) {
        super(post);
        this.body = post.getBody();
    }
}
