package edu.sdsu.abstractData;
import java.util.Arrays;
public class ArrayList<DataType> {
    private Object[] data;
    private int size = 0;

    public ArrayList(){
        data = new Object[10];
    }

    public Object get(int index){
        if(index < size){
            return data[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean add(DataType value){
        if(data.length - size <= 5){
            increaseListSize();
        }
        data[size++] = value;
        return true;
    }
    public void add(int bucketIndex, DataType value){
        if(data.length - size <= 5){
            increaseListSize();
        }
        data[size++] = value;
    }

    public Object remove(int index){
        if(index < size){
            Object obj = data[index];
            data[index] = null;
            int restingIndex = index;
            while(restingIndex < size){
                data[restingIndex] = data[restingIndex+1];
                data[restingIndex+1] = null;
                restingIndex++;
            }
            size--;
            return obj;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    public int size(){
        return size;
    }

    private void increaseListSize(){
        data = Arrays.copyOf(data, data.length*2);
        System.out.println("\nNew length: "+ data.length);
    }

}
