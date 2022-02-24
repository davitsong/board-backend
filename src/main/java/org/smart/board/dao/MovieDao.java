package org.smart.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.smart.board.entity.Board;
import org.smart.board.entity.Movie;

import java.util.List;
import java.util.Map;


@Mapper
public interface MovieDao {

    // 특정 movieseq에 해당하는 모든 내용
    public List<Movie> list(Long movieseq);

    // movie 삽입
    public int insert(Movie movie);

    // moive 삭제
    public int delete(Long movieseq);

    // movie upadate를 위한 선택
    public Movie findOne(Long movieseq);

    // movie upadae
    public int update(Movie movie);

    //hitcount counting
    public int hitCount(Long movieseq);

    public int getMovieCount(Map<String, String> map);

    public List<Movie> findAll(Map<String, Object> map);

}





