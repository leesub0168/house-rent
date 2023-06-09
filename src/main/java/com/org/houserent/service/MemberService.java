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
        if(member != null) throw new DuplicateMemberException("이미 존재하는 회원입니다.");
    }

    /**
     * 회원 로그인
     * */
    public MemberDto login(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) throw new NonExistMemberException("존재하지 않는 회원입니다");

        validatePassword(password, member.getPassword());

        return new MemberDto(member.getName(), member.getEmail());
    }

    /**
     * 비밀번호 일치 체크
     * */
    private void validatePassword(String password, String memberPassword) {
        String encrypt = SHACryptoUtil.encrypt(password);
        if (!memberPassword.equals(encrypt)) {
            throw new WrongPasswordException("잘못된 비밀번호 입니다.");
        }
    }

    /**
     * 비밀번호 변경전 패스워드 검증
     */

    public void checkPassword(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        if(member == null) throw new NonExistMemberException("존재하지 않는 회원입니다.");

        validatePassword(password, member.getPassword());
    }
    

    /**
     * id로 회원 조회
     * */
    public MemberDto findMemberById(Long id) {
        Member findMember = memberRepository.findById(id);
        if(findMember == null) throw new NonExistMemberException("존재하지 않는 회원입니다.");
        return new MemberDto(findMember);
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public void updateMemberInfo(MemberDto memberDto) {
        Member member = memberRepository.findByUserId(memberDto.getUser_id());
        if (member == null) throw new NonExistMemberException("존재하지 않는 회원입니다");
        member.updateMemberInfo(memberDto.getName(), memberDto.getEmail());
    }

    /**
     * 비밀번호 변경
     */
    @Transactional
    public void updatePassword(String userId, String newPassword) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) throw new NonExistMemberException("존재하지 않는 회원입니다");
        String encrypted_password = SHACryptoUtil.encrypt(newPassword);
        member.updatePassword(encrypted_password);
    }


    /**
     * 회원 탈퇴
     */
    @Transactional
    public void withDrawMember(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) throw new NonExistMemberException("존재하지 않는 회원입니다");
        validatePassword(password, member.getPassword());

        member.withDrawMember();
    }

    public MemberDto findMemberByUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) throw new NonExistMemberException("존재하지 않는 회원입니다");

        return new MemberDto(member);
    }
}
