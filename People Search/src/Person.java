public class Person {
	
	private String name;
	private int age;
	private int comparisons;
	
	public Person (String n, int a) {
		name = n;
		age = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getComparisons() {
		return comparisons;
	}

	
	public String sequentialSearch(Person[] array, String searchName) {
		comparisons = 1;
		String messageBoxOutput = "";
		for (int i = 0; i < array.length; i++) {
			if (searchName.equals(array[i].getName().toLowerCase())) {
				messageBoxOutput += "Name: " + array[i].getName() + "     Age: " + array[i].getAge() + '\n';
				break;
			}
			comparisons++;
		}
		if (messageBoxOutput.isBlank()) {
			return "There is nobody matching your search";
		}
		else {
			return messageBoxOutput;
		}
	}
	
	
	public String binarySearch(Person[] array, String searchName) {
		comparisons = 1;
		int index = -1;
		int lowEnd = 0;
		int highEnd = array.length - 1;
		if (searchName.trim().toLowerCase().equals(array[0].getName().trim().toLowerCase())) {
			index = 0;
		}
		else {
			while (highEnd >= lowEnd) { // Difference of highEnd and lowEnd decreases as the search range narrows
				int middle = (lowEnd + highEnd) / 2;
				// checks if the middle of the lowEnd and the highEnd is the target
				if (array[middle].getName().equals(searchName)) {
					index = middle; // the target is found
					break;
				}
				else if (array[middle].getName().compareToIgnoreCase(searchName) < 0) {
					// changes the lowEnd to narrow the search range
					lowEnd = middle + 1;
				}
				else if (array[middle].getName().compareToIgnoreCase(searchName) > 0) {
					// changes the highEnd to narrow the search range
					highEnd = middle - 1;
				}
				comparisons++; 
				System.out.println(comparisons);
			}
		}
		if (index == -1) {
			return "There is nobody matching your search";
		}
		else {
			return "Name: " + array[index].getName() + "     Age: " + array[index].getAge();
		}
	}
	
	public Person [] sortByAlphabet(Person arr[], int counter){
		int n = counter;
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n-1; i++) { 
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i+1; j < n; j++){
				if (arr[j].getName().trim().compareToIgnoreCase(name) < arr[min_idx].getName().trim().compareToIgnoreCase(name))
	                   min_idx = j;
			}
			// Swap the found minimum element with the first element
			Person temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
		}
		return arr;
	}
	
}