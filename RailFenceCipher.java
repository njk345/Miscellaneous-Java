import java.util.*;

public class RailFenceCipher {
	public static void main(String[] args) {
		System.out.println("Welcome to my Rail Fence Cipher!");
		System.out.println("Please enter the message you would like to encrypt or decrypt:");
		
		Scanner interviewer = new Scanner(System.in);
		char[] message = interviewer.nextLine().toCharArray();
		
		System.out.println("For encryptions, please press 1. For decryptions, please press 2.");
		int encryptDecrypt = interviewer.nextInt();
		
		if (encryptDecrypt == 1) {
			System.out.println("Decrypted Message:");
			System.out.println(encryptMessage(message));
		} else if (encryptDecrypt == 2) {
			System.out.println("Encrypted Message:");
			System.out.println(decryptMessage(message));
		} else {
			System.out.println("Invalid Choice");
		}
	}
	
	private static String encryptMessage(char[] message) {
		//first, remove punctuation and spaces
		//count number of alphanumeric letters in message
		int numLegitLetters = 0;
		for (int i = 0; i < message.length; i++) {
			if (Character.isLetterOrDigit(message[i])) {
				numLegitLetters++;
			}
		}
		//create new char array with just the alphanumeric letters
		char[] noPuncMessage = new char[numLegitLetters];
		int j = 0;
		for (int i = 0; i < message.length; i++) {
			if (Character.isLetterOrDigit(message[i])) {
				noPuncMessage[j] = message[i];
				j++;
			}
		}
		//make everything upper case
		for (int i = 0; i < noPuncMessage.length; i++) {
			noPuncMessage[i] = Character.toUpperCase(noPuncMessage[i]);
		}
		//build up the string with letters taken from alternating rows
		String encrypted = "";
		for (int i = 0; i < noPuncMessage.length; i += 2) {
			encrypted += noPuncMessage[i];
		}
		for (int i = 1; i < noPuncMessage.length; i += 2) {
			encrypted += noPuncMessage[i];
		}
		//add extra x's or z's randomly until length multiple of 4
		while (encrypted.length() % 4 != 0) {
			int randInt = (int)(Math.random() * 2); //either 0 or 1
			if (randInt == 0) {
				encrypted += "X";
			} else {
				encrypted += "Z";
			}
		}
		//make final string formatted with spaces
		String grouped = "";
		for (int i = 0; i < encrypted.length(); i += 4) {
			grouped += encrypted.substring(i, i+4) + " ";
		} 
		
		return grouped;
	}
	private static String decryptMessage(char[] message) {
		//make new char array with trailing x's and z's removed
		char[] trimmedMessage;
		int zeesOrExes = 0;
		
		for (int i = message.length - 1; i > message.length - 4; i--) {
			if (Character.toUpperCase(message[i]) == 'X' || Character.toUpperCase(message[i]) == 'Z') {
				zeesOrExes++;
			} else {
				break;
			}
		}
		trimmedMessage = new char[message.length - zeesOrExes];
		for (int i = 0; i < trimmedMessage.length; i++) {
			trimmedMessage[i] = message[i];
		}
		System.out.println(Arrays.toString(trimmedMessage));
		
		//make new char array with spaces removed
		char[] trimmedNoSpaces;
		int numSpaces = 0;
		for (int i = 0; i < trimmedMessage.length; i++) {
			if (trimmedMessage[i] == ' ') {
				numSpaces++;
			}
		}
		trimmedNoSpaces = new char[trimmedMessage.length - numSpaces];
		int j = 0;
		for (int i = 0; i < trimmedMessage.length; i++) {
			if (trimmedMessage[i] != ' ') {
				trimmedNoSpaces[j] = trimmedMessage[i];
				j++;
			}
		}
		System.out.println(Arrays.toString(trimmedNoSpaces));
		
		//split into top row and bottom row arrays
		int topRowLen;
		if (trimmedNoSpaces.length % 2 == 0) {
			topRowLen = trimmedNoSpaces.length / 2;
		} else {
			topRowLen = trimmedNoSpaces.length / 2 + 1;
		}
		int bottomRowLen = trimmedNoSpaces.length - topRowLen;
		char[] topRow = new char[topRowLen];
		char[] bottomRow = new char[bottomRowLen];
		
		//alternatingly put chars into top and bottom rows, starting with top
		for (int i = 0; i < topRowLen; i++) {
			topRow[i] = trimmedNoSpaces[i];
		}
		for (int i = 0; i < bottomRowLen; i++) {
			bottomRow[i] = trimmedNoSpaces[topRowLen + i];
		}
		System.out.println(Arrays.toString(topRow));
		System.out.println(Arrays.toString(bottomRow));
		
		//reform the string
		String decrypted = "";
		for (int i = 0; i < trimmedNoSpaces.length; i++) {
			if (i % 2 == 0) {
				decrypted += topRow[i/2];
			} else {
				decrypted += bottomRow[i/2];
			}
		}
		return decrypted;
	}
}