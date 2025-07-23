package Mental_Health_Simulation;
import java.util.Scanner;

public class Assistant {
    private user user;
    private CalmActivity ca;
    private QuoteProvider qp;
    private MoodTracker mt;

    Assistant(user user){
        this.user = user;
        ca = new CalmActivity();
        qp = new QuoteProvider();
        mt = new MoodTracker();

    }

    public void startSession(Scanner sc){
        String mood = mt.askMood(sc,user);
        System.out.println("Logging the mood history");
        user.addMoodHistory(mood);
        qp.getRandomQuote();

        System.out.println("Would you like to do some exercise?(yes\no): ");
        String ans = sc.nextLine().trim().toLowerCase();
        
        if(ans.equals("yes")){
            ca.breathingExercise();
        }
        System.out.println("Thank you for initiating session!");
    }
    public void showMoodHistory(){
        for(String mood :  user.getMoodHitory()){
            System.out.print(mood+",");

        }
        System.out.println();
    }



}