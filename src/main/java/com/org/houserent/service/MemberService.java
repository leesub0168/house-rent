package com.org.houserent.service;

import com.org.houserent.domain.Member;
import com.org.houserent.exception.DuplicateMemberException;
import com.org.houserent.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long joinMember(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    /**
     * 중복 회원 검증 - 이름, 이메일 일치 여부 확인
     * */
    private void validateDuplicateMember(Member member) {
        List<Member> duplicateMembers =
                memberRepository.findByNameAndEmail(member.getName(), member.getEmail());
        if(!duplicateMembers.isEmpty()) {
            throw new DuplicateMemberException("이미 존재하는 회원입니다.");
        }
    }

//    public MemberDTO login(String id, String password) throws Exception {
//        return new MemberDTO();
//    }

}
