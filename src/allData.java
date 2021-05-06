public class allData {

    public static class random implements Data{
        @Override
        public int[] datainput (int [] A){

            for (int i = 0; i < A.length; i++) {
                A[i] = (int) (Math.random()*10000);//10000미만 숫자 랜덤
            }
            return A;
        }
    }
    public static class descending implements Data{

        @Override
        public int[] datainput(int[] A) {
            for (int i = 0; i < A.length; i++) {
                A[i] = 100000-10*i;//일부러 10만부터 내림차순으로 넣는다.
            }
            return A;
        }
    }

    public static class nearlysorted implements Data{

        @Override
        public int[] datainput(int[] A) {
            int tmp = 0;
            for (int i = 0; i < A.length; i++) {
                A[i] = i;//먼저 데이터를 입력하고
            }
            for(int i = 1; i < A.length; i++){
                if(A.length - i>10){// 만약 뒤에 데이터가 있다면
                    tmp = A[10+i]; //10 간격으로 데이터를 서로 교환한다
                    A[i] = tmp;
                    A[10+i] = A[i];
                }

            }

            return A;
        }
    }



}
