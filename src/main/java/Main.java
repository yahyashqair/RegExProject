import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // Array of String , such that each String represent interface
    static ArrayList<String> interfaceAsStrings=new ArrayList<String>();
    // Array of Interfaces
    static ArrayList<Interface> interfaceObjects=new ArrayList<Interface>();

    public static void main(String[] args) {

        ReadFromFile();
        for (int i = 0; i < interfaceAsStrings.size(); i++) {
            interfaceObjects.add(convertStringToInterface(interfaceAsStrings.get(i)));
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
            String st,interfaceString;
            interfaceString="";
            while ((st = br.readLine()) != null){
                if(st.charAt(0)!=' '){
                    if(interfaceString.length()!=0){
                        interfaceAsStrings.add(interfaceString);
                    }
                    interfaceString=st;
                }else{
                    interfaceString=interfaceString + "\n" + st;
                }
                }
            interfaceAsStrings.add(interfaceString);
        }catch (Exception e){
            System.out.println("File Not Found ,"+e.getMessage());
        }
    }


    public static Interface convertStringToInterface(String interfaceString ){
        Interface i = new Interface();
        i.setAdminStatus(FindAdminStatus(interfaceString));
        i.setDupleMode(FindDuplexMode(interfaceString));
        i.setIdAddress(FindIP(interfaceString));
        i.setIfSpeed(FindSpeed(interfaceString));
        i.setInterfaceDescription(FindDescription(interfaceString));
        i.setMacAddress(FindMacAddress(interfaceString));
        i.setMtu(FindMTU(interfaceString));
        i.setOperationStatus(FindOperationStatus(interfaceString));
        i.setName(FindInterfaceName(interfaceString));
        return i;
    }
    /*
    * functions analyze interface string
    * */
    public static String FindInterfaceName(String interfaceString){
        final String regex = "\\A[^\\s]+(?=\\s)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while(matcher.find()){
            return matcher.group(0);
        }
    return "";
    }
    public static String FindAdminStatus(String interfaceString){
        final String regex = "(?<=is ).*(?=,\\sline protocol)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String FindOperationStatus(String interfaceString){
        final String regex = "(?<=line protocol is ).*";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String FindMacAddress(String interfaceString){
        final String regex = "[0-9a-f]{4}(.)[0-9a-f]{4}(.)[0-9a-f]{4}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String FindDescription(String interfaceString){
        final String regex =  "(?<=Description:).+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String FindIP(String interfaceString){
        final String regex = "\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String FindMTU(String interfaceString){
        final String regex = "(?<=MTU ).*?(?=,)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String FindDuplexMode(String interfaceString){
        final String regex = ".*duplex";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public static String FindSpeed(String interfaceString){
        final String regex =  "(?<=duplex,).*?(?=,)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(interfaceString);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

}
