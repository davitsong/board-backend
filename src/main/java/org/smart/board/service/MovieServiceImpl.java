package org.smart.board.service;

import org.smart.board.dao.MovieDao;
import org.smart.board.entity.Board;
import org.smart.board.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements  MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public List<Movie> list(Long movieseq) {
        List<Movie> movieList = movieDao.list(movieseq);
        return movieList;
    }

    @Override
    public int insert(Movie movie) {
        int result = movieDao.insert(movie);
        return result;
    }

    @Override
    public int delete(Long movieseq) {
        int result = movieDao.delete(movieseq);
        return result;
    }

    @Override
    public int update(Movie movie) {
        int result = movieDao.update(movie);
        return result;
    }

    @Override
    public Movie findOne(Long movieseq) {
        Movie movie = movieDao.findOne(movieseq);
        return movie;
    }

    @Override
    public int hitCount(Long movieseq) {
        int hitcount = movieDao.hitCount(movieseq);
        return hitcount;
    }

    @Override
    public int getMovieCount(String searchItem, String searchWord) {
        Map<String, String> map = new HashMap<>();
        map.put("searchItem", searchItem);
        map.put("searchWord", searchWord);

        int totalRecordCount = movieDao.getMovieCount(map);

        return totalRecordCount;
    }


    @Override
    public List<Movie> findAll(int srow, int erow, String searchItem, String searchWord) {

        Map<String, Object> map = new HashMap<>();
        map.put("srow", srow);
        map.put("erow", erow);
        map.put("searchItem", searchItem);
        map.put("searchWord", searchWord);

        List<Movie> movieList = movieDao.findAll(map);

        return movieList;
    }
}




