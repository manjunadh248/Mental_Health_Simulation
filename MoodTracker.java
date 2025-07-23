package Mental_Health_Simulation;
import java.util.Scanner;

public class MoodTracker{
    public String askMood(Scanner sc,user user){
        System.out.println("Hi"+user.getName()+", how are you felling today ?");
        System.out.println("Options: Happy,Sad,Anxious,Angry,Tired,Excited");
        System.out.println("Your Mood: ");
        String mood = sc.nextLine();
        return mood;

    }

}