package aaron.javaramp;




//-----------------------------------------------------------------------------------------------------------------------------
// Private constructor
//-----------------------------------------------------------------------------------------------------------------------------
// by declaring the constructor private, it will block any call that will call into the constructor. this means a) this class cannot be instantiated and can be only accessed through the factory create, and b) it cannot be inheritated.
//
//
//class SingleTonClass {
//    //Static Class Reference
//    private static SingleTonClass obj=null;
//    private SingleTonClass(){
//        /*Private Constructor will prevent
//         * the instantiation of this class directly*/
//    }
//    public static SingleTonClass objectCreationMethod(){
//        /*This logic will ensure that no more than
//         * one object can be created at a time */
//        if(obj==null){
//            obj= new SingleTonClass();
//        }
//        return obj;
//    }
//    public void display(){
//        System.out.println("Singleton class Example");
//    }
//}
//
//
//
//public class Main {
//
//    public static void main(String[] args) {
//        SingleTonClass singleton = SingleTonClass.objectCreationMethod();
//        singleton.display();
//
//    }
//}





//-----------------------------------------------------------------------------------------------------------------------------
// String vs. string buffer
//-----------------------------------------------------------------------------------------------------------------------------
//First of all, String is immutable. Immutable in the sense of memory. It creates new objects every time you create strings or assign a new string/change the value. That's why it is advisable to be careful when using string
//Since String is immutable, str+= "" will cause a complete copy of the original content in str, hence the complexity is 1 + 2 + 3 + 4... = n x 2
//the alternative is to use String Buffer / String Builder
//StringBuffer is synchronized i.e. thread safe. It means two threads can't call the methods of StringBuffer simultaneously. it is hence less efficient
//StringBuilder is non-synchronized i.e. not thread safe. It means two threads can call the methods of StringBuilder simultaneously. it is hence more efficient



//public class Main {
//
//    public static void main(String[] args) {
//
//        long startTime = System.currentTimeMillis();
//
//        StringBuffer sb = new StringBuffer("Java");
//        for (int i=0; i<100000; i++){
//            sb.append("Tpoint");
//        }
//
//        System.out.println("Time taken by StringBuffer: " + (System.currentTimeMillis() - startTime) + "ms");
//
//        startTime = System.currentTimeMillis();
//        StringBuilder sb2 = new StringBuilder("Java");
//        for (int i=0; i<100000; i++){
//            sb2.append("Tpoint");
//        }
//        System.out.println("Time taken by StringBuilder: " + (System.currentTimeMillis() - startTime) + "ms");
//
//
//        startTime = System.currentTimeMillis();
//        String str = "Java";
//        for (int i=0; i<100000; i++){
//            str += "Tpoint";
//        }
//        System.out.println("Time taken by String: " + (System.currentTimeMillis() - startTime) + "ms");
//    }
//}
