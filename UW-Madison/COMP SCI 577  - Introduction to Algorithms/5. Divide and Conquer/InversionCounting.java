//Yoo Jin Oh
//Oct 13 2022

import java.util.Arrays;
import java.util.Scanner;

public class InversionCounting {
	private int[] arr;
	
	public InversionCounting() {
        arr = null;
    }
	
	private int mergeSortHelper(int left_ind, int middle_ind, int right_ind) {
        int[] left_array = Arrays.copyOfRange(arr, left_ind, middle_ind + 1);
        int[] right_array = Arrays.copyOfRange(arr, middle_ind + 1, right_ind + 1);
        int left_iter = 0, right_iter = 0, iter = left_ind, step_count = 0;
        
        while (left_iter < left_array.length && right_iter < right_array.length) {
            if (left_array[left_iter] <= right_array[right_iter])
                arr[iter++] = left_array[left_iter++];
            else {
                arr[iter++] = right_array[right_iter++];
                step_count += (middle_ind + 1) - (left_ind + left_iter);
            }
        }
        while (left_iter < left_array.length)
            arr[iter++] = left_array[left_iter++];
        while (right_iter < right_array.length)
            arr[iter++] = right_array[right_iter++];
        return step_count;
    }

    private int mergeSort(int left_ind, int right_ind) {
        int step_count = 0;
        if (left_ind < right_ind) {
            int middle_ind = (left_ind + right_ind) / 2;
            
            step_count += mergeSort(left_ind, middle_ind);
            step_count += mergeSort(middle_ind + 1, right_ind);
            step_count += mergeSortHelper(left_ind, middle_ind, right_ind);
        }
        return step_count;
    }
    
    public void setData(int[] data) {
        arr = Arrays.copyOf(data, data.length);
    }
    
    public int getInversion() {
        return mergeSort(0, arr.length - 1);
    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int data_cnt = sc.nextInt();
        InversionCounting[] obj = new InversionCounting[data_cnt];
        
        for (int i = 0; i < data_cnt; i++) {
            int data_size = sc.nextInt();
            int[] data = new int[data_size];
            for (int j = 0; j < data_size; j++) {
                data[j] = sc.nextInt();
            }

            try {
                obj[i] = new InversionCounting();
                obj[i].setData(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < data_cnt; i++) {
            System.out.println(obj[i].getInversion());
        }
    }
}
