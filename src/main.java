import java.util.Scanner;

public class main {

    // 기본적으로 각 정렬도 실행이 되지만, 이 함수는 단순히 정렬하는 시간만 재어 결과배열에 입력하는 함수이다.
    public static void timecounter(int[][][] result, int n, Sorter sorter, Data data, int a, int b) {
        // 매개변수

        for(int i = 1; i<=n/2500;i++){ // 우선은 데이터를 1000씩 늘려가며 정렬을 수행해본다.
            long start, end; //시작시간, 종료시간
            start = System.currentTimeMillis();//시작시간 기록
            int[] arr = new int[i*2500]; // 입력받은 n값으로 배열을 하나 만든다.
            data.datainput(arr); // 만들어진 배열에 입력받은 데이터 타입으로 값을 입력한다.

            sorter.sort(arr); // 입력받은 정렬방식으로 정렬한다.
            end = System.currentTimeMillis(); // 종료시간 기록
            result[a][b][i-1] = (int) (end-start);

        }

    }



    public static void main(String[] args) throws Exception {

        Sorter insertion = new allSort.insertionSort();
        Sorter bubble = new allSort.bubbleSort();
        Sorter selection = new allSort.selectionSort();
        Sorter shell = new allSort.shellSort();

        Data random = new allData.random();
        Data somesorted = new allData.nearlysorted();
        Data descending = new allData.descending();



        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int [][][] result = new int[4][3][n/2500];
        // 1. 삽입 2. 버블 3. 선택 4. 쉘
        // 1. 랜덤 2. 어느정도 3. 내림



        timecounter(result,n,insertion,random,0,0);//삽입,랜덤을 실행하고 시간을 result[0][0][i]에 저장
        timecounter(result,n,bubble,random,1,0);//버블,랜덤을 실행하고 시간을 result[1][0][i]에 저장
        timecounter(result,n,selection,random,2,0);//선택,랜덤을 실행하고 시간을 result[2][0][i]에 저장
        timecounter(result,n,shell,random,3,0);//쉘,랜덤을 실행하고 시간을 result[3][0][i]에 저장

        timecounter(result,n,insertion,somesorted,0,1);//삽입,정렬된배열을 실행하고 시간을 result[0][1][i]에 저장
        timecounter(result,n,bubble,somesorted,1,1);//버블,정렬된배열을 실행하고 시간을 result[0][1][i]에 저장
        timecounter(result,n,selection,somesorted,2,1);//선택,정렬된배열을 실행하고 시간을 result[2][1][i]에 저장
        timecounter(result,n,shell,somesorted,3,1);//쉘,랜덤을 정렬된배열을 시간을 result[3][1][i]에 저장

        timecounter(result,n,insertion,descending,0,2);//삽입,내림차순을 실행하고 시간을 result[0][2][i]에 저장
        timecounter(result,n,bubble,descending,1,2);//버블,내림차순을 실행하고 시간을 result[1][2][i]에 저장
        timecounter(result,n,selection,descending,2,2);//선택,내림차순을 실행하고 시간을 result[2][2][i]에 저장
        timecounter(result,n,shell,descending,3,2);//쉘,내림차순을 실행하고 시간을 result[3][2][i]에 저장



        for(int i = 0; i<4;i++){
            for(int j = 0; j<3;j++){
                for(int k = 0; k<n/2500;k++){

                    System.out.println((i+1)+"번 정렬로 "+(j+1)+"번 종류의 데이터"+(k+1)*2500+"만큼을 정렬하는데 걸리는 시간은"+result[i][j][k]);

                }
            }
        }


    }
}

