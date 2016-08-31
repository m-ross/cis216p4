package lab04;

import javax.swing.JOptionPane;
import lab04.MailingList;

public class Menu {

	public static char getChoice() {
		char choice;
		String temp;
		while (true) {
			temp = JOptionPane.showInputDialog("(A)dd person to mailing list\n(F)ind person on mailing list\n(D)isplay mailing list\n(C)hange mailing list file name\n(Q)uit\n\nEnter choice");
			try { //leave loop if input is valid
				if (!temp.isEmpty()) {
					choice = temp.toLowerCase().charAt(0);
					if (choice=='a' || choice=='f' || choice=='d' || choice=='c' || choice=='q')
						return choice;
				}
			} catch (NullPointerException e) { //exit if dialog closed
				JOptionPane.showMessageDialog(null,"Task aborted");
				return 'q';
			}
			JOptionPane.showMessageDialog(null,"Invalid entry");
		}
	}

	public static String doChoice(char choice, String listFileN) {
		switch(choice) { //Do user choice
		case 'a': //Add person to list
			MailingList.addPerson(listFileN);
			break;
		case 'f': //Find person in list
			MailingList.findPerson(listFileN);
			break;
		case 'd': //Display list
			MailingList.showList(listFileN);
			break;
		case 'c': //Get mailing list file name
			listFileN = MailingList.getFileN(); //I made doChoice return a string in order to stop using listFileN as a field of MailingList, in turn to avoid the need to instantiate either Menu or MailingList. Is this messy?
		}
		return listFileN;
	}
}