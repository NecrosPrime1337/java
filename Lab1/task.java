package Lab1;

import java.io.IOException;
import java.util.Scanner;

/********************************************************************
 *                                                                  *
 *                дешево - сердито.                                 *
 *           велосипед - не коляска,                                *
 *      Костыль - не приговор.                                      *
 *                                  (c) Senior Java-Developer       *
 *                                                                  *
 ********************************************************************/

public class task {

    public static double f(double x) {
        return 8 * Math.cos(x*x);
    } //функция по варианту

    public static void out(String s) {   //чтобы не писать постоянно System.out.println
        System.out.println(s);
    }

    public static void author() {
        clear();
        out("Работу выполнил:");
        out("Студент группы 19ИЭ1БЗИ ");
        out("Иванов Евгений Андреевич");
    }

    public static void stay() throws IOException {
        Scanner in = new Scanner(System.in);
         System.in.read();              //может я и не догоняю, но почему он его игнорит если работает, как надо?? Не баг, а фича!
    }

    public static void clear() {
        for( int i = 0; i<=100; i++) out(""); //костыль, но это же джава. она целиком на костылях. Даже адекватной функции clear нет. молчу про readkey...
    }

    public static void func()
    {
        double upper, lower, step;
        Scanner s = new Scanner(System.in);
        out("ожидание ввода нижнего порога");
        lower = s.nextDouble();
        out("ожидание ввода верхнего порога");
        upper = s.nextDouble();
        out("ожидание ввода шагов");
        step = s.nextDouble();
        double x, sum=0, a, b;
        long t1,t2,t3;
        t1 = System.currentTimeMillis();
        int iteration=0;
        for(x = lower; x <= upper; x+=step)
        {
            a = f(x);
            b = f(x + step);
            sum += ((a+b)/2) *step;
            iteration++;
        }
        t2 = System.currentTimeMillis();
        t3 = t2-t1;
        out("Интеграл функции = "+sum);
        out("выполнено  "+iteration+" итераций за "+t3+" мС");
    }
}

/*


       ]__
     _/ * >
    / \  \              строго не судите, вот вам канарейка. Она помогала мне писать код
   / 7 7 '
  / 7 7_/
 / 7'  \__
/'     '





 */