package Mental_Health_Simulation;

import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;
public class Executer{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, what is your name ?");
        String name = sc.nextLine();
        user user = new user(name);
        Assistant assistant = new Assistant(user);
        while(true){
            System.out.println("***Checking Menue***");
            System.out.println("1. Strat a session");
            System.out.println("2. Show mood history");
            System.out.println("3. Terminate the session");
            String ch = sc.nextLine();

            switch (ch) {
                case "1":
                    assistant.startSession(sc);
                    break;
                case "2":
                    assistant.showMoodHistory();
                    break;

                case "3":
                    System.out.println("i hope you had a great session..");
                default:
                System.out.println("Wrong input try again..");
                    break;
            }
        }


    }
}