package EBPSO;
/**
 *
 * @author wissem
 */
//------------Keyboard program-------------------

//file name="Keyboard.java"
//class providing keyboard reading functions
import java.io.*;
public class Keyboard{
public static String readString(){
String read_line=null;
try{
InputStreamReader reader=new InputStreamReader(System.in);
BufferedReader entry=new BufferedReader(reader);
read_line=entry.readLine();
}
catch(IOException err){
System.exit(0);
}
return read_line;
}
////////////////**************************************
public static float readFloat(){
float x=0;
try{
String read_line=readString();
x=Float.parseFloat(read_line);
}
catch(NumberFormatException err){
System.out.println("***Float Data error expected***");
System.exit(0);
}
return x;
}
///////////////*********************************************
public static double readDouble(){
double x=0; 
try{
String read_line=readString();
x=Double.parseDouble(read_line);
}
catch(NumberFormatException err){
System.out.println("***Double Data error expected***");
System.exit(0);
}
return x;
}
///////////*******************************************
public static int readInt(){
int x=0; 
try{
String read_line=readString();
x=Integer.parseInt(read_line);
}
catch(NumberFormatException err){
System.out.println("***Int Data error expected***");
System.exit(0);
}
return x;
} 
}