package Mental_Health_Simulation;

import java.util.Random;

public class QuoteProvider{
    private static String quotes[] = {
        "You are Stronger than you think.",
        "Take it one step at a time.",
        "This too shall pass.",
        "Breathe,You are doing okay.",
        "It's okay to ask for help.",
        "you matter. your feeling are valid.",
        "Progress,not perfection"};

    public String getRandomQuote(){
        Random rand = new Random();
        return quotes[rand.nextInt(quotes.length)];
    }
}