package miu.edu.auction.utils;

public class GenerationUnique {
    /*
    generated a unique int by String
     */
    public static String hash(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        return String.valueOf(h);
    }
    public static void main(String[] args){
        String email = "ndt.rachkien@gmail.com";
        System.out.println(GenerationUnique.hash(email));
    }
}
