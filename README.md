# 1. Strassen Algorithm

## 1.1) 분할정복이란?

**분할 정복 알고리즘**은 크기가 크거나, 해결하는데 많은 비용이 드는 문제를 

각각의 작은 문제로 분할하고 그 결과를 종합하여 문제를 해결하는 알고리즘 종류이다.

문제를 나누어 처리함으로써 처리 양이 많거나 어려운 문제를 해결해낼 수 있다는 장점이 있다.

퀵소트, 합병정렬 등등 여러 알고리즘에도 사용된다.

## 1.2) Strassen 알고리즘이란?

독일의 수학자 폴커 슈트라센이 개발한 행렬 곱셈 알고리즘이다. 

n x n행렬 과 n x n행렬 을 곱할 때

슈트라센은 분할정복을 이용하기 때문에 일반적으로 정의대로 구하는 것보다 

 더 빠르게 구할 수 있다.

이 알고리즘은 n이 2의 거듭제곱일 때 사용 가능하다.

그러므로 행렬의 길이가 2의 거듭제곱이 아닐 때 그것보다 한 조금 더 큰 2의거듭제곱 크기의 정방행렬을 만든 후 값을 대입한 후 계산해준다.

## 1.3) Strassen 알고리즘 설계과정

1. 분할정복을 하기 전, 일반적인 방법이 더 빠를 수 있으므로 일반적인 방법도 추가한다.

2. 행렬을 쪼개서 구하고 다시 합치는 과정에서 분할 정복을 이용한다. 

3. A행렬과 B행렬을 4분할 하여 각 서브 배열들을 만들어준다.

4. 각각의 M1,M2,M3,.....,M7을 구한 뒤  각 식에 맞게 구한다.

5. C의 서브배열에 M값을 각각 대입하여 구한다.

6. C의 서브배열들을 합병하여 C를 만들고 출력한다.

   

![image-20210424123234963](C:\Users\Choi\AppData\Roaming\Typora\typora-user-images\image-20210424123234963.png)

**M1 = (A11 + A22) x (B11 + B22)**

**M2 = (A21 + A22) x B11**

**M3 = A11 x (B12 - B22)**

**M4 = A22 x (B21 - B11)**

**M5 = (A11 + A12) x B22**

**M6 = (A21 - A11) x (B11 + B12)**

**M7 = (A12 - A22) x (B21 + B22) **



**C11 = M1 + M4 - M5 + M7**

**C12 = M3 + M5**

**C21 = M2 + M4**

**C22 = M1 + M3 - M2 + M6**



### 문제 고려 요소

 - 분할정복을 하는 것 보다 일반적인 방법으로 해를 구하는 것이 더 간편한 경우
 - 각각의 분할 함수, 합병 함수, 덧셈함수, 뺄셈함수 구현
 - 행렬의 길이 n이 2의 거듭제곱수가 아닐 때



# 2. 알고리즘 구현코드

## 2.1) 알고리즘 코드(Strassen Algorithm)

#### 2.1.1) 세부 코드(Strassen Algorithm)



**1. 먼저 입력된 A의 길이를 n에 저장 후  결과가 저장될 배열 C를 생성하고 **

**만약 일반적인 방법으로 해를 구하는 방법이 더 간편할 경우에는 직접 구하는 방법을 택한다.** 

 ```java
		int n = A.length;
        int[][] C = new int [n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        }else if(n==2){
            C[0][0] = A[0][0] * B[0][0] + A[0][1]*B[1][0];
            C[0][1] = A[0][0] * B[0][1] + A[0][1]*B[1][1];
            C[1][0] = A[1][0] * B[0][0] + A[1][1]*B[1][0];
            C[1][1] = A[1][0] * B[0][1] + A[1][1]*B[1][1];
        }
 ```





**2. 나머지경우, 즉 이 문제에서 집중적으로 다룰 내용들이다. **

**A배열과 B배열을 4등분한 배열이 저장될 배열들을 각각 만들어준다.**

 ```java
else {
            int[][] a11 = new int[n / 2][n / 2];
            int[][] a12 = new int[n / 2][n / 2];
            int[][] a21 = new int[n / 2][n / 2];
            int[][] a22 = new int[n / 2][n / 2];

            int[][] b11 = new int[n / 2][n / 2];
            int[][] b12 = new int[n / 2][n / 2];
            int[][] b21 = new int[n / 2][n / 2];
            int[][] b22 = new int[n / 2][n / 2];
 ```

