package docurepo.view;

import docurepo.controller.UserController;
import java.util.Scanner;

public class CommandLineInterface {
    public static void Run(){
        ClearScreen();
        UserController.Init();
        UserController userController = UserController.Instance;
        System.out.println("SELAMAT DATANG PADA APLIKASI DOCUREPO!\n");

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        boolean login = false;
        String line_prefix;
        while (!exit) {
            
            if (!login) {
                System.out.print("Username: ");
                String username = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                login = userController.IsUserValid(username, password);
                if (login) {
                    ClearScreen();
                    // line_prefix
                }
            } else {
                System.out.print("<DOCUREPO> ");
                int command = GetCommand(sc);
                switch(command){
                    case -1:
                        exit = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static int GetCommand(Scanner sc){
        String input = sc.nextLine();
        if (input.equals("exit"))
            return -1;
        return 0;
    }

    private static void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // private static void SetLinePrefix()
}