public class allSort{

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



}
