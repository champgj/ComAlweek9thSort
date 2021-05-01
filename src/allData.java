public class allData {

    public static class random implements Data{
        @Override
        public int[] datainput (int [] A){

            for (int i = 0; i < A.length; i++) {
                A[i] = (int) (Math.random()*100);
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

    public static class somesorted implements Data{

        @Override
        public int[] datainput(int[] A) {
            for (int i = 0; i < A.length; i++) {
                if(i < A.length/10) A[i] = i;
                else A[i] = (int) (Math.random()*100);
            }
            return A;
        }
    }



}
