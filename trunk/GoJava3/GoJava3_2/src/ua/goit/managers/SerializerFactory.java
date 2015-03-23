package ua.goit.managers;

public class SerializerFactory {

  public static Serializer getSerializer(SerializerType type) {
    if (type==SerializerType.SML){
      return new XMLSerializer();
    } else if (type==SerializerType.JSON){
      return new JSONSerializer();
    } else {
      return new XMLSerializer(); 
    }

  }
}