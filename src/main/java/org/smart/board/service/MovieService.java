package org.smart.board.service;

import org.smart.board.entity.Board;
import org.smart.board.entity.Movie;

import java.util.List;

public interface MovieService {

    public List<Movie> list(Long movieseq);
    public int insert(Movie movie);
    public int delete(Long movieseq);
    public int update(Movie movie);
    public Movie findOne(Long movieseq);
    public int hitCount(Long movieseq);



    public int getMovieCount(String searchItem, String searchWord);
    // 게시글 전체 데이터 가져오기
    public List<Movie> findAll(int srow, int erow, String searchItem, String searchWord);

}