첫번째 행렬을 a11,a12,a21,a22로 4등분한다.

똑같이 두번째 행렬도 a11,a12,a21,a22로 4등분한다.



**3. A와 B배열을 4등분 하여 2번단계에서 만든 배열에 넣어준다.** 

 ```java
            divideMatrix(A,a11,0,0);
            divideMatrix(A,a12,0,n/2);
            divideMatrix(A,a21,n/2,0);
            divideMatrix(A,a22,n/2,n/2);

            divideMatrix(B,b11,0,0);
            divideMatrix(B,b12,0,n/2);
            divideMatrix(B,b21,n/2,0);
            divideMatrix(B,b22,n/2,n/2);
 ```

A를 4등분으로 쪼개서 각각 a11,a12,a21,a22에 넣는다.

B를 4등분으로 쪼개서 각각 b11,b12,b21,b22에 넣는다.



**4. 이 부분은 수학자 슈트라센이 연구하여 만든 공식이다. 이 공식들을 함수들로 변환하여 작성한다.** 

 ```java
            int[][] M1 = Strassen(addMatrix(a11, a22), addMatrix(b11, b22)); 
            int[][] M2 = Strassen(addMatrix(a21,a22),b11);           
            int[][] M3 = Strassen(a11, subMatrix(b12,b22));            
            int[][] M4 = Strassen(a22,subMatrix(b21,b11));         
            int[][] M5 = Strassen(addMatrix(a11,a12),b22);
            int[][] M6 = Strassen(subMatrix(a21, a11), addMatrix(b11,b12));
            int[][] M7 = Strassen(subMatrix(a12, a22), addMatrix(b21,b22)); 

            int[][] C11 = subMatrix(addMatrix(addMatrix(M1,M4),M7),M5);    
            int[][] C12 = addMatrix(M3,M5);                                
            int[][] C21 = addMatrix(M2,M4);                                
            int[][] C22 = subMatrix(addMatrix(addMatrix(M1,M3),M6),M2);
 ```



**이제 M시리즈를 정해준다.**

M1 = (a11 + a22) x (b11 + b22)

M2 = (a21 + a22) x b11

M3 = a11 x (b12 - b22)

M4 = a22 x (b21 - b11)

M5 = (a11 + a12) x b22

M6 = (a21 - a11) x (b11 + b12)

M7 = (a12 - a22) x (b21 + b22)



**M시리즈를 이용해서 AB행렬곱해진 결과물인 C행렬을 만드는 과정**

C11 = M1 + M4 - M5 + M7

C12 = M3 + M5

C21 = M2 + M4

C22 = M1 + M3 - M2 + M6



**5. 분할돼있는 각각의 서브배열들을 한개의 C배열로 합치는 과정, 그리고 최종결과물 C를 반환** 

 ```java
            mergeSub(C,C11,0,0);
            mergeSub(C,C12,0,n/2);
            mergeSub(C,C21,n/2,0);
            mergeSub(C,C22,n/2,n/2);
}
        return C;
 ```



**6. 행렬을 나누는 함수** 

 ```java
    public static void divideMatrix(int[][] original,int [][]sub, int startX,int startY){
        int n = sub.length;
        int x=0;
        int y = 0;
        for(int i = startY; i<startY+n; i++){
            x=0;
            for(int j = startX; j<startX+n; j++) {
                sub[x][y] = original[j][i];
                x++;
            }
            y++;
        }
    }
 ```

행렬을 나누는 함수이다. 매개변수로는 원래 함수, 나뉘어질 서브함수, X시작위치,Y시작위치 를 넣어준다.

정수형 n에 서브함수의 길이를 넣어주고 sub함수에 

원래함수 x시작위치~x시작위치+서브함수길이

원래함수 y시작위치~y시작위치+서브함수길이 범위에 있는 값들을 옮겨 넣어준다.



**7. 행렬끼리의 덧셈을 실행하는 함수** 

 ```java
    public static int[][] addMatrix(int[][] a,int [][]b){
        int n = a.length;
        int [][] added = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                added[i][j] = a[i][j] + b[i][j];
            }
        }
        return added;
    }
 ```

