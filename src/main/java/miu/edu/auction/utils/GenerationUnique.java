package miu.edu.auction.utils;

import java.util.Random;

public class GenerationUnique {
    /*
    generated a unique int by String
     */
    public static String hash(String s) {
        Random rand = new Random();
        long h = rand.nextInt(10);
        int index = s.indexOf('@');
//        System.out.println(index);
        for (int i = 0; i < index; i++) {
            h = 11 * h + s.charAt(i);
        }
        return String.valueOf(h);
    }
    public static void main(String[] args){
        String email = "ndt.rachkien@gmail.com";
        System.out.println(GenerationUnique.hash(email));

        System.out.println(GenerationUnique.hash(email));
    }
}
