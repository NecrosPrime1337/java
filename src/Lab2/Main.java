package Lab2;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//import static Lab2.MatrixIncidents.createMatrix;
import static java.lang.Integer.min;

public class Main {

    private static final Integer INF = Integer.MAX_VALUE / 2; // значение бесконечности

    public static int[][] matrix; // матрица смежности

    public static ArrayList<Integer> eccentr ;// массив эксцентриситетов вершин

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = 0; // количество вершин

        System.out.println("Введите количество вершин графа");

        n = sc.nextInt();

        matrix = new int[n][n];

/** Создание матрицы смежности*/

        createAdjacMatrx(n);

        System.out.println(" Матрица смежности ");

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.print(matrix[i][j] + "\t");

            }

            System.out.println();

        }

        algFloydWarshall(n, matrix);

        computRadDiamtr();

        vertexDegree(matrix, n);

/** Создание матрицы инцендентов*/

        createMatrix(matrix, n);

    }

    private static void createMatrix(int[][] matrix, int n) {
        Random rnd = new Random();
                  for (int xi=0; xi<n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                int value = rnd.nextInt(2);
                if (yi == xi) {
                    matrix[yi][xi] = 0;
                } else if(matrix[xi][yi] == matrix[yi][xi]) {
                    matrix[xi][yi] = value;
                    matrix[yi][xi] = value;
                }
            }
        }
    }


    private static void createAdjacMatrx(int n){

        int index = 0;

        double number = 0;

        for(int i = 0; i<n; i++){

            for (int j = index; j <n ; j++){

                number = Math.random();

                int resultNum = (int) Math.round (number); // округляем результат к ближайшему целому числу

                if(i!=j){

                    matrix[i][j] = resultNum;

                    matrix[j][i] = resultNum;

                }else{

                    matrix[i][j] = resultNum; // у вершины может быть несколько петель

                }

            }

            index++;

        }

    }

    /** Алгоритм Флойда-Уоршела (Сложность O(n*n*n) кубическая) */

    public static void algFloydWarshall(int n, int matrix[][]){

        int[][] matrix_adjacency = new int[n][n];

/** Копирую матрицу смежности*/

        for (int i = 0; i < n; i++){

            System.arraycopy(matrix[i], 0, matrix_adjacency[i], 0, n);

        }

/** Алгоритм Лойда для правильной работы требует обнуления диагональных ячеек*/

        for(int i = 0; i<n; i++){

            if(matrix_adjacency[i][i] > 0){

                matrix_adjacency[i][i] = 0;

            }

        }

        for (int k = 0; k < n; ++k){

            for (int i= 0; i < n; ++i){

                for (int j = 0; j < n; ++j){

/**Заменяем нулевые значения значением бесконечности, дабы показать алгоритму непересечение вершин */

                    if (i != j && matrix_adjacency[i][j] == 0){

                        matrix_adjacency[i][j] = INF;

                    }

                    if(i!=j){

                        if(matrix_adjacency[i][j] != 0){

                            matrix_adjacency[i][j] = min( matrix_adjacency[i][j], matrix_adjacency[i][k] + matrix_adjacency[k][j] );

                        }else{

                            matrix_adjacency[i][j] = matrix_adjacency[i][k] + matrix_adjacency[k][i];

                        }

                    }

                }

            }

        }

        System.out.println("\n" + " Матрица расстояний Уолшера");

        for (int i = 0; i < matrix_adjacency.length; i++) {

            for (int j = 0; j < matrix_adjacency[i].length; j++) {
                if (matrix_adjacency[i][j] == INF){
                    System.out.print("N" + "\t");}
                else
                System.out.print(matrix_adjacency[i][j] + "\t");

            }

            System.out.println();

        }

        vertexEccentr(matrix_adjacency, n);

    }

    private static void vertexEccentr( int matrix[][], int n){

        eccentr = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            eccentr.add(-1);

            for (int j = 0; j < n; j++) {

                if(matrix[i][j] != INF && eccentr.get(i) < matrix[i][j] ){

                    eccentr.set(i, matrix[i][j]) ;

                }

            }

        }

    }

    public static void computRadDiamtr(){

        int diametr = 0;

        int radius = INF;

        for( int i = 1; i<eccentr.size(); i++){

            int el = eccentr.get(i);

            if(radius > el && el > 0){

                radius = el;

            }

            if( diametr < el ){

                diametr = el;

            }

        }

        System.out.println("\n" + "Радиус графа: " + radius );

        System.out.println("Диаметр графа: " + diametr);

        tipeOfVert(radius, diametr);

    }

    private static void tipeOfVert(int radius, int diametr){

        int el = 0;

        boolean flag = false;

        String cent = ""; //Центральные вершины

        String per = ""; //Переферийные вершины

        if (radius != diametr){

            for (int i = 0; i < eccentr.size(); i++){

                el = eccentr.get(i);

                if (el == radius){

                    cent = cent + i + " " ;

                }

                if (el == diametr){

                    per = per + i + " " ;

                }

            }

        }else{

            flag = true;

        }

        if(flag){

            System.out.println("\n" +"Все вершины графа переферийные ");

        }else{

            System.out.println("\n" +"Центральные вершины графа: " + cent );

            System.out.println("Переферийные вершины графа: " + per);

        }

    }
 /*   private static void incidence(int matrix[][]){
        //char sm[n][n];  // Матрица смежности
        int[] incid = new int[n]; // Матрица инцендентности
        for(int i=v=0; i<N; i++) for(int j=i+1; j<N; j++) if (sm[i][j]) v++;
        // Нашли v - число вершин
        for(i=0; i<N; i++) { in[i] = (char *) malloc(v); memset(in[i], 0, N); }
        for(i=k=0; i<N; i++) for(j=i+1; j<N; j++) if (sm[i][j]) {
        in[k][i] = in[k][j] = 1;
        k++;
        }
    }
*/

    /**Подсчёт степеней вершин */

    private static void vertexDegree(int matrix[][], int n){

        int vert_deg[] = new int[n]; // степени вершин без учёта петель

        int lps_vert[] = new int[n]; //степень петель вершины

/**Вычисление степени вершин графа */

        int index = 0;

        for (int i = 0; i<n; i++ ){

            for (int j = index; j <n; j++){

                if(matrix[i][j] > 0){

                    if (i != j){

                        vert_deg[i] = vert_deg[i] + matrix[i][j] ;

                        vert_deg[j] = vert_deg[j] + matrix[j][i] ;

                    }else{

                        lps_vert[i] = matrix[i][j] * 2; // степень петель для вершины

                    }

                }

            }

            index++;



        //outInfoVert(vert_deg, lps_vert, "Смежности");

    }

    String vert_isol = "";

    String vert_end = "";

    String vert_dom = "";

    int count = vert_deg.length;

for (int i = 0; i < count; i++) {

        switch (vert_deg[i]){

            case 0:{

                vert_isol = vert_isol + i + "; " ;

                break;

            }

            case 1:{

                vert_end = vert_end + i + "; ";

            }

            default:{

                if( (vert_deg[i] == count -1) ){

                    vert_dom = vert_dom + i + "; ";

                }

                break;

            }

        }

        System.out.println("Вершина " + i + " со степенью "

                + (vert_deg[i] + lps_vert[i]));

    }

//System.out.println("\n" + "Типы Вершин матрицы " + type_matrix );

if (!vert_isol.isEmpty()){

        System.out.println("Изолированные вершины: " + vert_isol); }

if (!vert_end.isEmpty()){

        System.out.println("Концевые вершины: " + vert_end); }

if (!vert_dom.isEmpty()){

        System.out.println("Доминирующие вершины: " + vert_dom); }

}

}