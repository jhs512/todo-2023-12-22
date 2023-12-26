package com.ll.todo20231222.domain.post.post.dto;

import com.ll.todo20231222.domain.post.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
public abstract class PostBaseDto {
    @NonNull
    private long id;
    @NonNull
    private LocalDateTime createDate;
    @NonNull
    private LocalDateTime modifyDate;
    @NonNull
    private long authorId;
    @NonNull
    private String authorUsername;
    @NonNull
    private String title;

    public PostBaseDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.authorId = post.getAuthor().getId();
        this.authorUsername = post.getAuthor().getUsername();
        this.title = post.getTitle();
    }
}
