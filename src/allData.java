public class allData {

    public static class random implements Data{
        @Override
        public int[] datainput (int [] A){

            for (int i = 0; i < A.length; i++) {
                A[i] = (int) (Math.random()*10000);
            }
            return A;
        }
    }
    public static class descending implements Data{

        @Override
        public int[] datainput(int[] A) {
            for (int i = 0; i < A.length; i++) {
                A[i] = 100000-i;
            }
            return A;
        }
    }

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



}
