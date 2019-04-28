package antelopes.kinoxp.controllers;

import antelopes.kinoxp.models.Movie;
import antelopes.kinoxp.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@Controller
public class MovieController {
    private final static String URL_PATH = "/movies";
    private MovieRepository movieRepository = new MovieRepository();

    @GetMapping(URL_PATH + "/addAMovie")
    public String addAMovie(){
        return "movies/addAMovie";
    }

    @PostMapping(URL_PATH + "/addAMovie")
    public String addAMovie(@RequestParam("name")String title,
                            @RequestParam("genre")String genre,
                            @RequestParam("ageLimit") String ageLimit){
        int age = Integer.parseInt(ageLimit);
        Movie movie = new Movie(title, genre, age);
        movieRepository.create(movie);
        return "redirect:/employees/employees";
    }





    @PostMapping(URL_PATH + "/delete")
    public String delete(Model model, @RequestParam("id")String id){
        try{
            int movieID = Integer.parseInt(id);
            model.addAttribute("was_deleted", movieRepository.delete(movieID));
        }catch (Exception ex){
            model.addAttribute("was_deleted", false);
        }
        model.addAttribute(movieRepository.getAll());
        return "movies/updateMovieList";
    }


    @GetMapping(URL_PATH + "/updateMovieList")
    public String updateMovieList(@RequestParam("id") int movieId, Model model) {

        System.out.println("get maps update");
        Movie movie = movieRepository.get(movieId);
        model.addAttribute("movie", movie);
        return "movies/updateMovieList";
    }

    @PostMapping(URL_PATH + "/updateMovieList")
    public String updateMovieList(@ModelAttribute Movie movie) {

        movieRepository.update(movie);
        return "redirect:/employees/employees";
    }



    @GetMapping(URL_PATH + "/get/{id}")
    public String getMovie(Model model, @PathVariable("id")String id){
        try{
            int movieID = Integer.parseInt(id);
            model.addAttribute("movie", movieRepository.get(movieID));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "movies/displayMovie";
    }




    @GetMapping("/movies/deleteMovie")
    public String deleteSnacks(@RequestParam("id") int movieId, Model model )
    {
        Movie movie = movieRepository.get(movieId);
        model.addAttribute("movie",movie);
        return "movies/deleteMovie";
    }

    @PostMapping("/movies/deleteMovie")
    public String deleteSnacks(@RequestParam("id") int movieId){
        movieRepository.delete(movieId);
        return "redirect:/employees/employees";
    }
}
