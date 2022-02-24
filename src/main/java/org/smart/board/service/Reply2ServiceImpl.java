package org.smart.board.service;

import org.smart.board.dao.Reply2Dao;
import org.smart.board.dao.ReplyDao;
import org.smart.board.entity.Reply;
import org.smart.board.entity.Reply2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Reply2ServiceImpl implements Reply2Service {

    @Autowired
    private Reply2Dao reply2Dao;

    @Override
    public int insert(Reply2 reply) {
        return reply2Dao.insert(reply);
    }

    @Override
    public List<Reply2> list(Long movieseq) {
        return reply2Dao.list(movieseq);
    }

    @Override
    public Reply2 findOne(Long replyseq) {
        return reply2Dao.findOne(replyseq);
    }

    @Override
    public int delete(Long replyseq) {
        return reply2Dao.delete(replyseq);
    }

    @Override
    public int update(Reply2 reply) {
        return reply2Dao.update(reply);
    }
}

