package com.flab.foodeats.service;


import com.flab.foodeats.mapper.MemberMapper;
import com.flab.foodeats.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class InsertService {

    private MemberMapper memberMapper;

    public InsertService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public ResponseEntity<?> insert(Member member) {

        try {

            memberMapper.save(member.getId(), member.getPassword(), member.getName());
            return new ResponseEntity<>(member, HttpStatus.OK);

        } catch (DuplicateKeyException e) {

            throw new DuplicateKeyException("이미 가입된 아이디 입니다");
        }
    }


}
