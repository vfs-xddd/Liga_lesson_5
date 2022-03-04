package homework;

import java.util.Scanner;

public class NumbersCheck {

    public static boolean isInt(String str) {

        return new Scanner(str).hasNextInt();

    }
}
