/*	Program Name:   Lab 04 Mailing List
	Programmer:     Marcus Ross
	Date Due:       04 Oct 2013
	Description:	This program, after receiving a file name from the user, will present a menu that lets the user choose whether to add to the list, find a person in the list, display the list, and quit. If the user enters inappropriate or no input, they will be asked again. Otherwise, if they close a prompt, the program will escape from where it was, possibly exiting the program. I tried to make the class usage slightly more logical based on your advice, and I removed objects.
*/

package lab04;

import lab04.MailingList;
import lab04.Menu;

public class Main {
	public static void main (String args[]) {

		char choice;
		String listFileN;

		listFileN = MailingList.getFileN();

		do {
			choice = Menu.getChoice();
			listFileN = Menu.doChoice(choice,listFileN);
			System.gc();
		} while (choice!='q');
			
		System.exit(0);
	}
}