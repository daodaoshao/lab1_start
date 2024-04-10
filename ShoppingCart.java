import backEnd.*;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShoppingCart {


    private static final Lock lock = new ReentrantLock();

    private static void print(Wallet wallet, Pocket pocket) throws Exception {
        System.out.println("Your current balance is: " + wallet.getBalance() + " credits.");
        System.out.println(Store.asString());
        System.out.println("Your current pocket is:\n" + pocket.getPocket());
    }

    private static String scan(Scanner scanner) throws Exception {
        System.out.print("What do you want to buy? (type quit to stop) ");
        return scanner.nextLine();
    }

    public static  void main(String[] args) throws Exception {
        Wallet wallet = new Wallet();
        Pocket pocket = new Pocket();
        Scanner scanner = new Scanner(System.in);

        print(wallet, pocket);
        String product = scan(scanner);

        while (!product.equals("quit")) {
            /* TODO:
               - check if the amount of credits is enough, if not stop the execution.
               - otherwise, withdraw the price of the product from the wallet.
               - add the name of the product to the pocket file.
               - print the new balance.
            */
            if (wallet.safeWithdraw(Store.getProductPrice(product))) {
                pocket.addProduct(product);
            } else{
                    System.out.println("The balance is not enough \n");
            }



            // Just to print everything again...
            print(wallet, pocket);
            product = scan(scanner);

        }
    }
}