package antelopes.kinoxp.utilities;

import antelopes.kinoxp.models.Employee;

public class ActiveUser {
    private static boolean loggedIn = false;
    private static Employee activeEmployee = null;

    public static Employee getEmployee() {
        if(loggedIn)
            return activeEmployee;
        return null;
    }

    public static boolean isLoggedIn(){
        return loggedIn;
    }

    public static void login(Employee employee){
        activeEmployee = employee;
        loggedIn = true;
    }

    public static Employee logout(){
        loggedIn = false;
        Employee holder = activeEmployee;
        activeEmployee = null;
        return holder;
    }
}
