package org.smart.board.entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class Movie {

        private Long movieseq;
        private String usrid;
        private String title;
        private String content;
        private Long like;
        private Long hitcount;
        private Long dislike;
        private String regdate;

        private String originalfile;//추가
        private String savedfile;//추가

}

