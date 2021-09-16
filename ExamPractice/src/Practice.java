public class Practice {

    private static int staticField;
    private int instanceField;

    public static void main(String[] args)throws Exception{
        //Example 1-topics about using object
        Integer[] array = new Integer[3];
        array[0] = 4;// auto-boxing converting the primitive value to a object
        array[1] = new Integer(4);
        array[2] = array[0] + 2; // can only do addition on primitive values
                                    // out-boxing Integer 4 so that values can add, then auto-box 6 to new object


        //Example 2
        System.out.println(staticField);//default value prints out 0
        //System.out.println(instanceField);//can't print out, one instance field per object,
                                            // no instances fields created
        Practice r = new Practice();
        System.out.println(r.instanceField);//default value prints out 0, needs object to call instance field
        r.printSomething();



        //Example 3
        /**
        throw new RectangleException();//unchecked exception if there are no errors with RectangleException()
                                        //it doesn't get caught or method throws the exception
        try {
            throw new TriangleException();//checked exception, needs to be caught or method throws the exception
        } catch(TriangleException e) {

        }
         */

    }
    //Example 2
    public void printSomething(){
        System.out.println(staticField);
        Practice r = new Practice();
        System.out.println(instanceField);//uses instanceField printSomething() is called upon
    }

    BatteryPoweredDevice[] devices = new BatteryPoweredDevice[] {
            new CellPhone(),new CellPhone(),new BoomBox(),new BatteryPoweredDevice(0.2)};
            for(int i = 0, i < devices.length; i++){
        System.out.println(devices[i]);
        devices.charge();
        System.out.println(devices[i]);
            }
    }


//Example 4
class BatteryPoweredDevice{
    private double powerLeft;
    public BatteryPoweredDevice(double powerLeft){
        this.powerLeft = powerLeft;
    }
    public void charge(){
        if(powerLeft < 0.95){
            powerLeft += 0.05;
        }
    }

    public String toStinrg(){
        String return1 = ("power is " + powerLeft);
        return return1;
    }
}
class CellPhone extends BatteryPoweredDevice{
    private int phoneNumber;
    public CellPhone(){// don't need this if super constructor doesn't take arguments(if double powerLeft didn't exist)
        //phoneNumber = 123;//super constructor has to run first before doing anything else
        super(0.4);// needs to call constructors of parent class
        phoneNumber = 123;
    }
    @Override
    public void charge(){
        for(int i = 0; i < 5; i++) {
            super.charge();
        }
    }
}

class BoomBox extends BatteryPoweredDevice{
    public BoomBox(){
        super((0.8));
    }
}
