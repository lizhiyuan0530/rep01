package until;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/8/24
 * Time:  16:01
 * @description
 */
public class MyScannerUtil {
    private static Scanner scanner = new Scanner(System.in);
    // 获得扫描int值
    public static int getScannerInt(){
        return scanner.nextInt();
    }
    // 获得扫描int值
    public static double getScannerDouble(){
        return scanner.nextDouble();
    }
    // 获得扫描int值
    public static String getScannerString(){
        return scanner.next();
    }
}