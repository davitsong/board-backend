package org.smart.board.controller;
import org.smart.board.entity.Reply;
import org.smart.board.entity.Reply2;
import org.smart.board.service.Reply2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reply2")
public class Reply2Controller {

    @Autowired
    Reply2Service reply2Service;

    @ResponseBody
    @PostMapping("/reply2List")
    public List<Reply2> reply2List(Long movieseq) {

        List<Reply2> reply2List = reply2Service.list(movieseq);
        System.out.println("댓글목록 : " + reply2List);
        return reply2List;
    }

    @PostMapping("/reply2Write")
    @ResponseBody
    public String reply2Write(Reply2 reply, @AuthenticationPrincipal UserDetails user) {
        String loginId = user.getUsername();
        reply.setUsrid(loginId);
        System.out.println("----------------" + reply);
        int result = reply2Service.insert(reply);

        String message = "";
        if (result == 1) message = "댓글을 저장했습니다.";
        else if (result == 0) message = "댓글을 저장하지 못했습니다.";

        return message;
    }


    // 로그인한 사람의 아이디와, 댓글 쓴 사람의 아이디가 같을때만 삭제
    @PostMapping("/reply2Delete")
    @ResponseBody
    public String reply2Delete(Reply2 reply, @AuthenticationPrincipal UserDetails user) {
        System.out.println("삭제 하려는 데이터 "  + reply);
        String loginId = user.getUsername();
        Reply2 r = reply2Service.findOne(reply.getReplyseq());

        String message = "";
        if(!(loginId.equals(r.getUsrid()))) {
            message = "본인이 쓴 댓글만 삭제할 수 있습니다.";
        } else {
            int result = reply2Service.delete(reply.getReplyseq());
            if(result == 1) message = "삭제되었습니다.";
        }

        return message;
    }
    // 댓글 수정을 위해 1개의 데이터를 조회
    @ResponseBody
    @PostMapping("/reply2Find")
    public Reply2 reply2Find(Long replyseq, @AuthenticationPrincipal UserDetails user) {
        String loginId = user.getUsername();    // 로그인 정보
        Reply2 reply = reply2Service.findOne(replyseq);

        if(loginId.equals(reply.getUsrid()))
            return reply;

        return null;
    }

    // 댓글 수정
    @ResponseBody
    @PostMapping("/reply2Update")
    public String reply2Update(Reply2 reply) {
        int result = reply2Service.update(reply);
        String message = null;

        if(result == 1) {
            message = "수정 완료";
        } else {
            message ="수정 오류";
        }

        return message;
    }
}
