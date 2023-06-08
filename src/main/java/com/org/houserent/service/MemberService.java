package com.org.houserent.service;

import com.org.houserent.domain.Member;
import com.org.houserent.exception.DuplicateMemberException;
import com.org.houserent.exception.NonExistMemberException;
import com.org.houserent.exception.WrongPasswordException;
import com.org.houserent.repository.MemberRepository;
import com.org.houserent.util.SHACryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * */
    @Transactional
    public Long joinMember(MemberDto memberDto) {
        validateDuplicateUserId(memberDto.getUser_id());
        String encrypted_password = SHACryptoUtil.encrypt(memberDto.getPassword());
        Member member = new Member(memberDto.getUser_id(), encrypted_password, memberDto.getName(), memberDto.getEmail(), LocalDateTime.now());
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 아이디 체크
     * */
    public void validateDuplicateUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);
        if(member != null) {
            throw new DuplicateMemberException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 로그인
     * */
    public MemberDto login(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) {
            throw new NonExistMemberException("존재하지 않는 회원입니다");
        }

        checkPassword(password, member.getPassword());

        return new MemberDto(member.getName(), member.getEmail());
    }

    /**
     * 비밀번호 일치 체크
     * */
    private void checkPassword(String password, String memberPassword) {
        String encrypt = SHACryptoUtil.encrypt(password);
        if (!memberPassword.equals(encrypt)) {
            throw new WrongPasswordException("잘못된 비밀번호 입니다.");
        }
    }

    /**
     * id로 회원 조회
     * */
    public MemberDto findMember(Long id) {
        Member findMember = memberRepository.findById(id);

        return new MemberDto(findMember);
    }
    
    /**
     * 회원 정보 수정
     * */


    /**
     * 회원 탈퇴
     */
    public void withDrawMember(String userId, String password) {
        String encrypted_password = SHACryptoUtil.encrypt(password);
        Member member = memberRepository.findByUserIdAndPassword(userId, encrypted_password);
        if (member == null) {
            throw new NonExistMemberException("존재하지 않는 회원입니다");
        }
        member.withDrawMember();
    }

}
