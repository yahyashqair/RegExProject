import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public @Setter @Getter class Interface {

    /*
    * Representation of interfaces as a class called interface
    * */
    String name;
    String idAddress;
    String interfaceDescription;
    String ifSpeed;
    String dupleMode;
    String adminStatus;
    String operationStatus;
    String macAddress;
    String mtu ;

    //Setter and Getter Using Lombok


    @Override
    public String toString() {
        return  "\n name='" + name + '\'' +
                ",\n idAddress='" + idAddress + '\'' +
                ",\n interfaceDescription='" + interfaceDescription + '\'' +
                ",\n ifSpeed='" + ifSpeed + '\'' +
                ",\n dupleMode='" + dupleMode + '\'' +
                ",\n adminStatus='" + adminStatus + '\'' +
                ",\n operationStatus='" + operationStatus + '\'' +
                ",\n macAddress='" + macAddress + '\'' +
                ",\n mtu='" + mtu + '\'';
    }
}
