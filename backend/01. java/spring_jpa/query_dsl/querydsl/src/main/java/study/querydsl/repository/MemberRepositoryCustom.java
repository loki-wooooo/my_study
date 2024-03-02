package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(final MemberSearchCondition memberSearchCondition) throws Exception;

    // 단순쿼리
    Page<MemberTeamDto> searchPageSimple(final MemberSearchCondition condition, final Pageable pageable) throws Exception;

    // 카운트랑, 데이터 쿼리 별
    Page<MemberTeamDto> searchPageComplex(final MemberSearchCondition condition, final Pageable pageable) throws Exception;


}
