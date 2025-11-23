package taja;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JLabel;

abstract public class Word {
	public ArrayList<String> arr = new ArrayList<>();

	abstract void create();

	abstract void shuffle();

}

class WordData extends Word {

	@Override
	public void create() {
		try {
			Scanner inputStream = new Scanner(
					new File("word.txt"));
			while (inputStream.hasNextLine()) // word.txt에 단어가 없을 때 까지 읽음
				this.arr.add(inputStream.nextLine());
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}

	}

	@Override
	void shuffle() {
		Collections.shuffle(this.arr); //문제 무작위로 섞음
	}
}