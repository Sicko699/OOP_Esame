import controllers.GestoreGara;
import controllers.FileDataReader;

public class Main {
    public static void main(String[] args) {
        FileDataReader.init("traccia_A.txt");
        System.out.println("----------------------- Task 1 -----------------------");
        System.out.println(GestoreGara.Task1());
        System.out.println("----------------------- Task 2 -----------------------");
        System.out.println(GestoreGara.Task2());
        System.out.println("----------------------- Task 3 -----------------------");
        System.out.println(GestoreGara.Task3());
    }
}