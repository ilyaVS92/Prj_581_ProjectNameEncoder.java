import java.util.ArrayList;

/* Notes (Project 84785v1.build20220512)
12.v.22 - completed main features: program generates digits for project names

Proposed features:
13.v.22 -
1. add salt number to product
2. add function to trim spaces from sentence-like statements
3. handle null strings

22.vi.22
need to handle loops where more than the possible number of variations are displayed
so,
jdslkjfslkafjsad and 5 variations would look like
1. 3241
2. 423
3. 34
4. 2
5. 2342418093821903 << have this return "insufficient values" - or just have the whole program return an error

 */
public class Prj_581_encoderRing {
    public String encodeSimple(String input, int length){
        int inputLen = input.length();
        if (length == 0) length = inputLen;
        if (length > inputLen) length = inputLen;
        return toString(actualSingleDigits(StrToArray(input), length));
    }

    private String toString(int[] arry) { //returns a string - used at the very last stage
        String result = "";
        for (int i = 0; i<arry.length; i++){
            result += arry[i];
        }
        return result;
    }

    private int [] StrToArray(String str) { //convert string to array of ints
        int [] fromString = new int [str.length()];
        for (int i = 0; i<str.length(); i++){
            fromString[i] = str.charAt(i);
        }
        return fromString;
    }

    private int [] decreaseLength(int [] inputArr){
        int [] outputArrL1 = new int [inputArr.length-1];
        for (int inputArrCont = 0, L1cont = 0; inputArrCont< outputArrL1.length; inputArrCont++, L1cont++){
            int tempSum = inputArr[inputArrCont] + inputArr[inputArrCont+1];
            while (tempSum>9){
                tempSum = tempSum/10 + tempSum%10;
            }
            outputArrL1[L1cont] = tempSum;
        }
        return outputArrL1;
    }

    private int [] sameLength(int [] inputArr) {
        int [] outputArr = new int[inputArr.length];
        for (int i = 0; i<outputArr.length; i++){
            int tempSum = inputArr[i];
            while (tempSum>9){
                tempSum = tempSum/10 + tempSum%10;
            }
            outputArr[i] = tempSum;
        }
        return outputArr;
    }

    private int [] actualSingleDigits(int [] initialArr, int reqLength) {
        if (initialArr.length == reqLength){ //check if req is already met, return array if that is true;
            return sameLength(initialArr);
        } else if (initialArr.length > reqLength){ //
            int [] tempArr2 = initialArr;
            while (tempArr2.length > reqLength){
                int [] tempArr1 = decreaseLength(tempArr2);
                tempArr2 = tempArr1;
            }
            return tempArr2;
        } else {
//            System.out.println("entered invalid length requirement");
            return null;
        }
    }
    private boolean hasInts(int num){
        String nums = String.valueOf(num);
        if (nums.isEmpty()||nums.length()==0){
            return false;
        }
        return true;
    }

    private int [] actualSingleDigits(int [] initialArr){
        return decreaseLength(initialArr);
    }

    public ArrayList<String> encodeWithVars(String str, int inputDigits, int vars, int mode){
        //LOGIC works, tested 20.06.2022
        ArrayList<String> result = new ArrayList<>();
        int start = -1;
        int cycles = 0;
        int multiplier = 0;
        //take into account type of variations requested: neighboring, desc, or ascending
        switch (mode){
            case 1: //ascending mode
                start = inputDigits;
                cycles = vars+1;
                multiplier = -1;
                break;
            case 2: //descending mode
                start = inputDigits;
                cycles = vars+1;
                multiplier = 1;
                break;
            case 3: //neighboring mode
                start = inputDigits-vars;
                cycles = (vars*2)+1;
                multiplier = -1;
                break;
            default: //none or error mode
                start = inputDigits;
                cycles =1;
        }
        while(cycles>0){
            if (inputDigits!=start){
                result.add(encodeSimple(str, start));
                start -= multiplier;
                cycles--;
            } else {
                result.add(encodeSimple(str, start)+" *");
                start -= multiplier;
                cycles--;
            }
        }
            //ASCENDING METHOD
            // (inputDigits = 5, vars = 3) // 8 7 6 5; (inputDigits = 5, vars = 2) // 7 6 5
            /*
            start = inputDigits;
            continue for vars+1 cycles; ea cycle, inputDigits++
             */
            //DESCENDING METHOD
            // (inputDigits = 5, vars = 3) // 5 4 3 2; (inputDigits = 5, vars = 2) //  5 4 3
            /*
            start = inputDigits;
            continue for vars+1 cycles; ea cycle, inputDigits--
             */
            //NEIGHBOOR METHOD
            // (inputDigits = 5, vars = 3) // 2 3 4 -5- 6 7 8 (inputDigits = 5, vars = 2) // 3 4 -5- 6 7
            /*
            start = inputDigits-(vars/2); ea cycle inputDigits++; continue for (vars*2)+1 cycles

            end value will be '8' = //inputDigits+(vars/)
            */
            // we have 6 variations;
            //


        return result;
    }

    private void addSalt(){
        //adds an additional integer into the array.
        //may help if i'm dealing with a ArrayList
    }
//    private boolean checkValidity (String str, int num){
//        if (num>0 && !str.isEmpty()){
//            if ()
//        }
//        int inputLen = str.length();
//        if (length == 0) length = inputLen;
//        if (length > inputLen) length = inputLen;
//        return false;
//    }
}
