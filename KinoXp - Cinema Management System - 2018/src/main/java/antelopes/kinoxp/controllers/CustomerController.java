package antelopes.kinoxp.controllers;

import antelopes.kinoxp.models.*;
import antelopes.kinoxp.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class CustomerController{

    private Repository<Movie> movieRepository;
    private Repository<Reservation> reservationRepository;
    private Repository<Snack> snackRepository;
    private Repository<Seat> seatRepository;
    private Repository<Customer> customerRepository;

    public CustomerController(){
        movieRepository = new MovieRepository();
        reservationRepository= new ReservationRepository();
        snackRepository = new SnackRepository();
        seatRepository = new SeatRepository();
        customerRepository = new CustomerRepository();
    }

    @GetMapping("/customers/movieList")
    public String movieList(Model model) {

        List<Movie> movies = movieRepository.getAll();
        List<Movie> modelMovie = new LinkedList<>();
        for(Movie movie : movies){
            String path = FileController.getImagePath(movie.getId());
            movie.setImg_url(path);
            modelMovie.add(movie);
        }

        model.addAttribute("movies", modelMovie);
        return "customers/movieList";
    }

    @GetMapping("/booking")
    public String booking(@RequestParam("id")int movieId, Model model)
    {
        System.out.println(movieId);
        model.addAttribute("movie", movieRepository.get(movieId));
        model.addAttribute("seats", SeatsArray.getSeats());
        /**
        model.addAttribute("seat", seatRepository.getAll());
        model.addAttribute("customer", customerRepository.getAll());
         **/
        model.addAttribute("reservation", reservationRepository.getAll());

        return "customers/booking";
    }

    @PostMapping("/booking")
    public String booking(@RequestParam("movieID")int movieID,
                          @RequestParam("customerName")String customerName,
                          @RequestParam("date")String date,
                          @RequestParam("seat")String seat){
        date = date.replace("/", "-");
        Movie movie = new Movie(movieID);
        System.out.println(movieID);
        Reservation reservation = new Reservation(movie, date, seat, customerName);
        reservationRepository.create(reservation);
        return "redirect:/customers/movieList";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") int movieId, Model model){
        model.addAttribute("movie", movieRepository.get(movieId));
        return"customers/movieDetails";

    }

    @GetMapping("/customers/snacks")
    public String snacks(Model model){
        model.addAttribute("snacks", snackRepository.getAll());
        return "customers/snacks";
    }
}
