# 1. 정렬

## 1.1) 정렬에 대하여

### 1.1.1) 삽입정렬이란?

삽입 정렬은 자료 배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 부분(앞부분)과 비교하여, 

자신의 위치를 찾아 삽입함으로써 정렬을 완성하는 정렬 방법입니다.

삽입 정렬은 한 번에 한 요소의 위치만 결정되기 때문에 비효율적이다.

삽입 정렬은 입력되는 초기리스트가 "거의 정렬"되어 있을 경우 효율적이다.

### 1.1.2) 버블정렬이란?

이웃하는 숫자를 비교하여 작은 수를 앞쪽으로 이동시키는 과정을 반복하여 정렬하는 알고리즘으로 첫 번째 원소부터 인접한 원소끼리 계속 자리를 교환하면서 맨 끝부터 정렬한다.

데이터를 하나씩 비교해서 비교횟수가 많아지므로 성능면에서 좋지 않다.

### 1.1.3) 선택정렬이란?

먼저 데이터 중에서 최소값을 찾고 그 값을 맨 앞에 위치한 값과 교체하고 그것을 반복하는 정렬이다.

선택 정렬이 가장 좋은 성능을 내는 자료는 내림차순 자료이다. 

반대로 이미 어느정도 정렬된 상태에서는 속도가 느리다.

### 1.1.4) 쉘정렬이란?

정렬의 이름은 창안자 도널드 셸의 이름을 따서 붙여졌다. 

셸 정렬은 자료를 특정 길이로 쪼개서, 각 쪼개진부분에서 정렬을 수행한다.

길이를 더 줄이는 과정을 반복해서쪼개진 길이가 1이되었을 때 완전히 정렬된다.

만약 어떤 데이터가 원래 있어야될 위치에서 멀리 있다면 

여러 번의 교환이 발생하는 버블정렬의 단점을 해결할 수 있다.

## 1.2) 설계과정

1. 삽입,버블,선택,쉘 정렬을 구현한다.
2. 인터페이스로 구현해서 한개의 Sorter로 묶는다.
3. 내림차순, 어느정도정렬된 배열, 랜덤 데이터 배열을 만든다.
4. 데이터들도 인터페이스로 구현해서 한개의 data로 묶는다.
5. 각각의 데이터들을 4가지의 정렬로 정렬하고 그 시간을 측정한다.



# 2. 정렬구현코드

## 2.1) 정렬코드

### 2.1.1) Sorter 인터페이스

```java
public interface Sorter {
    public int[] sort(int[]A);
}

```



### 2.1.2) 삽입정렬

 ```java
public static class insertionSort implements Sorter {

        @Override
        public int[] sort (int [] A){

            for (int i = 1; i < A.length; i++) {
                int CurrentElement = A[i];//현재 비교될 수
                int j = i - 1;//비교할 대상을 j에 넣는다.
                while (j >= 0 && A[j]>CurrentElement) {
                    A[j+1] = A[j];//조건식을 만족하면 오른쪽으로 이동
                    j=j-1;
                }
                A[j+1] = CurrentElement;
            }
            return A;
        }

    }
 ```



### 2.1.3) 버블정렬

 ```java
public static class bubbleSort implements Sorter {

        @Override
        public int[] sort(int[] A) {
            int n = A.length;
            int tmp;
            for(int j = 0; j < n; j++) {
                for(int i = 1 ; i < n-j ; i++) {
                    // j번째와 j-1번째의 요소가 정렬이 안 돼있으면 교환
                    if(A[i-1] > A[i]) {
                        tmp = A[i-1];
                        A[i-1] = A[i];
                        A[i] = tmp;
                    }
                }
            }
            return A;
        }
    }
 ```



### 2.1.4) 선택정렬

 ```java
public static class selectionSort implements Sorter {
        @Override
        public int[] sort(int[] A) {
            int n = A.length;
            int min;
            int tmp;

            for (int i = 0; i < n - 1; i++) {
                min = i;
                for (int j = i + 1; j < n; j++) {
                    if (A[min] > A[j]) {
                        min = j;
                    }
                }
                //최솟값이 있던 자리와 배열 맨앞자리를 swap
                tmp = A[min];
                A[min] = A[i];
                A[i] = tmp;
            }
            return A;
        }
    }
 ```



