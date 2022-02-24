package org.smart.board.entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Reply2 {
    private Long replyseq;
    private Long movieseq;
    private String usrid;
    private String replytext;
    private String regdate;
}
