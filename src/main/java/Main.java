import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // Array of String , such that each String represent interface
    static ArrayList<String> interfaceAsString=new ArrayList<String>();
    // Array of Interfaces
    static ArrayList<Interface> interfaceObjects=new ArrayList<Interface>();

    public static void main(String[] args) {

        ReadFromFile();
        for (int i = 0; i < interfaceAsString.size(); i++) {
            interfaceObjects.add(convertStringToInterface(interfaceAsString.get(i)));
        }
        System.out.println("We Have #"+interfaceObjects.size());
        for (int i = 0; i <interfaceObjects.size() ; i++) {
            System.out.println("InterFace #"+(i+1));
            System.out.println(interfaceObjects.get(i));
            System.out.println("-----------------------------------------");
        }
    }

    /*
    *   Read From File And Convert it to strings
    * */
    public static void ReadFromFile(){
        try {
            /*
             * Read From File
             * */
            File file = new File("C:\\Users\\user\\Documents\\GitHub\\src\\main\\resources\\input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            /*
            * Initialize empty string
             * */
            String st,interfaceString;
            interfaceString="";
            /*
            * read lines and store it  in one string
            * */
            while ((st = br.readLine()) != null){
                if(st.charAt(0)!=' '){
                    if(interfaceString.length()!=0){
                        interfaceAsString.add(interfaceString);
                    }
                    interfaceString=st;
                }else{
                    interfaceString=interfaceString + "\n" + st;
                }
                }
            // insert the string in Array "interfaceAsString"
            interfaceAsString.add(interfaceString);
        }catch (Exception e){
            System.out.println("File Not Found ,"+e.getMessage());
        }
    }

    /*
    * Function use all helper function below
    * Such that take string parameter and
    * return interface object contain his data
     */
    public static Interface convertStringToInterface(String interfaceString ){
        Interface i = new Interface();
        i.setAdminStatus(findRegGroup0("(?<=is ).*(?=,\\sline protocol)",interfaceString));
        i.setDupleMode(findRegGroup0(".*duplex",interfaceString));
        i.setIdAddress(findRegGroup0("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}",interfaceString));
        i.setIfSpeed(findRegGroup0("(?<=duplex,).*?(?=,)",interfaceString));
        i.setInterfaceDescription(findRegGroup0("(?<=Description:).+",interfaceString));
        i.setMacAddress(findRegGroup0("[0-9a-f]{4}(.)[0-9a-f]{4}(.)[0-9a-f]{4}",interfaceString));
        i.setMtu(findRegGroup0("(?<=MTU ).*?(?=,)",interfaceString));
        i.setOperationStatus(findRegGroup0("(?<=line protocol is ).*",interfaceString));
        i.setName(findRegGroup0("\\A[^\\s]+(?=\\s)",interfaceString));
        return i;
    }
    /*
    * functions analyze interface string
    * */
    public static String findRegGroup0(String regex,String interfaceString){
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }


}