### 2.1.5) 쉘정렬

 ```java
public static class shellSort implements Sorter {

        @Override
        public int[] sort(int[] A) {
            int i;
            int j;
            int CurrentElement;
            for(int h = A.length; h > 0; h = (h-1)/3) {
                //길이를 n/3+1로 줄여가면서 수행
                for( i=h; i<A.length; i++ ) {
                    CurrentElement = A[i];
                    j = i;
                    while( j>=h && A[j-h]>CurrentElement ) {
                        A[j] = A[j-h];
                        j = j-h;
                    }
                    A[j] = CurrentElement;
                }
            }
            return A;
        }

    }

}
 ```

**직접 측정해보니 gap을 A.length/2 로 계속 1/2로 줄여나가는 것 보다 **

**N-1/3이 더 빨라서 h = (A.length-1) / 3 로 작성하였다.**

이것에 대해서는 밑에서 다시 다루겠습니다.



## 2.2) 데이터 생성함수

### 2.2.1) Data 인터페이스

```java
public interface Data {
    public int[] datainput(int[]A);

}
```



### 2.2.2) 랜덤 배열

```java
public static class random implements Data{
        @Override
        public int[] datainput (int [] A){

            for (int i = 0; i < A.length; i++) {
                A[i] = (int) (Math.random()*100);//100미만 숫자 랜덤
            }
            return A;
        }
    }
```



### 2.2.3) 내림차순 배열

```java
public static class descending implements Data{

        @Override
        public int[] datainput(int[] A) {
            for (int i = 0; i < A.length; i++) {
                A[i] = 100000-i;//10만부터 거꾸로 입력
            }
            return A;
        }
    }
```



### 2.2.4) 어느정도 정렬된 배열

```java
public static class nearlysorted implements Data{

    @Override
    public int[] datainput(int[] A) {
        int tmp = 0;
        for (int i = 0; i < A.length; i++) {
            A[i] = i;
        }
        // 순서대로 죽 넣고
        for(int i = 1; i < A.length; i++){
            //어느정도 정렬된 것을 만들기 위해 만약 7번뒤에도 값이 있으면 그 값끼리 바꾸게 했다.
            if(A.length - i>7){
                tmp = A[7+i];
                A[i] = tmp;
                A[7+i] = A[i];
            }

        }

        return A;
    }
}
```



## 2.3)  main

### 2.3.1) 정렬되는 시간을 쟤는 함수

```java
public static void timecounter(int[][][] result, int n, Sorter sorter, Data data, int a, int b) {
    // 매개변수

    for(int i = 1; i<=n/5000;i++){ // 우선은 데이터를 1000씩 늘려가며 정렬을 수행해본다.
        long start, end; //시작시간, 종료시간
        start = System.currentTimeMillis();//시작시간 기록
        int[] arr = new int[i*5000]; // 입력받은 n값으로 배열을 하나 만든다.
        data.datainput(arr); // 만들어진 배열에 입력받은 데이터 타입으로 값을 입력한다.

        sorter.sort(arr); // 입력받은 정렬방식으로 정렬한다.
        end = System.currentTimeMillis(); // 종료시간 기록
        result[a][b][i-1] = (int) (end-start);

    }

}
```

### 2.3.2) main 함수(측정함수포함)

