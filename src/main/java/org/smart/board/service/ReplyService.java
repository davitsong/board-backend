package org.smart.board.service;

import org.smart.board.entity.Board;
import org.smart.board.entity.Reply;

import java.util.List;

public interface ReplyService {
    public int insert(Reply reply);
    public List<Reply> list(Long boardseq);
    public Reply findOne(Long replyseq);
    public int delete(Long replyseq);
    public int update(Reply reply);
}
