package com.ll.todo20231222.domain.post.post.entity;

import com.ll.todo20231222.domain.member.member.entity.Member;
import com.ll.todo20231222.global.jpa.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Post extends BaseTime {
    @ManyToOne
    private Member author;
    private String title;
    private String body;
    private boolean published;
}


