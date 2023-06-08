package com.org.houserent.service;

import com.org.houserent.domain.Member;
import com.org.houserent.exception.DuplicateMemberException;
import com.org.houserent.exception.WrongPasswordException;
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
    public Long joinMember(MemberDto memberDto) {
        validateDuplicateUserId(memberDto.getUser_id());
        Member member = new Member(memberDto.getUser_id(), memberDto.getPassword(), memberDto.getName(), memberDto.getEmail());
        memberRepository.save(member);
        return member.getId();
    }
    /**
     * 중복 아이디 체크
     * */
    private void validateDuplicateUserId(String userId) {
        List<Member> duplicateMembers =
                memberRepository.findByUserId(userId);
        if(!duplicateMembers.isEmpty()) {
            throw new DuplicateMemberException("이미 존재하는 회원입니다.");
        }
    }

    public MemberDto login(String userId, String password) throws Exception {
        List<Member> members = memberRepository.findByUserId(userId);
        if (members.size() > 1) {
            throw new DuplicateMemberException("중복된 회원이 존재합니다.");
        }

        Member member = members.get(0);

        validatePassword(password, member.getPassword());

        return new MemberDto(member);
    }

    private void validatePassword(String password, String memberPassword) throws Exception {
        if (!memberPassword.equals(password)) {
            throw new WrongPasswordException("잘못된 비밀번호 입니다.");
        }
    }

    public MemberDto findMember(Long id) {
        Member findMember = memberRepository.findById(id);

        return new MemberDto(findMember);
    }
}