각각의 행렬에서 같은 위치에 있는 것들 끼리 더한다.



**8. 행렬끼리의 뺄셈을 실행하는 함수**

 ```java
 public static int[][] subMatrix(int[][] a,int [][]b){
        int n = a.length;
        int [][] subtracted = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                subtracted[i][j] = a[i][j] - b[i][j];
            }
        }
        return subtracted;
    }
 ```

각각의 행렬에서 같은 위치에 있는 것들 끼리 뺀다.



**9. 각서브행렬들을 한개의 행렬로 합치는 함수**

 ```java
	public static void mergeSub(int[][] C, int[][] Csub, int startX, int startY){
        int n = Csub.length;
        int x=0;
        int y = 0;
        for(int i = startY; i<startY+n; i++){
            x=0;
            for(int j = startX; j<startX+n; j++) {
                C[j][i] = Csub[x][y];    // <<<<=====
                x++;
            }
            y++;
        }
    }
 ```

위의행렬을 나누는 함수와 거의 비슷하다. 반대로 다시 합치는 것이라 화살표부분만 다르다.

 매개변수로는 원래 함수, 나뉘어진 서브함수, X시작위치,Y시작위치 를 넣어준다.

정수형 n에 서브함수의 길이를 넣어주고 sub함수를 

원래함수 x시작위치~x시작위치+서브함수길이

원래함수 y시작위치~y시작위치+서브함수길이 범위에  옮겨 넣어준다.



**10. 행렬이 2의 거듭제곱수가 아닐 때 나머지 부분을 0으로 채우는 함수**

```java
    // 만약 행렬의 길이가 2의 거듭제곱이 아닐 때 처리해주는 함수
    // 나머지 부분을 전부 0으로 채운다.
    public static int[][] fillzero (int [][]A) {

        int n = A.length;
        int i =0;
        int count = 0;
        while(true) {   //n과 가장 가까운 2의 거듭제곱수를 찾는 과정이다.
            if((Math.pow(2,i+1)>n)&&(Math.pow(2,i)<=n)){
                break;
            }
            else {
                i++;
            }
        }

        int [][] A0 = new int[(int)Math.pow(2,i+1)][(int) Math.pow(2,i+1)]; 
        // 찾은 거듭제곱수 만큼의 행렬을 만든다. 0으로 초기화

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                A0[j][k]=A[j][k];   // 값을 옮긴다.
            }
        }


        return A0;
    }
```

행렬의 길이 n과 가장 가까운 2의 거듭제곱수를 찾는다.

찾은 거듭제곱수만큼의 행렬을 만들고 0으로 초기화 후 값을 옮긴다.



**11. main 함수**

 ```java
public static void main(String[] args) {

        System.out.print("N x N 행렬 길이를 입력하시오(N=2^k)>");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산


        int[][] A = new int[n][n];
        for(int j = 0; j< A.length;j++){
            for(int i = 0; i < A.length; i++) {
                A [i][j] = (int) (Math.random()*10);

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();



        int[][] B = new int[n][n];
        for(int j = 0; j< B.length;j++){
            for(int i = 0; i < B.length; i++) {
                B [i][j] = (int) (Math.random()*10);

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();




        int[][] C = Strassen(fillzero(A),fillzero(B));

        System.out.println("A와 B의 행렬곱 결과 ");

         //이제 A와 B의 행렬 곱 C를 출력하는 함수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();




        long end = System.currentTimeMillis();

        System.out.println("이 코드를 실행하는데 걸린 시간은 : " + (end - start) + "ms");


    }

 ```

먼저 스캐너로 n을 입력해주면 입력한 n만큼 n by n 행렬을 A와 B 두가지를 임의로 만들고, 그것을 스트라센알고리즘을 호출하여 행렬 곱을 계산하는 코드이다.

코드의 성능, 구동하는데 걸리는 시간을 알아보기 위해 currentTimeIlls()를 사용하였다.





#### 2.1.2) 전체 코드(Strassen Algorithm)

