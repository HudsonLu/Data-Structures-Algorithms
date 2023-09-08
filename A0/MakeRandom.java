/** 
 *  @author Hudson Xingcheng Lu
 *  @version 18 May 2023
 * 
 * Written by: Hudson Xingcheng Lu 40254326
 * COMP352
 * Assignment # 0
 * Due Date Thursday 18 May
*/


import java.util.Random;

public class MakeRandom {

    public static void main(String[] args) {

        Random rand = new Random();

        if (args.length == 2) {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);

            rand.setSeed(x);

            for (int i = 0; i < y; i++) {
                System.out.print(rand.nextInt(Integer.MAX_VALUE) + " ");
            }
        } else if (args.length == 1) {
            int y = Integer.parseInt(args[0]);
            rand.setSeed(y);

            for (int i = 0; i < y; i++) {
                System.out.print(rand.nextInt(Integer.MAX_VALUE) + " ");
            }
        }
    }
}

