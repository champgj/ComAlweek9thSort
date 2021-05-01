public class allSort{

    public static class bubbleSort implements Sorter {

        @Override
        public int[] sort(int[] A) {
            int n = A.length;
            for(int j = 0; j < n; j++) {
                for(int i = 1 ; i < n-j ; i++) {
                    if(A[i-1] > A[i]) {
                        int tmp = A[i-1];
                        A[i-1] = A[i];
                        A[i] = tmp;
                    }
                }
            }
            return A;
        }

    }


    public static class insertionSort implements Sorter {

        @Override
        public int[] sort (int [] A){

            for (int i = 1; i < A.length; i++) {
                int CurrentElement = A[i];  // 기준
                int j = i - 1;   // 비교할 대상
                while (j >= 0 && A[j]>CurrentElement) {
                    A[j+1] = A[j];   // 비교대상이 큰 경우 오른쪽으로 옮김
                    j=j-1;
                }
                A[j+1] = CurrentElement;  // 기준값 저장
            }
            return A;
        }

    }


    public static class selectionSort implements Sorter {
        @Override
        public int[] sort(int[] A) {
            int n = A.length;
            int min; //최소값을 가진 데이터의 인덱스 저장 변수
            int tmp;

            for (int i = 0; i < n - 1; i++) { // size-1 : 마지막 요소는 자연스럽게 정렬됨
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

    public static class shellSort implements Sorter {

        @Override
        public int[] sort(int[] A) {
            int i;
            int j;
            int CurrentElement;
            for(int h = A.length / 2; h > 0; h = h/2) {
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
