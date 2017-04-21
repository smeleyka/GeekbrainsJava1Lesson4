import javafx.stage.Screen;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by smeleyka on 05.04.17.
 */
public class MainClass {

    public static final int SIZE = 5;
    public static final int DOT_TO_WINT = 3;
    public static final char EMPTY = '*';
    public static final char ODOT = '0';
    public static final char XDOT = 'X';
    public static char[][] map = new char[SIZE][SIZE];
    public static int turnCount;
    public static Random rand = new Random();
    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        initMap();
        printMap();
        do {
            humanTurn();
            //aiTurn2();
            printMap();
            if (winCheck(XDOT)) break;
            //if (diagonalCheck(XDOT)) break;
            aiTurn();
            printMap();
            if (winCheck(ODOT)) break;
            //if (diagonalCheck(ODOT)) break;
        } while (!isMapFull());
    }

    public static void initMap() {
        turnCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.printf("%2d ", i);
        }
        for (int i = 0; i < SIZE; i++) {
            System.out.println();
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%2c ", map[j][i]);
            }
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x, y;
        System.out.print("Ваш ход. ");
        System.out.println("ход № " + turnCount);
        do {
            System.out.println("Введите координаты \"X Y\"");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            sc.nextLine();
        } while (!isValidDot(x, y));
        map[x][y] = XDOT;
        turnCount += 1;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isValidDot(x, y));
        System.out.println("Компьютер сходил " + (x + 1) + " " + (y + 1));
        map[x][y] = ODOT;
        turnCount += 1;
    }

    public static void aiTurn2() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isValidDot(x, y));
        System.out.println("Компьютер сходил " + (x + 1) + " " + (y + 1));
        map[x][y] = XDOT;
        turnCount += 1;
    }

    public static boolean isValidDot(int x, int y) {
        if (x >= SIZE || y >= SIZE || y < 0 || x < 0) return false;
        return map[x][y] == EMPTY;
    }

    public static boolean isMapFull() {
        return turnCount >= (SIZE * SIZE);
    }

    /**
     * public static boolean winCheck() {
     * if (isMapFull()) {
     * System.out.println("Ничья.");л
     * turnCount = SIZE * SIZE;
     * return true;
     * }
     * return false;
     * }
     */
    public static boolean winCheck(char player_dot) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < SIZE; i++, x = 0, y = 0) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == player_dot) {
                    x++;
                    if (x == DOT_TO_WINT) {
                        System.out.println("Победил " + player_dot);
                        return true;
                    }
                } else x = 0;
                if (map[j][i] == player_dot) {
                    y++;
                    if (y == DOT_TO_WINT) {
                        System.out.println("Победил " + player_dot);
                        return true;
                    }
                } else y = 0;
            }
        }
        if (diagonalCheck(player_dot)) return true;
        if (isMapFull()) {
            System.out.println("Ничья.");
            turnCount = SIZE * SIZE;
            return true;
        }
        return false;
    }

    public static boolean diagonalCheck(char dot) {
        int x = 0;
        int f;
        for (int i = 0; i < SIZE; i++) {
            f = i;
            x = 0;
            for (int j = 0; f < SIZE; j++, f++) {
                if (map[j][f] == dot) {
                    x++;
                    if (x == DOT_TO_WINT) {
                        System.out.println("Победа! Диагональ " + dot);
                        return true;
                    }
                } else x = 0;
            }
            f = i;
            x = 0;
            for (int j = 0; f < SIZE; j++, f++) {
                if (map[f][j] == dot) {
                    x++;
                    if (x == DOT_TO_WINT) {
                        System.out.println("Победа! Диагональ " + dot);
                        return true;
                    }
                } else x = 0;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            f = i;
            for (int j = 0; f >= 0; j++, f--) {
                if (map[j][f] == dot) {
                    x++;
                    if (x == DOT_TO_WINT) {
                        System.out.println("Победа! Диагональ " + dot);
                        return true;
                    }
                } else x = 0;
            }
            for (int j = 0; f >= 0; j++, f--) {
                if (map[f][j] == dot) {
                    x++;
                    if (x == DOT_TO_WINT) {
                        System.out.println("Победа! Диагональ " + dot);
                        return true;
                    }
                } else x = 0;
            }
        }
        return false;
    }
}
