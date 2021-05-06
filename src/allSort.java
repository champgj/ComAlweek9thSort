public class allSort {

    public static class bubbleSort implements Sorter {

        @Override
        public int[] sort(int[] A) {
            int n = A.length;
            int tmp;
            for (int j = 0; j < n; j++) {
                for (int i = 1; i < n - j; i++) {
                    // j번째와 j-1번째의 요소가 정렬이 안 돼있으면 교환
                    if (A[i - 1] > A[i]) {
                        tmp = A[i - 1];
                        A[i - 1] = A[i];
                        A[i] = tmp;
                    }
                }
            }
            return A;
        }


    }


    public static class insertionSort implements Sorter {

        @Override
        public int[] sort(int[] A) {

            for (int i = 1; i < A.length; i++) {
                int CurrentElement = A[i];//현재 비교될 수
                int j = i - 1; //비교할 대상을 j에
                while (j >= 0 && A[j] > CurrentElement) {
                    A[j + 1] = A[j]; //오른쪽으로 이동
                    j = j - 1;
                }
                A[j + 1] = CurrentElement;
            }
            return A;
        }


    }


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
                //최솟값이 있던 자리와 배열 맨앞자리를 교환
                tmp = A[min];
                A[min] = A[i];
                A[i] = tmp;
            }
            return A;
        }


    }

    public static class shellSort implements Sorter {

        @Override
        public int[] sort(int[] A) {
            int i;
            int j;
            int CurrentElement;
            for (int h = A.length; h > 0; h = (h - 1) / 3) {
                //길이를 n/3+1로 줄여가면서 수행
                for (i = h; i < A.length; i++) {
                    CurrentElement = A[i];
                    j = i;
                    while (j >= h && A[j - h] > CurrentElement) {
                        A[j] = A[j - h];
                        j = j - h;
                    }
                    A[j] = CurrentElement;
                }
            }
            return A;
        }


    }
}