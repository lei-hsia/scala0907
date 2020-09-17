import java.util.Scanner;
import java.util.Arrays;

public class CibFintech {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        String[] strings = string.split(",");
        int length = strings.length;
        int[] nums = new int[length];
        for (int i = 0; i < length; ++i) {
            nums[i] = Integer.parseInt(strings[i]);
        } // input

        Arrays.sort(nums);
        String res = "";
        for (int i = 0; i < length - 1; ++i) {
            if (nums[i] == nums[i+1]) {
                res += (nums[i]+" ");
            }
        }
        System.out.println(res);
    }
}