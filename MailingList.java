package lab04;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class MailingList {

	public static String getFileN() {
		String listFileN;
		while (true) {
			listFileN = JOptionPane.showInputDialog("Enter mailing list file name");
 			try {
				if (!listFileN.isEmpty())
					return listFileN;
			} catch (NullPointerException e) { //exit if dialog closed
				JOptionPane.showMessageDialog(null,"Task aborted");
				System.exit(0); //can find no gentler way of ending program when listFileN is null without adding code to Menu
			}
			JOptionPane.showMessageDialog(null,"Invalid entry");
		}
	}

	public static void addPerson(String listFileN) {
		String personName, personAddr, personPhone;
		FileWriter outFileW;
		PrintWriter outFile;
		File inFileR;
		Scanner inFile;
		//Get person info from user
		while (true) {
			personName = JOptionPane.showInputDialog("Enter name of person to add");
			try { //leave loop if input is valid
				if (!personName.isEmpty())
					break;
			} catch (NullPointerException e) { //quit adding person if dialog closed
				JOptionPane.showMessageDialog(null,"Task aborted");
				return;
			}
			JOptionPane.showMessageDialog(null,"Invalid entry");
		}
		while (true) {
			personAddr = JOptionPane.showInputDialog("Enter address");
			try { //leave loop if input is valid
				if (!personAddr.isEmpty())
					break;
			} catch (NullPointerException e) { //quit adding person if dialog closed
				JOptionPane.showMessageDialog(null,"Task aborted");
				return;
			}
			JOptionPane.showMessageDialog(null,"Invalid entry");
		}
		while (true) {
			personPhone = JOptionPane.showInputDialog("Enter phone number");
			try { //leave loop if input is valid
				if (!personPhone.isEmpty())
					break;
			} catch (NullPointerException e) { //quit adding person if dialog closed
				JOptionPane.showMessageDialog(null,"Task aborted");
				return;
			}
			JOptionPane.showMessageDialog(null,"Invalid entry");
		}
		try { //open file for writing
			outFileW = new FileWriter(listFileN + ".txt",true);
			outFile = new PrintWriter(outFileW);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,String.format("File %s.txt not found",listFileN));
			return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,String.format("Error opening %s.txt",listFileN));
			return;
		}

		//Display person info to file
		outFile.printf("%s\n%s\n%s\n",personName,personAddr,personPhone);
		outFile.close();
	}

	public static void findPerson(String listFileN) {
		String personName, personAddr, personPhone, searchName;
		File inFileR;
		Scanner inFile;
		try {
			inFileR = new File(listFileN + ".txt");
			inFile = new Scanner(inFileR);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,String.format("File %s.txt not found",listFileN));
			return;
		}
		while (true) { //Get person name from user
			try {
				searchName = JOptionPane.showInputDialog("Enter name of person to find");
				if (!searchName.isEmpty())
					break;
			} catch (NullPointerException e) { //quit finding person if dialog closed
				JOptionPane.showMessageDialog(null,"Task aborted");
				return;
			}
			JOptionPane.showMessageDialog(null,"Invalid entry");
		}
		while (inFile.hasNext()) { //Process person in file
			try { //get info from next three lines in file
				personName = inFile.nextLine();
				personAddr = inFile.nextLine();
				personPhone = inFile.nextLine();
			} catch (RuntimeException e) {
				System.out.println("Incomplete entry encountered\n");
				inFile.close();
				return;
			}
			if (!personName.equalsIgnoreCase(searchName)) //restart loop if name in file doesn't equal input
				continue;
			JOptionPane.showMessageDialog(null,String.format("%-9s%-50.50s\n%-9s%-50.50s\n%-9s%-50.50s\n","Name:",personName,"Address:",personAddr,"Phone:",personPhone));
		}
		inFile.close();
	}

	public static void showList(String listFileN) {
		File inFileR;
		Scanner inFile;
		try { //open file for reading
			inFileR = new File(listFileN + ".txt");
			inFile = new Scanner(inFileR);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,String.format("File %s.txt not found",listFileN));
			return;
		}

		//Display header to user
		System.out.printf("\nFull Mailing List in %s.txt\n%-20s%-20s%-20s\n",listFileN,"Name","Address","Phone");
		while (inFile.hasNext()) //Process each person
			try { //display three lines from file
				System.out.printf("%-21.20s%-21.20s%-21.20s\n",inFile.nextLine(),inFile.nextLine(),inFile.nextLine()); //this is bad practice, isn't it?
			} catch (RuntimeException e) {
				System.out.println("Incomplete entry encountered");
				return;
			}
		inFile.close();
	}

}