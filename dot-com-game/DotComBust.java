import java.util.ArrayList;

public class DotComBust {
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private GameHelper helper = new GameHelper();
    private int numOfGuesses = 0;

    private void setUpGame() {
        DotCom one = new DotCom();
        one.setName("holyshit.com");
        DotCom two = new DotCom();
        two.setName("wtf.com");
        DotCom three = new DotCom();
        three.setName("doshoot.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);
        System.out.println("Welcome to Dot Com Bust Game!");
        System.out.println("Your Goal is to sink three dot coms.");
        System.out.println("Try to sink them all in fewest numbers of guesses.");

        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocation(newLocation);
        }
    }

    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            String userInput = helper.getUserInput("Enter a guess");
            checkUserGuess(userInput);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        ++numOfGuesses;
        String result = "miss";
        for (DotCom dotComToTest : dotComsList) {
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("hit")) {
                result = result + " " + dotComToTest.dotComName() + "!";
                break;
            }
            if (result.equals("kill")) {
                dotComsList.remove(dotComToTest);
                result = result + " " + dotComToTest.dotComName() + "!";
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("All Dot Coms are dead! Your stocks are now worthless.");
        if (numOfGuesses < 18) {
            System.out.println("It only takes you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough, " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}