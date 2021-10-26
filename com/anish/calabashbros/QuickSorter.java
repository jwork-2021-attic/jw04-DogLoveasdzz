package com.anish.calabashbros;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

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

    private void quickSortStep(int head, int tail){
        if(head >= tail)
            return;
        int pivot = head;
        int headI = head / a[0].length;
        int headJ = head - headI * a[0].length;
        for(int i = head + 1; i <= tail; i++){
            int firstI = i / a[0].length;
            int firstJ = i - firstI * a[0].length;
            if(this.a[firstI][firstJ].compareTo(this.a[headI][headJ]) < 0){
                pivot++;
                int secondI = pivot / a[0].length;
                int secondJ = pivot - secondI * a[0].length;
                swap(firstI, firstJ, secondI, secondJ);
            }
        }
        int firstI = headI;
        int firstJ = headJ;
        int secondI = pivot / a[0].length;
        int secondJ = pivot - secondI * a[0].length;
        swap(firstI, firstJ, secondI, secondJ);
        this.quickSortStep(head, pivot - 1);
        this.quickSortStep(pivot + 1, tail);
    }

    @Override
    public void sort() {
        if(this.a == null)
        return;
        this.plan = "";
        this.quickSortStep(0, this.a.length * a[0].length - 1);
    }

    @Override
    public String getPlan() {
        return this.plan;
    }
}

