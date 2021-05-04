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
        long start,end;

        int[] randomInsertion = randomArray;
        int[] randomBubble = randomArray;
        int[] randomSelection = randomArray;
        int[] randomShell = randomArray;

        //랜덤배열을 각 정렬방식으로 돌림
        start = System.currentTimeMillis(); // 코드 돌리는데에 걸리는 시간초 계산
        random.datainput(randomInsertion);
        insertion.sort(randomInsertion);
        end = System.currentTimeMillis();
        System.out.println("삽입정렬로 랜덤배열을 정렬하는데 걸린 시간은 : " + (end - start) + "ms");
/*
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
*/
        Sorter.print(randomInsertion);

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
/*
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
*/
        Sorter.print(somesortedInsertion);
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
/*
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
*/
        Sorter.print(descendingInsertion);

    }
}


