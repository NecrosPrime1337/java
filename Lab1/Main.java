package Lab1;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {
    private static int menu = 0;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        task.out("Лабораторная работа №1");
        task.out("");
        task.out("Главное меню.");
        task.out("1) Выполнение задачи");
        task.out("2) Об авторе");
        task.out("0) Выход");
        task.out("Ваш выбор:");
        try {
            menu = in.nextInt();
        }
        catch (InputMismatchException menu)
        {
            task.out("ошибка ввода ");
            task.stay();
            task.clear();
            main(args);
        }
        switch (menu){
            case 1: {
                task.func();
                break;
            }
            case 2: {
                task.author();
                break;
            }
            case 3: {
                System.exit(0); // LOL
            }
            }
    }

}
