package Mental_Health_Simulation;

public class CalmActivity{
    public void breathingExercise(){
        System.out.println("We will start with the breathing exercise");
        try{
        for(int i = 0;i < 3;i++){
            System.out.println("Breathe in.......");
            Thread.sleep(3000);
            System.out.println("Hold..");
            Thread.sleep(2000);
            System.out.println("Breathe out...");
            Thread.sleep(3000);
        }
    }catch(InterruptedException e){
        e.printStackTrace();
        System.out.println("Exercise interrupted.");
    }
    System.out.println("I hope you are feeling better..");
    
    }
}