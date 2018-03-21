/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;
import librarymanagement.HeadLibrarians;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.util.Scanner;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author AVRO
 */
public class bookManagementModule {

    
    
    
    public bookManagementModule() throws FileNotFoundException {
        
        System.out.println("Please select an option:\n0.View all librarians\n1.Register Member\n2.Add Book Catagory\n3.Delete Book Catagory\n4.Add book in specific catagory\n5.Modify catagory\n6.Delect book in specific catagory\n7.Modify book in specific catagory\n8.Log out");
        Scanner input = new Scanner(System.in);
        
        switch(input.nextInt()){
            case 0:
                HeadLibrarians.viewlibrarian();
                HeadLibrarians.viewmembers();
                break;
            case 1:
                memberRegistration();
            break;
            case 2: 
                addBookCatagory();
            break;
            case 3:
                deleteBookCatagory();
            break;
            case 4:
                System.out.println(addbook());
                break;
            case 5:
                System.out.println(modifycatagory());
                break;
            case 6:
                System.out.println(deleteBookinspecificCatagory());
            case 7:
                System.out.println(modifybookinspecificcatagory());
            case 8:
                logout();
                
                break;
            default:System.out.println("Invalid selection");
            break;
        }
        
    }
    
    public boolean memberRegistration(){
    boolean memberRegistrationSuccessful = false;
    Scanner input = new Scanner(System.in);
        System.out.println("Please enter a username for registration:");
        String username = input.nextLine();
        System.out.println("Please enter the password:");
        String password = input.nextLine();
        System.out.println("Please enter your Fullname: ");
        String fullName = input.nextLine();
        System.out.println("Please enter your Phone Number: ");
        String phoneNo = input.nextLine();
        
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            PrintWriter registration = new PrintWriter(new FileOutputStream("member.txt",true));
            registration.println(username + "," + password + "," + fullName + "," + phoneNo + "," + dateFormatter.format(now));
         
            registration.close();
            memberRegistrationSuccessful = true;
            
        } catch (Exception e) {
            
        }
    return  memberRegistrationSuccessful;
   
    }
    
    public void addBookCatagory(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the name of book catagory: ");
        String catagoryName = input.nextLine();
        try {
            PrintWriter addCatagory = new PrintWriter(new FileOutputStream("bookcatagories.txt",true));
            addCatagory.println(catagoryName);
            PrintWriter addcatagoryfile = new PrintWriter(catagoryName+".txt");//???????????
            addCatagory.close();
        } catch (Exception e) {
        }
        
    }
    
    
    public void deleteBookCatagory(){
        boolean bookcatagoryDeleted = false;
        int index = 0;
        
        try {
            Scanner catagories =new Scanner(new FileInputStream("bookcatagories.txt"));
             System.out.println("Please select a catagory to delete: ");
            
            while (catagories.hasNext()) {
                String catagory = catagories.next();//just called a variable
                
                System.out.println(index+1+"."+catagory);
                index++;
            }
            //System.out.println(index);
        } catch (Exception e) {
        }
        
        String arraydata[] = new String[index];
        try {
            Scanner catagories =new Scanner(new FileInputStream("bookcatagories.txt"));
            //get the array properly!
            String tempdata="";
            while (catagories.hasNext()) {
                tempdata +=catagories.next()+" ";
                
            }
            arraydata = tempdata.split(" ");
        } catch (Exception e) {
        }
        
                
        
        Scanner input = new Scanner(System.in);
        int userinputnumber = input.nextInt();
        try {
                PrintWriter modifiedCatagories = new PrintWriter(new FileOutputStream("bookcatagories.txt"));
                modifiedCatagories.print("");
                modifiedCatagories.close();
            } catch (Exception e) {
            }
        for (int i = 0; i < index; i++) {
            try {
                PrintWriter modifiedCatagories = new PrintWriter(new FileOutputStream("bookcatagories.txt",true));
                //not adding the catagory on the file
                if(userinputnumber-1!=i){
                    modifiedCatagories.println(arraydata[i]);//???????????????
                }
                modifiedCatagories.close();
            } catch (Exception e) {
            }
            
        }
        
    }
   
    public boolean modifycatagory(){
  boolean bookcatagoryModified = false;
        int index = 0;
        String alldata = "";
        try {
            Scanner catagories =new Scanner(new FileInputStream("bookcatagories.txt"));
             System.out.println("Please select a catagory to update/modify: ");
            
            while (catagories.hasNext()) {
                String catagory = catagories.next();//just called a variable
                alldata+=catagory+",";
                System.out.println(index+1+"."+catagory);
                index++;
            }
            //System.out.println(index);
        } catch (Exception e) {
        }
        String catagoriesArray[] = alldata.split(",");
        Scanner input1 = new Scanner(System.in);
        int selection = input1.nextInt()-1;
        System.out.println("Please enter the new name:");
        Scanner input2 = new Scanner(System.in);
        String update = input2.nextLine();
        catagoriesArray[selection] = update;
        //cleaning the book catagory file
        try {
                PrintWriter modifiedCatagories = new PrintWriter(new FileOutputStream("bookcatagories.txt"));
                modifiedCatagories.print("");
                modifiedCatagories.close();
            } catch (Exception e) {
            }
        
        try {
          PrintWriter modifiedCatagories = new PrintWriter(new FileOutputStream("bookcatagories.txt",true));
                for (int i = 0; i < catagoriesArray.length; i++) {
                modifiedCatagories.println(catagoriesArray[i]);
                
            }
                modifiedCatagories.close();
                bookcatagoryModified = true;
      } catch (Exception e) {
      }
        
        
  return bookcatagoryModified;
  }
  
  
    public boolean addbook(){
    boolean addbooksuccessfully=false;
    int index=0;
    String alldata="";
    try {
            Scanner catagories =new Scanner(new FileInputStream("bookcatagories.txt"));
             System.out.println("Please select and type the catagory name to add new book: ");
            
            while (catagories.hasNext()) {
                String catagory = catagories.next();//just called a variable
                alldata+=catagory+",";
                System.out.println(index+1+"."+catagory);
                index++;
            }
            //System.out.println(index);
        } catch (Exception e) {
        }
    
        String bookcatagory[] = alldata.split(",");
        
        Scanner input=new Scanner(System.in);
        int selection = input.nextInt();
        try {
            PrintWriter addbook = new PrintWriter(new FileOutputStream((bookcatagory[selection-1]+".txt")),true);
            System.out.println("Please enter the name of the book:");
            Scanner book = new Scanner(System.in);
            String bookname = book.nextLine();
            addbook.println(bookname);
            addbook.close();
            addbooksuccessfully=true;
        } catch (Exception e) {
            System.out.println("Please key in valid catagory");
        }
        
         //System.out.println("Please enter a book to add in specific catagory");
//         String addbook = input.nextLine();
        
    
//    try {
//            PrintWriter addCatagory = new PrintWriter(new FileOutputStream("addbook.txt",true));
//            addCatagory.println(addbook);
//            PrintWriter addcatagoryfile = new PrintWriter(addbook+".txt");//???????????
//            addCatagory.close();
//            addbooksuccessfully = true;
//        } catch (Exception e) {
//        }
//return addbook();
//    }
//    
return addbooksuccessfully;
    }
    public boolean deleteBookinspecificCatagory(){
        boolean bookcatagoryDeleted = false;
        int index = 0;
        
        try {
            Scanner catagories =new Scanner(new FileInputStream("bookcatagories.txt"));
             System.out.println("Please select and type the catagory name to delect book: ");
            
            while (catagories.hasNext()) {
                String catagory = catagories.next();//just called a variable
                
                System.out.println(index+1+"."+catagory);
                index++;
            }
            //System.out.println(index);
        } catch (Exception e) {

        }        
    String arraydata[] = new String[index];
        try {
            Scanner catagories =new Scanner(new FileInputStream("bookcatagories.txt"));
            //get the array properly!
            String tempdata="";
            while (catagories.hasNext()) {
                tempdata +=catagories.next()+" ";
                
            }
            arraydata = tempdata.split(" ");
        } catch (Exception e) {
        }
        
                
        
        Scanner input = new Scanner(System.in);
        int userinputnumber = input.nextInt();
        try {
                PrintWriter modifiedCatagories = new PrintWriter(new FileOutputStream("bookcatagories.txt"));
                modifiedCatagories.print("");
                modifiedCatagories.close();
            } catch (Exception e) {
            }
        for (int i = 0; i < index; i++) {
            try {
                PrintWriter modifiedCatagories = new PrintWriter(new FileOutputStream("bookcatagories.txt",true));
                //not adding the catagory on the file
                if(userinputnumber-1!=i){
                    modifiedCatagories.println(arraydata[i]);//???????????????
                }
                modifiedCatagories.close();
            } catch (Exception e) {
            }
            
        }
       return bookcatagoryDeleted;
        
      
    }
    public boolean modifybookinspecificcatagory(){
  boolean bookcatagoryModified = false;
        int index = 0;
        String alldata = "";
        try {
            Scanner catagories =new Scanner(new FileInputStream("bookcatagories.txt"));
             System.out.println("Please select a catagorymodify: ");
            
            while (catagories.hasNext()) {
                String catagory = catagories.next();//just called a variable
                alldata+=catagory+",";
                System.out.println(index+1+"."+catagory);
                index++;
            }
            //System.out.println(index);
        } catch (Exception e) {
        }
        String catagoriesArray[] = alldata.split(",");
        Scanner input1 = new Scanner(System.in);
        int selection = input1.nextInt()-1;
        System.out.println("Please enter the new name:");
        Scanner input2 = new Scanner(System.in);
        String update = input2.nextLine();
        catagoriesArray[selection] = update;
        //cleaning the book catagory file
        
        for (int i = 0; i < catagoriesArray.length; i++) {
            System.out.println(catagoriesArray[i]);
        }
        
        try {
          PrintWriter modifiedCatagories = new PrintWriter(new FileOutputStream("bookcatagories.txt"));
          for (int i = 0; i < catagoriesArray.length; i++) {
              modifiedCatagories.println(catagoriesArray[i]);
          }
                
            
                modifiedCatagories.close();
               
                bookcatagoryModified = true;
                
      } catch (IOException e) {
          System.out.println("shit");
      }
      
        
        
  return bookcatagoryModified;
  }
    
    public static void clockin(String user){
//        System.out.println("hello");
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Format 1:   " + dateFormatter.format(now));
        
        try{
        PrintWriter data=new PrintWriter(new FileOutputStream("time.txt",true));
        
//        System.out.println(user);

        data.println(user + "," + dateFormatter.format(now));
        data.close();
        }catch(IOException e){
        System.out.println("Something went wrong");
    }
}
   
    public boolean clockout(String user){
        boolean clockout=false;
 
    
    try{
        PrintWriter data=new PrintWriter(new FileOutputStream("time.txt",true));
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Format 1:   " + dateFormatter.format(now));
        System.out.println(user);
        data.println(user+dateFormatter.format(now));
        data.close();
        clockout=false;
    }catch(Exception e){
        System.out.println("Clocked out");
        return clockout;
    }
    return clockout;    
 }      
     public void logout() throws FileNotFoundException{
         loginModule a = new loginModule();
         clockout(a.user);
//         a.login();
     }
}  
    
    









