package com.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/** This class has various implementations of removing the duplicate elements from the given array.
 *  User can provide the input type to get the desired output.
 * @author Deepesh
 *
 */
public class DeDup {

	public static int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16,
			19, 1, 18, 4, 9, 3, 20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16,
			3, 6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

	public static String scenarios = "Random | Sorted | Original | Input | Other";

	public static void main(String[] args) {
		Scanner scan = null;
		String order = "";
		try {
			/** Read the user input either from Command Line or Scanner. */
			scan = new Scanner(System.in);
			if (args != null && args.length > 0) {
				if (args.length > 1) {
					System.err.println("Please provide only one scenario..");
					System.exit(0);
				}
				System.out.println("User provided Command Line input: " + args[0]);
				order = args[0];
			} else {
				if (scan == null || !scan.hasNext()) {
					System.err.println("No input Provided.");
					System.exit(0);
				} else {
					order = scan.next();
					System.out.println("User provided Console input: " + order);
				}
			}

			/**
			 * Validate the input as provided - 
			 * Random: For Unique Array Elements in any order. 
			 * Sorted: For Unique Array Elements in Sorted order.
			 * Original: For Unique Array Elements in Original order. 
			 * Input: For Original source Array Elements as is with Duplicates i.e. no operation performed. 
			 * Other: For Unique Array Elements in original order using simple basic approach.
			 */

	
			
			
			/** Validate the input as expected. */
			if (!scenarios.contains(order)) {
				System.err.println("Please provide a valid input scenario type i.e."
						+ " Random | Sorted | Original | Input | Other");
				return;
			}

			/** Call method and provide the scenario type obtained from the input. */
			Integer[] uniqueArray = removeDuplicates(randomIntegers, order);
			if (uniqueArray == null) {
				System.err.println("Input type did not match as Expected.");
				// Log.error("Input type did not match as Expected.");
				return;
			}

			/** Display the Array Elements. */
			printArray(uniqueArray);

		} catch (Exception ex) {
			System.err.println("Exception: " + ex.getMessage());
			// Log.error("Exception: " + ex.getMessage());
		} finally {
			if (scan != null)
				scan.close();
		}
	}

	/** This method uses the switch case and performs based on the user input provided.
	 * @param inputArr
	 * @param order
	 * @return Integer[]
	 */
	private static Integer[] removeDuplicates(int[] inputArr, String order) {
		switch (order) {
		case "Input":
			return Arrays.stream(inputArr).boxed().toArray(Integer[]::new);
			
		case "Original":
			Integer[] uniqueArrayOriginal = removeDup(inputArr);
			return uniqueArrayOriginal;
		
		case "Random":
			Integer[] uniqueArrayRandom = removeDuplicates(inputArr);
			return uniqueArrayRandom;
		
		case "Sorted":
			Integer[] uniqueSortedArray = removeDuplicatesAndSort(inputArr);
			return uniqueSortedArray;
		
		default:
			Integer[] uniqueArray = removeDupUsingBasic(inputArr);
			return uniqueArray;
		}
	}

	/** Remove duplicate elements and retains the original order using JAVA 8 APIs.
	 * @param arr
	 * @return Integer[]
	 */
	protected static Integer[] removeDup(int arr[]) {
		return Arrays.stream(arr).distinct().boxed().toArray(Integer[]::new);
	}

	/** Remove duplicate elements and retains the original order using simple basic approach which may 
	 * required improved solution.
	 * @param arr
	 * @return Integer[]
	 */
	protected static Integer[] removeDupUsingBasic(int[] arr) {
		int end = arr.length;
		for (int i = 0; i < end; i++) {
			for (int j = i + 1; j < end; j++) {
				if (arr[i] == arr[j]) {
					arr[j] = arr[end - 1];
					end--;
					j--;

				}
			}
		}
		int[] uniqueList = new int[end];
		System.arraycopy(arr, 0, uniqueList, 0, end);
		return Arrays.stream(uniqueList).boxed().toArray(Integer[]::new);

	}

	/** Removes duplicate elements but does not guarantee the order since using Set.
	 * @param arr
	 * @return Integer[]
	 */
	protected static Integer[] removeDuplicates(int arr[]) {
		int end = arr.length;
		Set<Integer> setArr = new HashSet<>();
		for (int i = 0; i < end; i++) {
			setArr.add(arr[i]);
		}
		return setArr.toArray(new Integer[setArr.size()]);
		// return setArr.stream().toArray(Integer[]::new);
	}

	/** Remove duplicates using Set and sort the array.
	 * @param arr
	 * @return Integer[]
	 */
	protected static Integer[] removeDuplicatesAndSort(int arr[]) {
		int end = arr.length;
		Set<Integer> setArr = new HashSet<>();
		for (int i = 0; i < end; i++) {
			setArr.add(arr[i]);
		}
		Integer[] uniqueArr = setArr.toArray(new Integer[setArr.size()]);
		Arrays.sort(uniqueArr);
		return uniqueArr;
	}

	/** Prints the array elements.
	 * @param arr
	 */
	protected static void printArray(Integer[] arr) {
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			System.out.print(arr[i] + "\t");
		}
	}

}
