import java.util.Scanner;
import java.util.ArrayList;

/* Initially Login Menu:
* Create new User (2. Log in 3. Edit data (only if ada user)) Q. Exit (WILL DELETE ALL DATA)
*
* (POSSIBLE IDEA) 3. Calculate BMI - input weight and height and instantly get BMI then go back to menu
* 
* Create new User: register user data in the ArrayList of objects
* Log in: 
* - Choose from all the registered users(SHOULD WE USE PASSWORD?)
* - Display all relevant data (BMR, BMI, their registered data, etc)
* - 4 Options 1. Set Goals 2. Edit Data 3. Activity Log Q. Exit
*
* Set Goals: Set whether you want to gain/ lose/ maintain weight OR set how much calories you want in a day
* Edit data: Edit user data; all of everything
* Activity Log: A log for users to track activities done/ food eaten and how much calories burned/ gained doing so
*/

public class Program {
    double weight;
    double height;
    int age;
    String gender;
    String name;
    int calorieGoals;
    ArrayList<String> calorieCounter = new ArrayList<String>();
    ArrayList<String> calorieActivity = new ArrayList<String>();

    public Program(String name, double weight, double height, int age, String gender, int calorieGoals) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.calorieGoals = calorieGoals;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Program callFunction = new Program("", 0, 0, 0, "", 0);
        ArrayList<Program> listOfUsers = new ArrayList<Program>();

        String loginMenuChoice;
        String menuUserLoginChoice;
        int intMenuUserLoginChoice;

        projectName();

        do {
            loginMenuChoice = "";
            menuUserLoginChoice = "";
            intMenuUserLoginChoice = 0;

            loginMenuChoice = loginMenu(listOfUsers);
            switch (loginMenuChoice) {
                case "1": // register create new user
                    callFunction.registerUsers(listOfUsers);

                    break;
                case "2": // log in to existing user
                    for (int i = 0; i < listOfUsers.size(); i++) {
                        System.out.printf("%d. %s\tHeight: %.0f\n", i + 1, listOfUsers.get(i).name,
                                listOfUsers.get(i).height);
                    }
                    System.out.printf("Log in [1-%d]: ", listOfUsers.size());
                    while (!menuUserLoginChoice.matches("^[a-zA-Z!@#&()–[{}]:;',?/*~$^+=<>]")) {
                        menuUserLoginChoice = s.next();
                        if (Integer.parseInt(menuUserLoginChoice) - 1 < 0
                                || Integer.parseInt(menuUserLoginChoice) - 1 > listOfUsers.size()) {
                            System.out.println("\n!! Invalid Input !!\n");
                        } else {
                            intMenuUserLoginChoice = Integer.parseInt(menuUserLoginChoice) - 1;
                        }
                    }

                    // displayUserData(listOfUsers, intMenuUserLoginChoice);

                    break;

                case "q": // exit
                    break;
            }
        } while (!loginMenuChoice.equalsIgnoreCase("Q"));
        System.out.println("\n\nThank you for using");
        projectName();

    }

    static void projectName() {
        System.out.println("=======PROJECT NAME=======");
    }

    static String loginMenu(ArrayList<Program> listOfUsers) {
        Scanner s = new Scanner(System.in);
        String loginMenuChoice = "";

        if (listOfUsers.size() > 0) {
            while (!loginMenuChoice.equals("1") && !loginMenuChoice.equals("2")
                    && !loginMenuChoice.equalsIgnoreCase("q")) {
                loginMenuChoice = "";
                System.out.println("1. Create New User");
                System.out.println("2. Log In");
                System.out.print("Q. Exit\nInput: ");
                loginMenuChoice = s.next();
                if (!loginMenuChoice.equals("1") && !loginMenuChoice.equals("2")
                        && !loginMenuChoice.equalsIgnoreCase("q")) {
                    System.out.println("\n!! Invalid input !!\n");
                }
            }
        } else if (listOfUsers.size() == 0) {
            while (!loginMenuChoice.equals("1") && !loginMenuChoice.equalsIgnoreCase("q")) {
                loginMenuChoice = "";
                System.out.println("1. Create New User");
                System.out.print("Q. Exit\nInput: ");
                loginMenuChoice = s.next();
                if (!loginMenuChoice.equals("1") && !loginMenuChoice.equalsIgnoreCase("q")) {
                    System.out.println("\n!! Invalid input !!\n");
                }
            }
        }

        return loginMenuChoice;

    }

    public void registerUsers(ArrayList<Program> listOfUsers) {
        Scanner s = new Scanner(System.in);
        Program temp = new Program("", 0, 0, 0, "", 0);

        System.out.print("User Name: ");
        temp.name = s.next() + s.nextLine();

        System.out.print("Weight(kg): ");
        temp.weight = s.nextDouble();

        System.out.print("Height(cm): ");
        temp.height = s.nextDouble();

        do {
            System.out.print("Gender (F or M): ");
            temp.gender = s.next();
            if (!temp.gender.equalsIgnoreCase("f") && !temp.gender.equalsIgnoreCase("M")) {
                System.out.println("\n!! Input not recognized !!\n");
            }
        } while (!temp.gender.equalsIgnoreCase("f") && !temp.gender.equalsIgnoreCase("M"));

        System.out.print("Age: ");
        temp.age = s.nextInt();

        listOfUsers.add(temp);
    }

    // static void displayAndChooseUsers(ArrayList<Program> listOfUsers) {
    // Scanner s = new Scanner(System.in);

    // String menuUserLoginChoice = "";
    // int intMenuUserLoginChoice = 0;

    // }

    static double BMI(Program user) {
        double BMI;
        BMI = (double) user.weight / ((user.height / 100) * (user.height / 100));
        return BMI;
    }

    static double BMR(Program user) {
        double BMR = 0;

        if (user.gender.equalsIgnoreCase("m")) {
            BMR = (10 * user.weight) + (6.25 * user.height) - (5 * user.age) + 5;
        } else if (user.gender.equalsIgnoreCase("f")) {
            BMR = (10 * user.weight) + (6.25 * user.height) - (5 * user.age) - 161;
        }

        return BMR;
    }

    public void setGoals(Program user) {
        /*
         * first, want to maintain, lose, or gain weight;
         * or also just set a goal of calories they have for the whole day
         * if theyre underweight, suggest going to doctor if want to lose weight
         * if theyre obese, call them fat
         * gain -gain slow 300-500 calories + BMR
         * -gain faster 700-1000 calories + BMR
         * lose -
         * -
         * maintain -
         */

    }

    static void displayUserData(ArrayList<Program> listOfUsers, int intMenuUserLoginChoice) {

        System.out.printf("Height (cm): %5.0f\tAge: %3d", listOfUsers.get(intMenuUserLoginChoice).height,
                listOfUsers.get(intMenuUserLoginChoice).age);
        System.out.printf("Weight (kg): %3.2f\tGender: %s", listOfUsers.get(intMenuUserLoginChoice).weight,
                listOfUsers.get(intMenuUserLoginChoice).gender);
        System.out.printf("BMI: %.2f\tBMR: %.0f", BMI(listOfUsers.get(intMenuUserLoginChoice)),
                BMR(listOfUsers.get(intMenuUserLoginChoice)));
    }

    public void editUser() {

    }
}
