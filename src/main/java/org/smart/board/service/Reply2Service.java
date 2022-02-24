package org.smart.board.service;

import org.smart.board.entity.Board;
import org.smart.board.entity.Reply2;

import java.util.List;

public interface Reply2Service {
    public int insert(Reply2 reply);
    public List<Reply2> list(Long boardseq);
    public Reply2 findOne(Long replyseq);
    public int delete(Long replyseq);
    public int update(Reply2 reply);
}
