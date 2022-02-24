package org.smart.board.controller;

import org.smart.board.entity.Board;
import org.smart.board.entity.Movie;
import org.smart.board.service.MovieService;
import org.smart.board.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @Value("${spring.servlet.multipart.location}")
    private String uploadPath; // c:/upload

//    @GetMapping("/movieList")
//    public String movieList(Long movieseq, Model model){
//        System.out.println(movieseq);
//        List<Movie> movieList = movieService.list(movieseq);
//        System.out.println("영화목록 : " + movieList );
//        model.addAttribute("movieList", movieList);
//        return "movie/movieList";
//
//    }

    @GetMapping("/movieList")
    public String movieList(@RequestParam(defaultValue="1") int currentPage,
                            @RequestParam(defaultValue="title") String searchItem,
                            @RequestParam(defaultValue="") String searchWord,
                            Model model,Long movieseq) {
        // DB에서 저장된 전체 글 개수를 얻어옴
        int totalRecordCount = movieService.getMovieCount(searchItem, searchWord); //
        PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
        int countPerPage = navi.getCountPerPage();

        int srow = 1 + (currentPage-1) * countPerPage;
        int erow = countPerPage + (currentPage-1) * countPerPage;

        List<Movie> movieList = movieService.findAll(srow, erow, searchItem, searchWord);

        model.addAttribute("movieList", movieList);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("navi", navi);
        model.addAttribute("srow", srow);
        model.addAttribute("erow", erow);

        return "movie/movieList";
    }



    @GetMapping("/movieWrite")
    public String movieWrite() {

        return "Movie/movieWrite";
    }

    @PostMapping("/movieWrite")
    public String movieWrite(Movie movie, MultipartFile attachFile, @AuthenticationPrincipal UserDetails user) {


        movie.setUsrid(user.getUsername()); // 로그인이 완료된 후 걷어낼 코드
        //System.out.println("===============" + movie);
        System.out.println(attachFile.getName());

        //파일첨부 됐다면
        if(!attachFile.isEmpty()){
            //업로드 폴더가 존재하는지 확인: 없으면 생성
            File path = new File(uploadPath);
            if(!path.isDirectory())
                path.mkdirs();

            String originalfile = attachFile.getOriginalFilename();

            //savedfile을 만듦
            String uuid = java.util.UUID.randomUUID().toString();

            String filename;
            String ext;
            String savedfile;

            int index = originalfile.indexOf('.');
            filename = originalfile.substring(0,index);

            //확장자 없는 경우
            if(index == -1){
                ext="";
            }
            //확장자가 있는경우 bts.jpg : 3위치에 . 이 있음
            else{
                ext=originalfile.substring(index+1);
            }

            savedfile = filename + "_" + uuid + "." + ext;

            System.out.println("==============" + originalfile + "," + savedfile);

            // 저 위의 경로에 savedfile을 저장하고, or, sa ==> board에 setting한다
            File serverFile = null; // 경로 + savedfile
            serverFile = new File(uploadPath + "/" + savedfile);

            // 파일저장하기
            try{
                attachFile.transferTo(serverFile);
            }catch (Exception e){
                e.printStackTrace();
            }

            movie.setOriginalfile(originalfile);
            movie.setSavedfile(savedfile);
        }
        movie.setUsrid(user.getUsername());
        movieService.insert(movie);


        return "redirect:/movie/movieList";
    }

    @GetMapping("/movieDelete")
    public String movieDelete(Long movieseq) {
        movieService.delete(movieseq);

        return "redirect:/movie/movieList";
    }

    /**
     * 영화 게시글 수정을 위한 화면 요청
     * @return
     */
    @GetMapping("/movieUpdate")
    public String movieUpdate(Long movieseq, Model model) {
        Movie movie = movieService.findOne(movieseq);

        model.addAttribute("movie", movie);

        return "movie/movieUpdate";
    }

    @PostMapping("/movieUpdate")
    public String movieUpdate(Movie movie){


        System.out.println(movie);// usrid, boardseq, title, content

        // DB
        //board.setUsrid("aaa"); // 완료된 후 걷어낼 코드
        movieService.update(movie);

        return "redirect:/movie/movieList";
    }

    @GetMapping("/movieDetail")
    public String movieDetail(Long movieseq, Model model) {
        // 1) DB에서 boardseq에 해당하는 하나의 글을 질의해옴
        // 1-1) 조회수 증가해야함
        int hitcount = movieService.hitCount(movieseq); // 조회수 증가
        Movie movie  = movieService.findOne(movieseq);

        // 2) Model에 저장
        model.addAttribute("movie", movie);

        // 3) view로 forward
        return "movie/movieDetail";
    }

    @GetMapping("/download")
    public String download(long movieseq, HttpServletResponse response){
        Movie movie = movieService.findOne(movieseq);

        String originalfile = movie.getOriginalfile();//사용자
        String savedfile = movie.getSavedfile();//HDD
        //System.out.println(board\); //파일명 들어 있는지 확인

        //실제저장된 Full path
        String fullpath = uploadPath + "/" + savedfile;

        try{
            response.setHeader("content-Disposition", "attachment; filename="
                    + URLEncoder.encode(originalfile,"UTF-8"));
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        FileInputStream filein = null; // 하드디스크에서 메모리로 적재
        ServletOutputStream fileout = null; //메모리에서 클라이언트로 보내기

        try{
            filein= new FileInputStream(fullpath);
            fileout= response.getOutputStream();
            FileCopyUtils.copy(filein,fileout);

            fileout.close();
            filein.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}
