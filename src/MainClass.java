import javafx.stage.Screen;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by smeleyka on 05.04.17.
 */
public class MainClass {

    public static final int SIZE = 3;
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
            printMap();
            if (winCheck()) break;
            aiTurn();
            printMap();
            if (winCheck()) break;
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
        System.out.println("[ход № "+turnCount);
        do {
            System.out.println("Введите координаты \"X Y\"");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            sc.nextLine();
        } while (!isValidDot(x, y));
        map[x][y]=XDOT;
        turnCount += 1;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isValidDot(x, y));
        System.out.println("Компьютер сходил "+(x+1)+" "+(y+1));
        map[x][y]=ODOT;
        turnCount +=1;
    }

    public static boolean isValidDot(int x, int y) {
        if (x >= SIZE || y >= SIZE || y < 0 || x < 0) return false;
        return map[x][y] == EMPTY;
    }

    public static boolean isMapFull() {
        return turnCount >= (SIZE * SIZE);
    }

    public static boolean winCheck() {
        if (isMapFull()) {
            System.out.println("Ничья.");
            turnCount = SIZE * SIZE;
            return true;
        }
        return false;
    }
}