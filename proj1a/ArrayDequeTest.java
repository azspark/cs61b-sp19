public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

//    public static boolean checkProportional(ArrayDeque ad) {
//        boolean outcome  = ad.size() >= (ad.getItemsLength() *0.25);
//        if(!outcome) {
//            System.out.println("You have size length proportional problem!");
//        }
//        return outcome;
//    }

    public static boolean checkIntArrayEqual(ArrayDeque ad, int[] arrays) {
        boolean outcome = true;
        for (int i = 0; i < arrays.length; i++) {
            try {
                if (arrays[i] != (Integer) ad.get(i)) {
                    outcome = false;
                    break;
                }
            } catch (Exception e) {
                System.out.println("problem index: " + i);
                return false;
            }
        }
        if(!outcome) {
            System.out.println("Two array item are not equal!");
        }
        return outcome;
    }

    public static boolean checkArrayDequeEqual(ArrayDeque ad1, ArrayDeque ad2) {
        if(ad1.size() != ad2.size()){
            System.out.println("Two ArrayDeque are not equal in size");
            return false;
        }

        for(int i = 0; i < ad1.size(); i++){
            if(ad1.get(i) != ad2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void testAdd() {
        boolean passed = true;
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        int[] a1 = new int[]{1, 2, 3, 4, 5};
        for(int i = 0; i < a1.length; i++) {
            ad1.addLast(a1[i]);
        }
        passed = passed && checkSize(5, ad1.size());
        passed = passed && checkIntArrayEqual(ad1, a1);
//        passed = passed && checkProportional(ad1);

        for(int i = 0; i < 5; i++) {
            ad1.addFirst(9);
        }

        passed = passed && checkSize(10, ad1.size());
        int[] a1Added = new int[]{9, 9, 9, 9, 9, 1, 2, 3, 4, 5};
        passed = passed && checkIntArrayEqual(ad1, a1Added);
//        passed = passed && checkProportional(ad1);

        int[] a2 = new int[300];
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 300; i++) {
            ad2.addLast(i);
            a2[i] = i;
        }
        passed = passed && checkSize(300, ad2.size());
        passed = passed && checkIntArrayEqual(ad2, a2);
//        passed = passed && checkProportional(ad2);

        printTestStatus(passed);
    }

    public static void testRemove() {
        boolean passed = true;
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        int[] a1 = new int[]{1, 2, 3, 4, 5};
        for(int i = 0; i < a1.length; i++) {
            ad1.addLast(a1[i]);
        }
        ad1.removeLast();
        ad1.removeFirst();
        passed = passed && checkSize(3, ad1.size());
//        passed = passed && checkProportional(ad1);
        int[] expected1 = new int[]{2,3, 4};
        passed = passed && checkIntArrayEqual(ad1, expected1);

        int[] a2 = new int[300];
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 300; i++) {
            ad2.addLast(i);
            a2[i] = i;
        }
        for (int i = 0; i < 120; i++) {
            ad2.removeLast();
            ad1.removeFirst();
//            passed = passed && checkProportional(ad2);
        }
        printTestStatus(passed);
    }

    public static void testInitializer() {
        boolean passed = true;
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 300; i++) {
            ad2.addLast(i);
        }

//        ArrayDeque<Integer> adCopy = new ArrayDeque<>(ad2);
//
//        passed = passed && checkArrayDequeEqual(ad2, adCopy);
        printTestStatus(passed);
    }


    public static void main(String args[]) {
        testAdd();
        testRemove();
        testInitializer();
    }
}