```java
import java.util.Scanner;

public class main {

    // 기본적으로 각 정렬도 실행이 되지만, 이 함수는 단순히 정렬하는 시간만 재어 결과배열에 입력하는 함수이다.
    public static void timecounter(int[][][] result, int n, Sorter sorter, Data data, int a, int b) {
        // 매개변수

        for(int i = 1; i<=n/5000;i++){ // 우선은 데이터를 1000씩 늘려가며 정렬을 수행해본다.
            long start, end; //시작시간, 종료시간
            start = System.currentTimeMillis();//시작시간 기록
            int[] arr = new int[i*5000]; // 입력받은 n값으로 배열을 하나 만든다.
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

        int [][][] result = new int[4][3][n/5000];
        // 1. 삽입 2. 버블 3. 선택 4. 쉘
        // 1. 랜덤 2. 어느정도 3. 내림



        timecounter(result,n,insertion,random,0,0);
        //삽입,랜덤을 실행하고 시간을 result[0][0][i]에 저장
        timecounter(result,n,bubble,random,1,0);
        //버블,랜덤을 실행하고 시간을 result[1][0][i]에 저장
        timecounter(result,n,selection,random,2,0);
        //선택,랜덤을 실행하고 시간을 result[2][0][i]에 저장
        timecounter(result,n,shell,random,3,0);
        //쉘,랜덤을 실행하고 시간을 result[3][0][i]에 저장

        timecounter(result,n,insertion,somesorted,0,1);
        //삽입,정렬된배열을 실행하고 시간을 result[0][1][i]에 저장
        timecounter(result,n,bubble,somesorted,1,1);
        //버블,정렬된배열을 실행하고 시간을 result[0][1][i]에 저장
        timecounter(result,n,selection,somesorted,2,1);
        //선택,정렬된배열을 실행하고 시간을 result[2][1][i]에 저장
        timecounter(result,n,shell,somesorted,3,1);
        //쉘,랜덤을 정렬된배열을 시간을 result[3][1][i]에 저장

        timecounter(result,n,insertion,descending,0,2);
        //삽입,내림차순을 실행하고 시간을 result[0][2][i]에 저장
        timecounter(result,n,bubble,descending,1,2);
        //버블,내림차순을 실행하고 시간을 result[1][2][i]에 저장
        timecounter(result,n,selection,descending,2,2);
        //선택,내림차순을 실행하고 시간을 result[2][2][i]에 저장
        timecounter(result,n,shell,descending,3,2);
        //쉘,내림차순을 실행하고 시간을 result[3][2][i]에 저장



        for(int i = 0; i<4;i++){
            for(int j = 0; j<3;j++){
                for(int k = 0; k<n/5000;k++){

                    System.out.println((i+1)+"번 정렬로 "+(j+1)+"번 종류의 데이터"+(k+1)*5000+"만큼을 정렬하는데 걸리는 시간은"+result[i][j][k]);

                }
            }
        }


    }
}


```



### 2.3.1) 출력결과

출력결과는 다음과 같다.

예시로 크기가 1000인 배열까지의 데이터를 생성하여 정렬하였다.





# 3. 정렬성능비교

## 3.1) 각 정렬별 장단점



|          | 장점                                                   | 단점                             |
| -------- | :----------------------------------------------------- | :------------------------------- |
| 삽입정렬 | 최선의 경우 O(N)으로 구할 수 있다.                     | 최선의 경우 O(N^2)이 걸린다.     |
| 버블정렬 | 구현이 간단하다.                                       | 대체적으로 느리다.               |
| 선택정렬 | 구현이 간단하다.비교하는 횟수에 비해 교환이 적게일어남 | 대체적으로 느리다.               |
| 쉘정렬   | O(NlogN)보다 빠르다.                                   | 간격설정에 따라 성능편차가 있다. |



## 3.2) 각 데이터별 정렬의 시간복잡도



|          | 최악의경우 | 평균      | 최선의경우 |
| -------- | ---------- | --------- | ---------- |
| 삽입정렬 | O(N^2)     | O(N^2)    | O(N^2)     |
| 버블정렬 | O(N^2)     | O(N^2)    | O(N^2)     |
| 선택정렬 | O(N^2)     | O(N^2)    | O(N^2)     |
| 쉘정렬   | O(N^1.25)  | O(N^1.25) | O(N^1.25)  |



## 3.3) 각 데이터 별 성능비교

![image-20210506205012810](C:\Users\Choi\AppData\Roaming\Typora\typora-user-images\image-20210506205012810.png)



같은 정렬방식이라도 코드로 짜는 방법의 차이로 그래프가 다르게 나올 수 있을 것 같다.

