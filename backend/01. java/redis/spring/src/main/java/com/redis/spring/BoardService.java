package com.redis.spring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 캐시 추가
    // page, size -> 유동적인 값 변경
    // rediscacheconfig -> boardCacheManager bean 객체
    // cacheaside 전략

    /**
     * - `cacheNames` : 캐시 이름을 설정
     * - `key` : Redis에 저장할 Key의 이름을 설정
     * - `cacheManager` : 사용할 `cacheManager`의 Bean 이름을 지정
     * */
    @Cacheable(
            cacheNames = {"getBoards"}
            , key = "'boards.page:'+ #page + ':size:' + #size"
            , cacheManager = "boardCacheManager"
    )
    public List<Board> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Board> pageOfBoards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);
        return pageOfBoards.getContent();
    }
}

