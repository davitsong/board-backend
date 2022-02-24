package org.smart.board.service;

import org.smart.board.dao.ReplyDao;
import org.smart.board.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Override
    public int insert(Reply reply) {
        return replyDao.insert(reply);
    }

    @Override
    public List<Reply> list(Long boardseq) {
        return replyDao.list(boardseq);
    }

    @Override
    public Reply findOne(Long replyseq) {
        return replyDao.findOne(replyseq);
    }

    @Override
    public int delete(Long replyseq) {
        return replyDao.delete(replyseq);
    }

    @Override
    public int update(Reply reply) {
        return replyDao.update(reply);
    }
}

