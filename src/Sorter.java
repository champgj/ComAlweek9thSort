public interface Sorter {
    public int[] sort(int[]A);

    public static void print(int[] A){
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }


}
