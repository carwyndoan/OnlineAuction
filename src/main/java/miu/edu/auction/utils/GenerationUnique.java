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

    public static String generateInvoiceNumber() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 && i >= 48))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static void main(String[] args) {
        String email = "ndt.rachkien@gmail.com";
        System.out.println(GenerationUnique.hash(email));

        System.out.println(GenerationUnique.hash(email));

        System.out.println(GenerationUnique.generateInvoiceNumber());
    }
}
