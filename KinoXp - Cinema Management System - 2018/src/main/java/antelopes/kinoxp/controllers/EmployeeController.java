package antelopes.kinoxp.controllers;

import antelopes.kinoxp.models.Employee;
import antelopes.kinoxp.models.Movie;
import antelopes.kinoxp.models.Snack;
import antelopes.kinoxp.models.Schedule;
import antelopes.kinoxp.repositories.EmployeeRepository;
import antelopes.kinoxp.repositories.MovieRepository;
import antelopes.kinoxp.repositories.SnackRepository;
import antelopes.kinoxp.repositories.ScheduleRepository;
import antelopes.kinoxp.utilities.ActiveUser;
import antelopes.kinoxp.utilities.PasswordHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class EmployeeController {
    private static final String URL_PATH = "/employees";
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private SnackRepository snackRepository= new SnackRepository();
    private MovieRepository movieRepository = new MovieRepository();
    private ScheduleRepository scheduleRepository = new ScheduleRepository();

    @GetMapping(URL_PATH + "/login")
    public String login(){
        if(ActiveUser.isLoggedIn())
            return "redirect:/employees/employees";
        return "employees/login";
    }

    @PostMapping(URL_PATH + "/login")
    public String login(@RequestParam("password")String password){
        Employee employee = employeeRepository.get("kinoxp");
        try{
            if(employee != null){
                if(PasswordHash.validatePassword(password, employee.getPassword())){
                    ActiveUser.login(employee);
                    return "redirect:/employees/employees";
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        // TODO create a link to the other page
        return "employees/login";
    }

    @GetMapping(URL_PATH + "/employees")
    public String movieList(Model model){
        // Check if the user is logged
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";
        List<Movie> movies = movieRepository.getAll();
        List<Movie> modelMovie = new LinkedList<>();
        for(Movie movie : movies){
            String path = FileController.getImagePath(movie.getId());
            movie.setImg_url(path);
            modelMovie.add(movie);
        }

        model.addAttribute("movies", modelMovie);
        return "employees/employees";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = URL_PATH + "/logout")
    public String logout(){
        ActiveUser.logout();
        return "index";
    }

    @GetMapping("/employees/snacksList")
    public String snacksList(Model model){
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        model.addAttribute("snacks", snackRepository.getAll());
        return "employees/snacksList";
    }


    @GetMapping("/employees/updateSnacks")
    public String updateSnacks(@RequestParam("id") int snacksId, Model model) {
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        Snack snack = snackRepository.get(snacksId);
        model.addAttribute("snack", snack);
        return "employees/updateSnacks";
    }
    @PostMapping("/employees/updateSnacks")
    public String updateSnacks(@ModelAttribute Snack snack) {
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        snackRepository.update(snack);
        return "redirect:/employees/snacksList";
    }

    @GetMapping("/employees/deleteSnacks")
    public String deleteSnacks(@RequestParam("id") int snackId, Model model )
    {
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        Snack snack = snackRepository.get(snackId);
        model.addAttribute("snack",snack);
        return "employees/deleteSnacks";
    }

    @PostMapping("/employees/deleteSnacks")
    public String deleteSnacks(@RequestParam("id") int snackId){
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        snackRepository.delete(snackId);
        return "redirect:/employees/snacksList";
    }


    @GetMapping("/employees/addSnacks")
    public String addSnacks(){
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        return "employees/addSnacks";
    }

    @PostMapping("/employees/addSnacks")
    public String addSnacks(@RequestParam("name")String name,
                           @RequestParam("price")String price){
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        int p = Integer.parseInt(price);
        Snack snack = new Snack(name, p);
        snackRepository.create(snack);
        return "redirect:/employees/snacksList";
    }


    @GetMapping("/employees/employeeSchedule")
    public String employeeSchedule(){
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";

        return "employees/employeeSchedule";
    }

    @GetMapping("/employees/schedule")
    public String schedule(Model model){
        if(!ActiveUser.isLoggedIn())
            return "redirect:/employees/login";
        model.addAttribute("scheduleList", scheduleRepository.getAll());
        return "employees/schedule";
    }

    @GetMapping("/employees/updateSchedule")
    public String updateSchedule(Model model) {
        model.addAttribute("scheduleList", scheduleRepository.getAll());
        return "employees/updateSchedule";
    }

    @PostMapping("/employees/updateSchedule")
    public String updateSchedule(Model model, @ModelAttribute("employee") Schedule schedule) {
        scheduleRepository.update(schedule);
        model.addAttribute("scheduleList", scheduleRepository.getAll());
        return "redirect:/employees/updateSchedule";
    }

}
