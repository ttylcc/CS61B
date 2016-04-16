public class TestSmooth{
    public static void smooth(int[] ints){
        int cp = 1;
        int compare = ints[0];
        for (int i = 1; i < ints.length; i++){
            if (ints[i] != compare){
                compare = ints[i];

                int tmp = ints[cp];
                ints[cp] = ints[i];
                ints[i] = tmp;

                cp++;
            }
            else{
                ints[i] = -1;
            }
            String ss = stringInts(ints);
            System.out.println(ss);
        }

    }

    private static String stringInts(int[] ints) {
        String s = "[  ";
        for (int i = 0; i < ints.length; i++) {
            s = s + Integer.toString(ints[i]) + "  ";
        }
        return s + "]";
    }

    public static void main(String[] args) {
        int[] test1 = {3, 7, 7, 7, 4, 5, 5, 2, 0, 8, 8, 8, 8, 5};
        //System.out.println("smooshing " + stringInts(test1) + ":");
        smooth(test1);
        //String result = stringInts(test1);
        //System.out.println(result);
    }
}
