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

    public static void print(int[] A){
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
```



### 2.1.2) 삽입정렬

 ```java
public static class insertionSort implements Sorter {

        @Override
        public int[] sort (int [] A){

            for (int i = 1; i < A.length; i++) {
                int CurrentElement = A[i];
                int j = i - 1;
                while (j >= 0 && A[j]>CurrentElement) {
                    A[j+1] = A[j];
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
            for(int h = A.length / 3+1; h > 0; h = h/2) {
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
 ```

**직접 테스트 해본 결과 gap을 A.length/2 로 계속 1/2로 줄여나가는 것 보다 **

**N/3+1이 더 빨라서 h = A.length / 3+1 로 작성하였다.**

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
                A[i] = (int) (Math.random()*100);
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
                A[i] = 100000-i;
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
        for(int i = 1; i < A.length; i++){
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



### 2.3) main 함수

```java
import java.util.Scanner;

public class main {


    public static void main(String[] args) {

        Sorter insertion = new allSort.insertionSort();
        Sorter bubble = new allSort.bubbleSort();
        Sorter selection = new allSort.selectionSort();
        Sorter shell = new allSort.shellSort();


        Data random = new allData.random();
        Data nearlysorted = new allData.nearlysorted();
        Data descending = new allData.descending();

        System.out.println("만들고싶은 랜덤배열,어느정도 정렬된 배열, 내림차순 배열의 크기를 입력하시오");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();



        int [] randomArray = new int[n];
        random.datainput(randomArray);


        int[] randomInsertion = randomArray;
        int[] randomBubble = randomArray;
        int[] randomSelection = randomArray;
        int[] randomShell = randomArray;

        //랜덤배열을 각 정렬방식으로 돌림
        long start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(randomInsertion);
        insertion.sort(randomInsertion);
        long end = System.currentTimeMillis();
        System.out.println("삽입정렬로 랜덤배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(randomBubble);
        bubble.sort(randomBubble);
        end = System.currentTimeMillis();
        System.out.println("버블정렬로 랜덤배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(randomSelection);
        selection.sort(randomSelection);
        end = System.currentTimeMillis();
        System.out.println("선택정렬로 랜덤배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(randomShell);
        shell.sort(randomShell);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬로 랜덤배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");


        System.out.println("");


        int [] nearlysortedArray = new int[n];
        nearlysorted.datainput(nearlysortedArray);

        int[] somesortedInsertion = nearlysortedArray;
        int[] somesortedBubble = nearlysortedArray;
        int[] somesortedSelection = nearlysortedArray;
        int[] somesortedShell = nearlysortedArray;


        //어느정도 정렬된 배열을 각 정렬방식으로 돌림
        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(somesortedInsertion);
        insertion.sort(somesortedInsertion);
        end = System.currentTimeMillis();
        System.out.println("삽입정렬로 어느정도 정렬된 배열 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(somesortedBubble);
        bubble.sort(somesortedBubble);
        end = System.currentTimeMillis();
        System.out.println("버블정렬로 어느정도 정렬된 배열 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(somesortedSelection);
        selection.sort(somesortedSelection);
        end = System.currentTimeMillis();
        System.out.println("선택정렬로 어느정도 정렬된 배열 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(somesortedShell);
        shell.sort(somesortedShell);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬로 어느정도 정렬된 배열 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        System.out.println("");

        int [] descendingArray = new int[n];
        descending.datainput(descendingArray);

        int[] descendingInsertion = descendingArray;
        int[] descendingBubble = descendingArray;
        int[] descendingSelection = descendingArray;
        int[] descendingShell = descendingArray;
        

        //내림차순배열을 각 정렬방식으로 돌림
        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(descendingInsertion);
        insertion.sort(descendingInsertion);
        end = System.currentTimeMillis();
        System.out.println("삽입정렬로 내림차순배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(descendingBubble);
        bubble.sort(descendingBubble);
        end = System.currentTimeMillis();
        System.out.println("버블정렬로 내림차순배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(descendingSelection);
        selection.sort(descendingSelection);
        end = System.currentTimeMillis();
        System.out.println("선택정렬로 내림차순배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(descendingShell);
        shell.sort(descendingShell);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬로 내림차순배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");

        
    }
}
```



### 2.3.1) 출력결과

출력결과는 다음과 같다.

예시로 크기가 1000인 배열을 생성하여 정렬하였다.

![image-20210504212201256](https://user-images.githubusercontent.com/75067408/117023527-270cff80-ad34-11eb-95cd-298f8f786bb6.png)



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

![image-20210504213544834](https://user-images.githubusercontent.com/75067408/117023604-38560c00-ad34-11eb-8454-69a7dd3efdaa.png)



![image-20210504213612201](https://user-images.githubusercontent.com/75067408/117023652-4310a100-ad34-11eb-8ca5-356519603830.png)

![image-20210504213645993](https://user-images.githubusercontent.com/75067408/117023702-4e63cc80-ad34-11eb-97d5-624c87dd8909.png)







같은 정렬방식이라도 코드로 짜는 방법의 차이로 그래프가 다르게 나올 수 있을 것 같다.

![image-20210504213851312](https://user-images.githubusercontent.com/75067408/117023750-57549e00-ad34-11eb-94f8-1526945ae29f.png)

위 사진은 각각의 배열과 그 크기에 따라서 정렬을 수행했을 때 걸리는 시간을 적은 표 이다.



## p.s.) 쉘 정렬에서 지금까지 알려진 가장 좋은 성능을 보인 간격들

최적격차!

쉘정렬에서의 중요한 문제는 비교된 요소들 사이의 최적의 간격을 결정하는 것이다.

최고의 성능은 Marcin Ciura의  1, 4, 10, 23, 57, 132, 301, 701, 1750 의 시퀀스에 의해 제공된다.

또한 Knuth’s sequence: k = 3k + 1 도 있다.

제가 작성한 코드도 Knuth’s sequence를 이용해서 작성했습니다.





