/**
 * Created by smeleyka on 21.04.17.
 */
public class TestClass {
    public static final int SIZE = 5;


    public static void main(String[] args) {
        int f;
        for (int i = SIZE-1; i >= 0; i--) {
            f = i;
            for (int j = 0; f>= 0; j++,f--) {
                System.out.println(f + " " + j);
            }
        }
    }
}
