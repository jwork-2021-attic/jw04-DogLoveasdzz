package com.anish.calabashbros;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[][] a;

    @Override
    public void load(T[][] a) {
        this.a = a;
    }

    private void swap(int i, int j, int i2, int j2) {
        T temp;
        temp = a[i][j];
        a[i][j] = a[i2][j2];
        a[i2][j2] = temp;
        plan += "" + a[i][j] + "<->" + a[i2][j2] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length * a[0].length - 1; i++) {
                int firstI = i / a[0].length;
                int firstJ = i - firstI * a[0].length;
                int secondI = (i + 1) / a[0].length;
                int secondJ = (i + 1) - secondI * a[0].length;
                if (a[firstI][firstJ].compareTo(a[secondI][secondJ]) > 0) {
                    swap(firstI, firstJ, secondI, secondJ);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}