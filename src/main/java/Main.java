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
        i.setAdminStatus(findAdminStatus(interfaceString));
        i.setDupleMode(findDuplexMode(interfaceString));
        i.setIdAddress(findIP(interfaceString));
        i.setIfSpeed(findSpeed(interfaceString));
        i.setInterfaceDescription(findDescription(interfaceString));
        i.setMacAddress(findMacAddress(interfaceString));
        i.setMtu(findMTU(interfaceString));
        i.setOperationStatus(findOperationStatus(interfaceString));
        i.setName(findInterfaceName(interfaceString));
        return i;
    }
    /*
    * functions analyze interface string
    * */
    public static String findInterfaceName(String interfaceString){
        final String regex = "\\A[^\\s]+(?=\\s)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while(matcher.find()){
            return matcher.group(0);
        }
    return "";
    }
    public static String findAdminStatus(String interfaceString){
        final String regex = "(?<=is ).*(?=,\\sline protocol)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String findOperationStatus(String interfaceString){
        final String regex = "(?<=line protocol is ).*";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String findMacAddress(String interfaceString){
        final String regex = "[0-9a-f]{4}(.)[0-9a-f]{4}(.)[0-9a-f]{4}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String findDescription(String interfaceString){
        final String regex =  "(?<=Description:).+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String findIP(String interfaceString){
        final String regex = "\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String findMTU(String interfaceString){
        final String regex = "(?<=MTU ).*?(?=,)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String findDuplexMode(String interfaceString){
        final String regex = ".*duplex";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String findSpeed(String interfaceString){
        final String regex =  "(?<=duplex,).*?(?=,)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

}
