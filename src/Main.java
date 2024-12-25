import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static int ATTEMS_COUNT = 6;
    private static File file = new File("C://Users//dmd29//Documents//GitHub//HungMan//Dictionary.txt");
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random(System.currentTimeMillis());



    public static void main(String[] args) throws IOException {
        /*startGameRound();
        String maskWord = makeWord();
        while (true) {
            playGame(maskWord);
        }*/

        while (true) {
            startGame();
        }
    }
    public static String chooseWord() throws FileNotFoundException {
        String maskWord = null;
        int n = 0;
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            ++n;
            String line = sc.nextLine();
            if (random.nextInt(n) == 0)
                maskWord = line;
        }
        System.out.println(maskWord);
        String output = maskWord.replaceAll("[а-яА-ЯёЁ]", "*");
        System.out.println(output);
        return maskWord;
    }

    /*public static String maskWord(String word) {
        String output = word.replaceAll("[а-яА-ЯёЁ]", "*");
        System.out.println(output);
        return output;
    }*/

    //ввод буквы
    public static void playGame(String hiddenWord)  {
        do {
            System.out.println("Введите букву: ");
            char c = scanner.next().charAt(0);
            if (hiddenWord.toLowerCase().indexOf(c) >= 0) {
                System.out.println("Есть такая буква в слове!");

                for (int i = 0; i< hiddenWord.length(); i++) {
                    if (hiddenWord.charAt(i) == c) {
                        hiddenWord = replaceLetter(Character.toString(c), hiddenWord);
                    }
                }
                System.out.println(hiddenWord);
            } else {
                System.out.println("Не правильно, попробуем еще раз");
                ATTEMS_COUNT--;
                String[]gallows = gallows();
                System.out.println("Осталось попыток: " + ATTEMS_COUNT);

            }
        } while (hiddenWord.contains("*"));

    }

    public static String replaceLetter (String letter, String maskWord) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maskWord.length(); i++) {
            if (maskWord.charAt(i) == letter.charAt(0)) {
                stringBuilder.append(letter);
            } else if (maskWord.charAt(i) != '*') {
                stringBuilder.append(maskWord.charAt(i));
            } else {
                stringBuilder.append("*");
            }
        }
        return stringBuilder.toString();
    }

    // начало раунда
    public static void startGameRound() {
        System.out.println("Начало новой игры");
        String[]gallows = gallows();
    }
    public static void startGame() throws FileNotFoundException {
        System.out.println("Если хотите начать новую игру введите 'да', если хотите выйти введите 'нет':");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("да")) {
            System.out.println("Игра начинается...");
            startGameRound();
            String chooseWord = chooseWord();
            while (true) {
                playGame(chooseWord);
            }
        } else if (input.equalsIgnoreCase("нет")) {
            System.out.println("Выход из игры...");
        } else {
            System.out.println("Неправильный ввод, попробуйте снова.");
        }
    }

    //печать виселицы
    public static String[]gallows() {
        String[] gallows = new String[]{
                """
        +------+
        |
        |
        |
        |
      =====
""",
                """ 
        +------+
        |      O
        |
        |
        |
      =====
""", """ 
        +------+
        |      O
        |      |
        |
        |
      =====
""", """ 
        +------+
        |      O
        |     /|
        |
        |
      =====
""", """ 
         +------+
         |      O
         |     /|\\
         |
         |
       =====
""", """ 
         +------+
         |      O
         |     /|\\
         |     /
         |
       =====
""", """ 
         +------+
         |      O
         |     /|\\
         |     / \\
         |
       =====
"""};

        if (ATTEMS_COUNT==6) {
            System.out.println(gallows[0]);
        } else if (ATTEMS_COUNT ==5) {
            System.out.println(gallows[1]);
        } else if (ATTEMS_COUNT == 4) {
            System.out.println(gallows[2]);
        } else if (ATTEMS_COUNT == 3) {
            System.out.println(gallows[3]);
        } else if (ATTEMS_COUNT == 2) {
            System.out.println(gallows[4]);
        } else if (ATTEMS_COUNT == 1) {
            System.out.println(gallows[5]);
        } else if (ATTEMS_COUNT == 0) {
            System.out.println(gallows[6]);
            isLose();
        }
        return gallows;

    }


    private static boolean isWin() {
        System.out.println("Вы выиграли!!!!!");
        return false;
    }
    private static boolean isLose() {
        System.out.println("Вы проиграли");
        return false;
    }
    private static boolean gameContinues() {
        System.out.println("Продолжаем игру");
        return false;
    }
}

