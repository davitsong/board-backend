package org.smart.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.smart.board.entity.Reply;
import org.smart.board.entity.Reply2;

import java.util.List;

@Mapper
public interface Reply2Dao {
    public List<Reply2> list(Long movieseq);
    public int insert(Reply2 reply);
    public Reply2 findOne(Long replyseq);
    public int delete(Long replyseq);
    public int update(Reply2 reply);

}
