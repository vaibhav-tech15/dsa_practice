package practice.dsa.seralize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import practice.dsa.model.Person;

public class SeralizeObjects {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person[] people = new Person[2];
		
		people[0] = new Person();
		people[0].setName("Ramesh");
		people[0].setId(1);
		people[0].setAge(53);
		
		people[1] = new Person();
		people[1].setName("Suresh");
		people[1].setId(2);
		people[1].setAge(22);
		
		byte[] serializedPeople = serializePersons(people);
				
		System.out.println("Sum of ages is: " + findAgeSum(serializedPeople));
	}

	private static int findAgeSum(byte[] serializedPeople) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(serializedPeople));
		Person[] people = (Person[]) inputStream.readObject();
		System.out.println("Total persons: " + people.length);
		return Arrays.stream(people).map(Person::getAge).reduce(0,Integer::sum);
	}

	public static byte[] serializePersons(Person[] people) throws IOException {
		ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(outputBytes);
		outputStream.writeObject(people);
		outputStream.close();
		outputBytes.close();
		return outputBytes.toByteArray();
	}
}
