package vn.topica.itlab4.exercises.hieu_trainer;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Exercises1_17 {
    private final static Exercises1_17 instance = new Exercises1_17();

    private Exercises1_17() {

    }

    public static Exercises1_17 getInstance() {
        return instance;
    }

    /**
     * Input: image .jpg
     * players with red T-shirt
     * Output: replace red color by blue color
     */
    public void replaceColor() {
        BufferedImage img = null;
        File f = null;
        try {
            f = new File("D:/topica/src/main/resources/bt1_b1.jpg");
            img = ImageIO.read(f);
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int p = img.getRGB(x, y);
                    Color c = new Color(p);
                    int red = c.getRed();
                    int green = c.getGreen();
                    int blue = c.getBlue();
                    int alpha = c.getAlpha();
                    Color newColor = new Color(blue, green, red, alpha);
                    if ((red > 1.5 * green) && (red > 1.5 * blue)) {
                        img.setRGB(x, y, newColor.getRGB());
                    }
                }
            }
            File output = new File("D:/topica/src/main/resources/converted_img.jpg");
            ImageIO.write(img, "jpg", output);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Exercise 1:
     * Bài tập 1: Viết chương trình tìm trung bình các số bằng mảng ([]). Thời gian 15 phút
     *
     * @return average numbers in array
     */
    public int arverageNumber() {
        int[] nums = new int[10];
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        return total / nums.length;
    }

    /**
     * Input a number from keyboard
     * Check if a number is odd | even then print it out
     * Bài tập 2: Viết chương trình kiểm tra 1 số là số lẻ hay chẵn.? time 10 phút
     */
    public void oddEven() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number: ");
        int num = sc.nextInt();
        if (num % 2 == 0) {
            System.out.printf("%d is even number", num);
        } else {
            System.out.printf("%d is even number", num);
        }
    }

    /**
     * Input: n get from keyboard
     * Output: all primes which less than and equal to n
     * Then print it out
     * Bài tập 3: Viết chương trình hiển thị n số nguyên tố đầu tiên. Thời gian 30 phút.
     */
    public void prime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("n = ");
        int n = sc.nextInt();
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == 2 || i == 3 || i == 5 || i == 7) {
                primes.add(i);
            } else if (i % 2 != 0 && i % 3 != 0 && i % 5 != 0 && i % 7 != 0) {
                primes.add(i);
            }
        }
        System.out.println(StringUtils.join(primes, "|"));
    }

    /**
     * Input: number input from keyboard
     * Output: print out whether number is < 0 or > 0
     * Bài tập 4: Viết chương trình  kiểm tra xem số đầu vào (được nhập bởi người dùng) là dương hay âm
     */
    public void checkPositiveNegative() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number = ");
        int num = sc.nextInt();
        if (num > 0) {
            System.out.printf("%d is positive number", num);
        } else if (num < 0) {
            System.out.printf("%d is negative number", num);
        }
    }

    /**
     * Exercise 5 | Exercise 16: reverse a string
     * Viết chương trình đảo ngược chuỗi
     *
     * "Viết chương trình đảo ngược các từ trong 1 chuỗi.
     * Ví dụ: Welcome to BeginnersBook --> emocleW ot kooBsrennigeB
     * This is an easy Java Program --> sihT si na ysae avaJ margorP"
     */
    public void reverseString() {
        String str = "This is something.";
        char[] chars = str.toCharArray();
        String outStr = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            outStr += chars[i];
        }
        System.out.printf(outStr);
    }

    /**
     * Exercise 6: reverse a number
     * Viết chương trình đảo ngược số tự nhiên
     */
    public void reverseNumber() {
        int num = 84565843;
        String str = String.valueOf(num);
        char[] chars = str.toCharArray();
        String outStr = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            outStr += chars[i];
        }
        System.out.println(Integer.valueOf(outStr));
    }

    /**
     * Exercise 7: Check leap year
     * "Viết chương trình để kiểm tra năm nhuận
     * Để xác định xem một năm có phải là năm nhuận hay không, hãy làm theo các bước sau:
     * 1. Nếu năm chia hết cho 4, chuyển sang bước 2. Nếu không, hãy chuyển sang bước 5.
     * 2. Nếu năm chia hết cho 100, chuyển sang bước 3. Nếu không, hãy chuyển sang bước 4.
     * 3. Nếu năm chia hết cho 400, chuyển sang bước 4. Nếu không, hãy chuyển sang bước 5.
     * 4. Năm là một năm nhuận (nó có 365 ngày).
     * 5. Năm không phải là năm nhuận (nó có 365 ngày)."
     */
    public void checkLeapYear() {
        int year = 1996;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            System.out.printf("%d is a leap year", year);
        } else {
            System.out.printf("%d is not a leap year", year);
        }
    }

    /**
     * Exercise 8: Decimal number to heximal number
     * Viết chương trình chuyển từ số hệ thập phân sang hệ thập lục phân. Vd: 10>A, 16>10
     */
    public void toHexaNumber() {
        int num = 16;
        int[] binary = new int[30];
        int i = 0;
        String strNum = "";
        while (num > 0) {
            binary[i] = num % 16;
            strNum += binary[i]+strNum;
            num /= 16;
            i++;
        }
        // reverse
        char[] chars = strNum.toCharArray();
        String outStr = "";
        for (int j = chars.length - 1; j >= 0; j--) {
            outStr += chars[j];
        }
        System.out.println(Integer.valueOf(outStr));
    }

    /**
     * Exercise 9: Decimal number to binary number
     * Viết chương trình chuyển từ số hệ thập phân sang hệ nhị phân. Vd: 45>101101
     */
    public void toBinaryNumber() {
        int num = 45;
        int[] binary = new int[50];
        int i = 0;
        while (num > 0) {
            binary[i] = num % 2;
            num /= 2;
            System.out.print(binary[i]);
            i++;
        }
    }

    /**
     * Exercise 10: get computer IP
     * Viết chương trình lấy IP của máy mình
     */
    public void getIP(){
        try {
            System.out.println(Inet4Address.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exercise 11: get same character in a string
     * Viết chương trình tìm ký tự trùng nhau trong một xâu, không phân biệt HOA-thường
     */
    public void getSameCharacter() {
        String str = "This is Same String haha.";
        char[] chars = str.toLowerCase().toCharArray();
        List<Character> sames = new ArrayList<Character>();
        for (int j = 0; j < chars.length; j++) {
            char c = chars[j];
            for (int i = j+1; i < chars.length-1; i++) {
                if (c == chars[i] && !sames.contains(chars[i])) {
                    sames.add(c);
                }
            }
        }
        System.out.println(StringUtils.join(sames, "|"));
    }

    /**
     * Exercise 12: create a random number which is greater than 0
     * Viết chương trình sinh số tự nhiên ngẫu nhiên
     */
    public void createNumber() {
        Random r = new Random();
        System.out.println(r.nextInt(100));
    }

    /**
     * Exercise 13: check symmetry string
     * Viết chương trình kiểm tra chuỗi có là đối xứng không? vd: "cofdfoc"
     */
    public void checkSymmetry() {
        String str = "abbcbba";
        char[] chars = str.toCharArray();
        boolean isSummetry = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                isSummetry = false;
            }
        }
        System.out.printf("This string is summetry: %s", isSummetry);
    }

    /**
     * Exercise 14: bubble sort using for loop
     * Viết chương trình xắp xếp số theo thuật toán nổi bọt sử dụng câu lệnh for
     */
    public void bubbleSortFor() {
        int[] arr = new int[]{4,5,9,1,2,4,11,22,7,21};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        System.out.println(StringUtils.join(ArrayUtils.toObject(arr), ","));
    }

    /**
     * Exercise 15: bubble sort using while loop
     * Viết chương trình xắp xếp số theo thuật toán nổi bọt sử dụng câu lệnh while
     */
    public void bubbleSortWhile() {
        int[] arr = new int[]{4,5,9,1,2,4,11,22,7,21};
        int i = 0;
        while (i < arr.length) {
            int j = i + 1;
            while (j < arr.length - 1) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                j++;
            }
            i++;
        }
        System.out.println(StringUtils.join(ArrayUtils.toObject(arr), ","));
    }

    /**
     * Exercise 17: convert two number that don't use temp variable
     * "Viết chương trình hoán đổi giá trị 2 số tự nhiên mà không dùng biến tạm.
     * Ví dụ A=10, B=6. sau khi goán đổi giá trị: A=6, B=10."
     */
    public void convertNumber() {
        int a = 10;
        int b = 5;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.printf("a = %d, b = %d", a, b);
    }

}
