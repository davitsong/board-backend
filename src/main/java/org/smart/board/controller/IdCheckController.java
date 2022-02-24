package org.smart.board.controller;

import org.smart.board.entity.Member;
import org.smart.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IdCheckController {
    @Autowired
    MemberService memberService;

    @PostMapping("/account/idCheck")
    @ResponseBody
    public boolean idCheck(String usrid) {
        Member member = memberService.findOne(usrid);
        if(member == null)
            return true;

        return false; // 사용불가능한 아이디
    }

}
