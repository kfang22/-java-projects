import java.util.ArrayList;

public class Review <T extends Number>{ // <R> only applies to instance methods of the class
    public static void main(String[] args){
        ArrayList list; // object type if type is not specified

        Double[]doubles = new Double[] {3.0,4.0,5.0,6.0,7.0,};
        Integer[] integers = new Integer[]{3,3,3,3};
        new Review<Double>().printMiddle(doubles);
        new Review<Integer>().printMiddle(integers);
        //doubles[0] = (Double)printMiddle(doubles); // need to up cast if Object printMiddle, not if T printMiddle

    }

    public T printMiddle(T [] array){ // expands generic to limit what can be generic, Number only allows number related types
        Integer index = array.length/2;
        T print = array[index];
        System.out.println(print.intValue());
        return print;
    }



    //public static void complexityTrace(ArrayStrin)




}
