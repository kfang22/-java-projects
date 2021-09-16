public interface ListADT<T> {
    //problem size n = number of elementsin the list
    //compare this to the time(number of steps)

    //substet of methods from java.util.Collections: unordered collections of data

    public void add(T newObject);
    //array: 0(1) when shadow array is used to spread out the copuing into bigger arrays
    //linked node: o(1) maintain a tail reference to jump directly to the end

    public boolean contains(T findOBject); //0(n)

    public int size(); //0(1) use size field to track this

    public boolean isEmpety();//0(1)


    // subset of methods from java.util.List: ordered collection of data
    public void add(int index, T newObject);
    //array: 0(n) shifting array contents to make room for new balue
    //linked node: 0(n) stepping through index node to find the place to add

    public int indexOf(T findObject); //0(n)

    public T get(int index);
    //array: 0(1)
    //linked nide: 0(n) stepping through index node to find the place add

    public T remove(int index);
    //array: 0(n) shifting array contents to fill in the hole left from removed value
    //linked node:0(n) stepping through index-1 node to find the place to add


}