```java
// 홀수일 때는 어떻게 처리되는지 고민해보다가 결과가 너무 복잡할 것 같다..
// 스트라센 알고리즘은 무조건 n이 2의 거듭제곱일 때만 가능하다.
// 홀수는 생각할 필요 없을 것 같다.
// 홀수일 때는 조금더 큰 2의제곱행렬 만들고 나머지부분 0으로 채운다. <- 이것도 만들어야됨..
// 완료!

import java.util.Scanner;

public class StrassenAlgorithm {

    public static int [][] Strassen(int[][] A, int[][] B) {

        int n = A.length; //n X n 행렬에서 n을 저장한다.

        int[][] C = new int [n][n]; // 마지막에 다 곱해진 것들이 들어갈 곳이다.

        if (n == 1) { //1x1 짜리 행렬의 곱 이면 그냥 곱해버린다.
            C[0][0] = A[0][0] * B[0][0];
        }else if(n==2){ //2x2 짜리 행렬의 곱일 때도 그냥 곱해버린다.
            C[0][0] = A[0][0] * B[0][0] + A[0][1]*B[1][0];
            C[0][1] = A[0][0] * B[0][1] + A[0][1]*B[1][1];
            C[1][0] = A[1][0] * B[0][0] + A[1][1]*B[1][0];
            C[1][1] = A[1][0] * B[0][1] + A[1][1]*B[1][1];

        }else { //나머지 경우 4,8,16,32 등등..

            // 첫번째 행렬을 a11,a12,a21,a22로 4등분한다.
            int[][] a11 = new int[n / 2][n / 2];
            int[][] a12 = new int[n / 2][n / 2];
            int[][] a21 = new int[n / 2][n / 2];
            int[][] a22 = new int[n / 2][n / 2];
            // 똑같이 두번째 행렬도 a11,a12,a21,a22로 4등분한다.
            int[][] b11 = new int[n / 2][n / 2];
            int[][] b12 = new int[n / 2][n / 2];
            int[][] b21 = new int[n / 2][n / 2];
            int[][] b22 = new int[n / 2][n / 2];

            //A를 4등분으로 쪼개서 각각 a11,a12,a21,a22에 넣는다.
            divideMatrix(A,a11,0,0);
            divideMatrix(A,a12,0,n/2);
            divideMatrix(A,a21,n/2,0);
            divideMatrix(A,a22,n/2,n/2);
            //B를 4등분으로 쪼개서 각각 b11,b12,b21,b22에 넣는다.
            divideMatrix(B,b11,0,0);
            divideMatrix(B,b12,0,n/2);
            divideMatrix(B,b21,n/2,0);
            divideMatrix(B,b22,n/2,n/2);


            // 이제 M시리즈를 정해준다.
            // 사진에 보면 예를 들어  m1 = (a11 + a22)x(b11+b22) 일때
            // 중간에 곱셈역할은 Strassen 함수가 해준다.
            //이거 잘못넣어놔서 한참헤맸다.. 이유는 readme에 잘못된 사진을 올려놔서..그리고 밑에 C11,C12,C21,C22 작성하는것도 잘못작성.....
            //수정완료!
            int[][] M1 = Strassen(addMatrix(a11, a22), addMatrix(b11, b22));  //M1 = (a11 + a22) x (b11 + b22)
            int[][] M2 = Strassen(addMatrix(a21,a22),b11);                    //M2 = (a21 + a22) x b11
            int[][] M3 = Strassen(a11, subMatrix(b12,b22));                   //M3 = a11 x (b12 - b22)
            int[][] M4 = Strassen(a22,subMatrix(b21,b11));                    //M4 = a22 x (b21 - b11)
            int[][] M5 = Strassen(addMatrix(a11,a12),b22);                    //M5 = (a11 + a12) x b22
            int[][] M6 = Strassen(subMatrix(a21, a11), addMatrix(b11,b12));   //M6 = (a21 - a11) x (b11 + b12)
            int[][] M7 = Strassen(subMatrix(a12, a22), addMatrix(b21,b22));   //M7 = (a12 - a22) x (b21 + b22)


            // M시리즈를 이용해서 AB행렬곱해진 결과물인 C행렬을 만드는 과정
            int[][] C11 = subMatrix(addMatrix(addMatrix(M1,M4),M7),M5);    //C11 = M1 + M4 - M5 + M7
            int[][] C12 = addMatrix(M3,M5);                                //C12 = M3 + M5
            int[][] C21 = addMatrix(M2,M4);                                //C21 = M2 + M4
            int[][] C22 = subMatrix(addMatrix(addMatrix(M1,M3),M6),M2);    //C22 = M1 + M3 - M2 + M6



            // 거의 완성단계
            // C11,C12,C21,C22 를 이어붙여서 C에 저장
            // 머지 하는 것도 함수로 만든다.
            mergeSub(C,C11,0,0);
            mergeSub(C,C12,0,n/2);
            mergeSub(C,C21,n/2,0);
            mergeSub(C,C22,n/2,n/2);

        }

        return C;
    }



    //행렬을 나누는 함수 만들기 매개변수는 원래함수랑 쪼개져 저장될 함수,그리고 그 원래함수의 어디부터 어디까지 옮겨넣을건지
    // 수정 - 만약 위처럼 하게 되면 매개변수가 원래행렬,서브행렬,x시작,x끝, y시작,y끝 총 6개로 너무 많음
    // 그래서 시작점?을 넣고 그 이후로 sub행렬의 길이만큼만 돌리는 것으로 작성

    public static void divideMatrix(int[][] original,int [][]sub, int startX,int startY){
        int n = sub.length;
        int x=0;
        int y = 0;
        for(int i = startY; i<startY+n; i++){
            x=0;
            for(int j = startX; j<startX+n; j++) {
                sub[x][y] = original[j][i];
                x++;
            }
            y++;
        }

    }


    //행렬끼리 더해주는 함수
    public static int[][] addMatrix(int[][] a,int [][]b){
        int n = a.length; //편의를 위해 쪼개진 a배열의 길이를 n에 넣는다. 위의 n과는 다른 n
        int [][] added = new int[n][n]; // 각각 더한 값들이 들어갈 배열을 만든다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                added[i][j] = a[i][j] + b[i][j];
            }
        }
        return added;
    }
    // 행렬끼리 빼주는 함수
    public static int[][] subMatrix(int[][] a,int [][]b){ //addMatrix함수와 거의 똑같다.
        int n = a.length;
        int [][] subtracted = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                subtracted[i][j] = a[i][j] - b[i][j];
            }
        }
        return subtracted;
    }

    // 현재는 쪼개져있는 C11,C12,C21,C22 행렬들을 하나의 C행렬로 합치는 함수
    // 디바이드하는 함수랑 거의 비슷하다.
    public static void mergeSub(int[][] C, int[][] Csub, int startX, int startY){
        int n = Csub.length;
        int x=0;
        int y = 0;
        for(int i = startY; i<startY+n; i++){
            x=0;
            for(int j = startX; j<startX+n; j++) {
                C[j][i] = Csub[x][y];
                x++;
            }
            y++;

        }
    }

    // 만약 행렬의 길이가 2의 거듭제곱이 아닐 때 처리해주는 함수
    // 나머지 부분을 전부 0으로 채운다.
    public static int[][] fillzero (int [][]A) {

        int n = A.length;
        int i =0;
        int count = 0;
        while(true) {   //n과 가장 가까운 2의 거듭제곱수를 찾는 과정이다.
            if((Math.pow(2,i+1)>n)&&(Math.pow(2,i)<=n)){
                break;
            }
            else {
                i++;
            }
        }

        int [][] A0 = new int[(int)Math.pow(2,i+1)][(int) Math.pow(2,i+1)]; // 찾은 거듭제곱수 만큼의 행렬을 만든다. 0으로 초기화

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                A0[j][k]=A[j][k];   // 값을 옮긴다.
            }
        }


        return A0;
    }



    public static void main(String[] args) {

        System.out.print("N x N 행렬 길이를 입력하시오(N=2^k)>");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산


        int[][] A = new int[n][n];
        for(int j = 0; j< A.length;j++){
            for(int i = 0; i < A.length; i++) {
                A [i][j] = (int) (Math.random()*10);

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();



        int[][] B = new int[n][n];
        for(int j = 0; j< B.length;j++){
            for(int i = 0; i < B.length; i++) {
                B [i][j] = (int) (Math.random()*10);

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();




        int[][] C = Strassen(fillzero(A),fillzero(B));

        System.out.println("A와 B의 행렬곱 결과 ");

         //이제 A와 B의 행렬 곱 C를 출력하는 함수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();




        long end = System.currentTimeMillis();

        System.out.println("이 코드를 실행하는데 걸린 시간은 : " + (end - start) + "ms");


    }

}
```



