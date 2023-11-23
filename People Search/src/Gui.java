import javax.swing.*;
import BreezySwing.*;
public class Gui extends GBFrame {
	JLabel nameLabel = addLabel ("Name: ", 1, 1, 1, 1);
	JTextField nameInput = addTextField ("", 1, 2, 1, 1);
	
	JLabel ageLabel = addLabel ("Age: ", 2, 1, 1, 1);
	JTextField ageInput = addTextField ("", 2, 2, 1, 1);
	
	JButton binary = addButton("Binary Search", 3, 1, 1, 1);
	
	JButton enter = addButton("Enter", 3, 2, 1, 1);
	
	JButton sequential = addButton("Sequential Search", 4, 1, 1, 1);
	
	JButton update = addButton("Update", 4, 2, 1, 1);
	
	JButton alphabetical = addButton("Print by Alphabetical Order", 5, 1, 1, 1);
	
	JButton delete = addButton("Delete", 5, 2, 1, 1);
	
	JLabel indexLabel = addLabel ("Person # to update: ", 6, 1, 1, 1);
	
	JTextField index = addTextField ("", 6, 2, 1, 1);
	
	
	int counter = 0;
	Person[] array1;
	
	public void buttonClicked(JButton buttonObj) {
		if (buttonObj == enter	) {
			try {
				if (nameInput.getText().isBlank() || ageInput.getText().isBlank()) { //error checking
					messageBox("Please fill in the input fields");
				}
				else if (counter == 20) { //error checking
					messageBox("You cannot input more than 20 people");
				}
				else {
					if (counter == 0) { //first person being added
						Person[] array2 = new Person[1];
						array2[0] = new Person(nameInput.getText().trim(), Integer.parseInt(ageInput.getText().trim()));
						array1 = array2;
						counter++;
					}
					else { //not first person being added
						Person[] array2 = new Person[counter + 1];
						for (int i = 0; i < counter; i++) { //copies old array to new array
							array2[i] = array1[i];
						}
						array2[counter] = new Person(nameInput.getText().trim(), Integer.parseInt(ageInput.getText().trim()));
						array1 = array2;
						counter++;
						array1 = array1[0].sortByAlphabet(array1, counter); //sorts array into alphabetical order
					}
					nameInput.setText("");
					ageInput.setText("");
					index.setText("");
					nameInput.grabFocus();
				}
			}
			catch (Exception e) {
				messageBox("Invalid input");
			}
		}
		
		
		if (buttonObj == binary	) {
			if (counter == 0) { //error checking
				messageBox("Please input a person first");
			}
			else if (nameInput.getText().isBlank()) { //error checking
				messageBox("Please input a person to search");
			}
			else {
				Person person = new Person("", 0);
				messageBox(person.binarySearch(array1, nameInput.getText().trim().toLowerCase()) + '\n' + "Comparisons made: " + person.getComparisons());
			}
		}
		
		
		if (buttonObj == sequential	) {
			if (counter == 0) { //error checking
				messageBox("Please input a person first");
			}
			else if (nameInput.getText().isBlank()) { //error checking
				messageBox("Please input a person to search");
			}
			else {
				Person person = new Person("", 0);
				messageBox(person.sequentialSearch(array1, nameInput.getText().trim().toLowerCase()) + '\n' + "Comparisons made: " + person.getComparisons());
			}
		}
		
		
		if (buttonObj == alphabetical	) {
			if (counter == 0) { //error checking
				messageBox("Please input a person first");
			}
			else {
				String messageBoxOutput = "";
				for (int i = 0; i < counter; i++) {
					messageBoxOutput += "Person #" + (i+1) + "     Name: " + array1[i].getName() + "     Age: " + array1[i].getAge() + '\n';
				}
				if (messageBoxOutput.isBlank()) {
					messageBox("Please input information first");
				}
				else {
					messageBox(messageBoxOutput, 350, 300);
				}
			}
		}
		
		if (buttonObj == update	) {
			try {
				if (counter == 0) { //error checking
					messageBox("Please enter a person first");
				}
				else if (index.getText().isBlank()) { //error checking
					messageBox("Please input a person's number to update. You can see a person's assigned number by clicking the 'Print in Alphabetical Order' button", 1000, 150);
				}
				else if (nameInput.getText().isBlank() && ageInput.getText().isBlank()) { //error checking
					messageBox("Please input a name or age to update");
				}
				else if (Integer.parseInt(index.getText().trim()) > counter || Integer.parseInt(index.getText().trim()) < 1) { //error checking
					messageBox("That person does not exist");
				}
				else {
					if (!nameInput.getText().isBlank() && ageInput.getText().isBlank()) { //if only name is being updated
						array1[Integer.parseInt(index.getText().trim())-1].setName(nameInput.getText().trim());
					}
					else if (nameInput.getText().isBlank() && !ageInput.getText().isBlank()) { //if only age is being updated
						array1[Integer.parseInt(index.getText().trim())-1].setAge(Integer.parseInt(ageInput.getText().trim()));
					}
					else if (!nameInput.getText().isBlank() && !ageInput.getText().isBlank()) { //if name and age are being update
						array1[Integer.parseInt(index.getText().trim())-1].setName(nameInput.getText().trim());
						array1[Integer.parseInt(index.getText().trim())-1].setAge(Integer.parseInt(ageInput.getText().trim()));
					}
					messageBox("Person #" + index.getText().trim() + " has been update");
					nameInput.setText("");
					ageInput.setText("");
					index.setText("");
					array1 = array1[0].sortByAlphabet(array1, counter); //sorts array into alphabetical order
				}
			}
			catch (Exception e) { //catches parsing error
				messageBox("Invalid input");
			}
		}
		
		if (buttonObj == delete) {
			if (counter == 0) { //error checking
				messageBox("Please input a person first");
			}
			else if (nameInput.getText().isBlank()) { //error checking
				messageBox("Please choose a person's name to delete");
			}
			else {
				Person[] temp = new Person[counter-1];
				boolean found = false;
				for (int i = 0; i < counter; i++) {
					if (nameInput.getText().trim().toLowerCase().equals(array1[i].getName())) {
						for (int j = 0; j < i; j++) {
							temp[j] = array1[j];
						}
						for (int j = i; j < counter-1; j++) {
							temp[j] = array1[j+1];
						}
						found = true;
						counter--;
						break;
					}
				}
				array1 = temp; //creates new array without index user wants to delete
				if (found) {
					array1 = array1[0].sortByAlphabet(array1, counter); //sorts array into alphabetical order
					messageBox(nameInput.getText().trim() + " has been removed");
					nameInput.setText("");
					ageInput.setText("");
					index.setText("");
				}
				else {
					messageBox("That person does not exist");
				}
			}
		}
	}
	
	//outputs GUI
	public static void main(String[] args) {
		JFrame frm = new Gui();
		frm.setTitle("People Search");
		frm.setSize(600, 400);
		frm.setVisible(true);
	}
}