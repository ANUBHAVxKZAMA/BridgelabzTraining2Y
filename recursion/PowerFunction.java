import java.util.Scanner;

public class PowerFunction {

    static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }

        int half = power(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        }

        return x * half * half;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the base: ");
        int x = sc.nextInt();

        System.out.print("Enter the exponent: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Enter a non-negative exponent.");
        } else {
            System.out.println("Result = " + power(x, n));
        }

        sc.close();
    }
}