#### 2.1.3) 출력결과

![image-20210428161036246](C:\Users\Choi\AppData\Roaming\Typora\typora-user-images\image-20210428161036246.png)





## 2.2) 알고리즘 코드(3중 for문)

### 2.2.1) 3중 for문

```java
public static int[][] multiply(int[][]A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++)
                    C[i][j] += A[i][k] * B[k][j];
            }
        return C;
    }
```



### 2.2.1) 전체코드(3중 for문)

```java
import java.util.Scanner;

public class naive {


    public static int[][] multiply(int[][]A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++)
                    C[i][j] += A[i][k] * B[k][j];
            }
        return C;
    }




    public static void main(String[] args) {

        System.out.print("N x N 행렬 길이를 입력하시오(N=2^k)>");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //N by N 행렬 길이 입력받고

        long start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산

        //만약 64 by 64 짜리의 행렬곱을 한다고 하면

        // 코드 확인을 위해 우선 4by4짜리를 먼저 넣어본다.
        int[][] A = new int[n][n];
        for(int j = 0; j< A.length;j++){
            for(int i = 0; i < A.length; i++) {
                A [i][j] = (int) (Math.random()*10);

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();



        int[][] B = new int[n][n];
        for(int j = 0; j< B.length;j++){
            for(int i = 0; i < B.length; i++) {
                B [i][j] = (int) (Math.random()*10);

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        int[][] C = multiply(A,B);

        System.out.println("A와 B의 행렬곱 결과 ");

        //이제 A와 B의 행렬 곱 C를 출력하는 함수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();



        long end = System.currentTimeMillis();

        System.out.println("이 코드를 실행하는데 걸린 시간은 : " + (end - start) + "ms");
    }

}

```

