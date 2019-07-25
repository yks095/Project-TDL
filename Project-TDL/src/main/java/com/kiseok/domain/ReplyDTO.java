package com.kiseok.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDTO {

    private Long idx;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public ReplyDTO (Reply reply)   {
        this.idx = reply.getIdx();
        this.content = reply.getContent();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();

    }

}
