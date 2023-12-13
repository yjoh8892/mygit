// Yoo Jin OH
// SEP 15 2022

import java.util.*;

public class HelloWorld {
	public static void main(String[] args) {
		int n;
		int i = 1;
		String mystr;
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		
		while (i <= n) {
            	mystr = sc.nextLine();
            	i++;
			System.out.println("Hello, " + mystr + "!");
			}
		}
	}