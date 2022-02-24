package org.smart.board.service;

import org.smart.board.entity.Member;

public interface MemberService {
    // 회원가입
    public int insertMember(Member member);

    // 회원 조회 : return null 이어야 사용가능
    public Member findOne(String usrid);
}
