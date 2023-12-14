import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        while (true){
            playGame(sc, rand);
            System.out.println("Do you want to play again? 'yes' or 'no':");
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("no")){
                break;
            }
        }

    }

    private static void playGame(Scanner sc, Random rand) {
        int ties = 0;
        int userWins = 0;
        int computerWins = 0;

        System.out.println("How many rounds do you want to play?");
        int numberOfRounds = sc.nextInt();
        sc.nextLine();

        while (numberOfRounds > 0) {
            System.out.println("What's your choice? 1 = Rock, 2 = Paper, 3 = Scissors");

            int userChoice = getUserChoice(sc);
            int computerChoice = rand.nextInt(3) + 1;
            System.out.println("User picked: " + userChoice);
            System.out.println("Computer picked: " + computerChoice);

            int[] roundScores = playRound(userChoice, computerChoice, new int[]{userWins, computerWins, ties});
            userWins = roundScores[0];
            computerWins = roundScores[1];
            ties = roundScores[2];

            numberOfRounds--;
        }

        System.out.println();
        System.out.println("User wins: " + userWins);
        System.out.println("Computer wins: " + computerWins);
        System.out.println("Ties: " + ties);
    }

    private static int[] playRound(int userChoice, int computerChoice, int[] scores) {
        if (userChoice == computerChoice) {
            scores[2]++; // ties
            System.out.println("It's a tie!");
        } else if ((userChoice == ROCK && computerChoice == SCISSORS) ||
                (userChoice == PAPER && computerChoice == ROCK) ||
                (userChoice == SCISSORS && computerChoice == PAPER)) {
            scores[0]++; // user wins
            System.out.println("User wins");
        } else {
            scores[1]++; // computer wins
            System.out.println("Computer wins");
        }
        return scores;
    }

    private static int getUserChoice(Scanner sc){
        while (true){
            try{
                int userChoice = Integer.parseInt(sc.nextLine());
                if (userChoice >= 1 && userChoice <=3){
                    return userChoice;
                }else{
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
}