### 2.1.3) 출력결과

출력결과는 다음과 같다.

만약 Strassen 알고리즘과 같은 입력값을 넣으면 출력결과도 같은 결과가 나온다 .

![image-20210428161144186](C:\Users\Choi\AppData\Roaming\Typora\typora-user-images\image-20210428161144186.png)



# 3. 시간복잡도 알고리즘 성능비교

## 3.1) 스트라센알고리즘의 시간복잡도 분석

 - 그냥 일반적인 방식으로 구하면 for문이 3중으로 돼있어서 대략 O(n^3)이다

 - 하지만 스트라센알고리즘을 이용하면 은 M1,M2,,M7 각각의 1개씩 곱셈  총 7번

   그리고 나머지 덧셈과 뺄셈이 O(n<sup>2)

   전체 복잡도 T(N) = 7T(N/2) + O(N<sup>2) 이다. n>1이고, n = 2<sup>k (k>=1)이다.

   이 식을 정리하면 슈트라센 알고리즘의 최종적인 시간복잡도는 O(N<sup>2.8074)이 된다.

   하나의 n x n 곱셈이 일곱 개의 (n/2) x (n/2) 곱셈으로 바뀌었기 때문이다.

   2.8074 라는 수가 나오게 된 이유에 대한 식을 풀어 쓰면
   
   T(n) = 7x7x7x7x7x7x7......(k 번 곱)

​		=7<sup>k

​		=n<sup>log7

​		=n<sup>2.8074

이 된다. 그래서 O(N<sup>2.8074)이 된다.



## 3.2) 스트라센알고리즘과 3중for문 의 시간복잡도비교



![image-20210428125416457](C:\Users\Choi\AppData\Roaming\Typora\typora-user-images\image-20210428125416457.png)

값이 작을 때는 크게 차이가 나지 않지만 값이 커지면 커질 수록 차이가 심해진다.



## 3.3)슈트라센알고리즘 개발 이후의 역사

슈트라센 알고리즘 이후의 행렬 곱 알고리즘의 역사는 

- 위노그라드 알고리즘O(N<sup>2.795) 이 개발, 
- 코퍼스미스와 슈무엘 위노그라드 알고리즘O(N<sup>2.376),
- Stother 알고리즘O(N<sup>2.3737),
- 월리엄스알고리즘O(N<sup>2.3727) 까지 단축되었다.









