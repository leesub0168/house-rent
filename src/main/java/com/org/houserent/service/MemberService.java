package com.org.houserent.service;

import com.org.houserent.domain.Member;
import com.org.houserent.exception.DuplicateMemberException;
import com.org.houserent.exception.NonExistMemberException;
import com.org.houserent.exception.WrongPasswordException;
import com.org.houserent.repository.MemberRepository;
import com.org.houserent.service.dto.MemberDto;
import com.org.houserent.service.dto.MemberJoinDto;
import com.org.houserent.util.SHACryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * */
    @Transactional
    public Long joinMember(MemberJoinDto memberJoinDto) {
        validateDuplicateUserId(memberJoinDto.getUser_id());

        String encrypted_password = SHACryptoUtil.encrypt(memberJoinDto.getPassword());
        memberJoinDto.setEncryptedPassword(encrypted_password);

        return memberRepository.save(memberJoinDto.toEntity()).getId();
    }

    /**
     * 중복 아이디 체크
     * */
    public void validateDuplicateUserId(String userId) {
        memberRepository.findByUserIdAndWithdrawDateIsNull(userId)
                .ifPresent(m -> { throw new DuplicateMemberException("이미 존재하는 회원입니다.");});
    }

    /**
     * 회원 로그인
     * */
    public MemberDto login(String userId, String password) {
        Member member = memberRepository.findByUserIdAndWithdrawDateIsNull(userId)
                .orElseThrow(() -> new NonExistMemberException("존재하지 않는 회원입니다."));

        validatePassword(password, member.getPassword());

        return MemberDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();
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

    public void checkPasswordBeforeChangePassword(String userId, String password) {
        Member member = memberRepository.findByUserIdAndWithdrawDateIsNull(userId)
                .orElseThrow(() -> new NonExistMemberException("존재하지 않는 회원입니다."));

        validatePassword(password, member.getPassword());
    }
    

    /**
     * id로 회원 조회
     * */
    public MemberDto findMemberById(Long id) {
        return memberRepository.findById(id)
                .map(MemberDto::new)
                .orElseThrow(() -> new NonExistMemberException("존재하지 않는 회원입니다."));
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public void updateMemberInfo(MemberDto memberDto) {
        Member member = memberRepository.findByUserIdAndWithdrawDateIsNull(memberDto.getUser_id())
                .orElseThrow(() -> new NonExistMemberException("존재하지 않는 회원입니다."));

        member.updateMemberInfo(memberDto.getName(), memberDto.getEmail());
    }

    /**
     * 비밀번호 변경
     */
    @Transactional
    public void changePassword(String userId, String newPassword) {
        Member member = memberRepository.findByUserIdAndWithdrawDateIsNull(userId)
                .orElseThrow(() -> new NonExistMemberException("존재하지 않는 회원입니다."));

        String encrypted_password = SHACryptoUtil.encrypt(newPassword);
        member.updatePassword(encrypted_password);
    }


    /**
     * 회원 탈퇴
     */
    @Transactional
    public void withDrawMember(String userId, String password) {
        Member member = memberRepository.findByUserIdAndWithdrawDateIsNull(userId)
                .orElseThrow(() -> new NonExistMemberException("존재하지 않는 회원입니다"));

        validatePassword(password, member.getPassword());

        member.withDrawMember();
    }

    public MemberDto findMemberByUserId(String userId) {
        return memberRepository.findByUserIdAndWithdrawDateIsNull(userId)
                .map(MemberDto::new)
                .orElseThrow(() -> new NonExistMemberException("존재하지 않는 회원입니다"));
    }
}
