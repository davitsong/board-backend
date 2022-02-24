package org.smart.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.smart.board.entity.Reply;

import java.util.List;

@Mapper
public interface ReplyDao {
    public List<Reply> list(Long boardseq);
    public int insert(Reply reply);
    public Reply findOne(Long replyseq);
    public int delete(Long replyseq);
    public int update(Reply reply);
}