![image-20210504213851312](https://user-images.githubusercontent.com/75067408/117023750-57549e00-ad34-11eb-94f8-1526945ae29f.png

|       | 삽입정렬 | 삽입정렬 | 삽입정렬 | 버블정렬 | 버블정렬 | 버블정렬 | 선택정렬 | 선택정렬 | 선택정렬 | 쉘정렬 | 쉘정렬   | 쉘정렬   |
| ----- | -------- | -------- | -------- | -------- | -------- | -------- | -------- | -------- | -------- | ------ | -------- | -------- |
|       | 랜덤     | 어느정도 | 내림차순 | 랜덤     | 어느정도 | 내림차순 | 랜덤     | 어느정도 | 내림차순 | 랜덤   | 어느정도 | 내림차순 |
| 0     | 0        | 0        | 0        | 0        | 0        | 0        | 0        | 0        | 0        | 0      | 0        | 0        |
| 2500  | 7        | 0        | 1        | 9        | 1        | 6        | 6        | 1        | 2        | 1      | 0        | 0        |
| 5000  | 7        | 0        | 4        | 23       | 5        | 24       | 11       | 6        | 8        | 2      | 0        | 0        |
| 7500  | 7        | 0        | 10       | 53       | 12       | 54       | 14       | 12       | 16       | 2      | 0        | 0        |
| 10000 | 8        | 1        | 14       | 102      | 19       | 96       | 41       | 23       | 31       | 1      | 0        | 1        |
| 12500 | 11       | 1        | 22       | 167      | 27       | 149      | 66       | 35       | 47       | 1      | 1        | 0        |
| 15000 | 17       | 0        | 32       | 250      | 39       | 216      | 94       | 51       | 66       | 1      | 0        | 0        |
| 17500 | 23       | 0        | 43       | 351      | 53       | 293      | 128      | 68       | 87       | 2      | 0        | 0        |
| 20000 | 29       | 0        | 55       | 471      | 69       | 383      | 141      | 89       | 115      | 2      | 0        | 1        |
| 22500 | 35       | 1        | 70       | 614      | 88       | 484      | 173      | 113      | 146      | 2      | 0        | 0        |
| 25000 | 44       | 0        | 86       | 781      | 109      | 599      | 174      | 140      | 178      | 2      | 0        | 1        |
| 27500 | 52       | 1        | 107      | 956      | 136      | 723      | 203      | 168      | 216      | 2      | 1        | 0        |
| 30000 | 63       | 0        | 126      | 1157     | 155      | 866      | 210      | 201      | 256      | 3      | 0        | 1        |
| 32500 | 73       | 1        | 146      | 1376     | 182      | 1014     | 240      | 235      | 302      | 4      | 0        | 0        |
| 35000 | 85       | 0        | 169      | 1610     | 211      | 1174     | 276      | 277      | 356      | 3      | 0        | 1        |
| 37500 | 108      | 0        | 198      | 1861     | 243      | 1348     | 317      | 313      | 401      | 4      | 1        | 1        |
| 40000 | 112      | 0        | 228      | 2138     | 278      | 1540     | 359      | 361      | 457      | 4      | 0        | 1        |
| 42500 | 126      | 1        | 257      | 2435     | 311      | 1736     | 406      | 409      | 516      | 4      | 0        | 1        |
| 45000 | 140      | 0        | 283      | 2755     | 349      | 1941     | 456      | 451      | 577      | 5      | 1        | 1        |
| 47500 | 157      | 1        | 313      | 3080     | 388      | 2165     | 506      | 503      | 643      | 5      | 0        | 1        |
| 50000 | 173      | 0        | 346      | 3452     | 431      | 2390     | 563      | 558      | 712      | 5      | 1        | 0        |
| 52500 | 191      | 1        | 380      | 3793     | 479      | 2650     | 619      | 615      | 786      | 5      | 0        | 1        |
| 55000 | 209      | 0        | 419      | 4201     | 524      | 2899     | 680      | 675      | 867      | 6      | 0        | 1        |
| 57500 | 228      | 1        | 456      | 4603     | 572      | 3176     | 748      | 737      | 945      | 6      | 1        | 1        |
| 60000 | 250      | 0        | 496      | 5038     | 621      | 3498     | 823      | 803      | 1026     | 6      | 0        | 2        |



위 사진은 각각의 배열과 그 크기에 따라서 정렬을 수행했을 때 걸리는 시간을 적은 표 이다.



## p.s.) 쉘 정렬에서 지금까지 알려진 가장 좋은 성능을 보인 간격들

최적격차!

쉘정렬에서의 중요한 문제는 비교된 요소들 사이의 최적의 간격을 결정하는 것이다.

최고의 성능은 Marcin Ciura의  1, 4, 10, 23, 57, 132, 301, 701, 1750 의 시퀀스에 의해 제공된다.

또한 Knuth’s sequence: k = 3k + 1 도 있다.

제가 작성한 코드도 Knuth’s sequence를 이용해서 작성했습니다.





