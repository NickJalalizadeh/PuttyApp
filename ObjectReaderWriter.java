package my.PuttyAppUI;

import java.awt.*;
import java.io.*;

public class ObjectReaderWriter {
  private static Object[] thisObject;
  
  public static void main( String args[] ) {
      
  }

  public static void WriteObject(Object[] obj, String filePath) {
    try {
        FileOutputStream fos = new FileOutputStream( filePath );
        ObjectOutputStream outStream = new ObjectOutputStream( fos );

        outStream.writeObject( obj );

        outStream.flush();
        outStream.close();
        fos.close();
    }
    catch ( InvalidClassException e ) {
      System.out.println( "InvalidClassException..." );
    }
    catch ( OptionalDataException e ) {
      System.out.println( "OptionalDataException..." );
    }
    catch ( FileNotFoundException e ) {
      System.out.println( "FileNotFoundException..." );
    }
    catch ( IOException e ) {
      System.out.println( "IOException..." );
      e.printStackTrace();
    }
  }
  
  public static Object[] ReadObject(String filePath) {
    try {
      FileInputStream fis = new FileInputStream( filePath );
      ObjectInputStream inStream = new ObjectInputStream( fis );

      thisObject = ( Object[] )inStream.readObject();
      inStream.close();
      fis.close();
    }
    catch ( InvalidClassException e ) {
      System.out.println( "InvalidClassException..." );
    }
    catch ( ClassNotFoundException e ) {
      System.out.println( "ClassNotFoundException..." );
    }
    catch ( OptionalDataException e ) {
      System.out.println( "OptionalDataException..." );
    }
    catch ( FileNotFoundException e ) {
      System.out.println( "FileNotFoundException..." );
    }
    catch ( IOException e ) {
      System.out.println( "IOException..." );
    }
    return thisObject;
  }
}