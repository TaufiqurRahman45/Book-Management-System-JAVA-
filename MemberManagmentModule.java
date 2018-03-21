package librarymanagement;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AVRO
 */
public class MemberManagmentModule {
    String username;
   
    public MemberManagmentModule(String username) throws FileNotFoundException{
        this.username = username;
        if(isMemberActive(username))
            System.out.println("Active");
        else
            System.out.println("user inactive");
        System.out.println("\n1.Update Profile \n2.Update Search for a book based on different catagorise \n3.Borrow a book \n4.Renew Membership");    
    
    Scanner input=new Scanner(System.in);
    
        switch(input.nextInt()){
            case 1:
                System.out.println("Update Profile");
                UpdateProfile();
                break;
            case 2:
                System.out.println("Update Search for a book based on different categories");
                UpdateSearch();
                break;
            case 3:
                
                UpdateSearch();
                break;
            case 4:
                System.out.println("Renew Membership");
                renew(username);
                
                break;
            default:
                System.out.println("Invalid Keyword");
        }
        
    }
    public void UpdateProfile() throws FileNotFoundException{
        Scanner input=new Scanner(System.in);
        int counter = 0;
        System.out.println("Enter username: ");
        String username = input.nextLine();
        ArrayList<String[]> list = new ArrayList<>();
        try{
            Scanner input1 = new Scanner(new FileInputStream("member.txt"));
            while(input1.hasNextLine()){
                String[] line = input1.nextLine().split(",");
                list.add(line);
            }
            
            } catch (Exception e){
            System.out.println(""+e);
             }
        boolean userFound = false;
        int foundIndex = 0;
        for(int i = 0; i < list.size(); i++){
            if(username.equalsIgnoreCase(list.get(i)[0])){
                userFound = true;
                foundIndex = i;
            }
        }
        if(userFound){
            System.out.println("");
            String[] userDetails = new String[3];
            System.out.println("Enter fullname : ");
            userDetails[0] = input.nextLine();
            System.out.println("");
            System.out.println("Enter phone number : ");
            userDetails[1] = input.nextLine();
//            System.out.println(list.size());
            for (int i = 0; i < 1; i++) {
                list.get(foundIndex)[i+2] = userDetails[i];
            }
            
            PrintWriter registration=new PrintWriter(new FileOutputStream("member.txt"));
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).length; j++) {
                    if(j != list.get(i).length - 1)
                        registration.print(list.get(i)[j] + ",");
                    else
                        registration.print(list.get(i)[j]);
                }
                registration.println();
            }
            registration.close();

        }
        else{
            System.out.println("User not found");
        }
            
//                 try{
//                PrintWriter registration=new PrintWriter(new FileOutputStream("membr.txt",true));
//                for (int i = 0; i < counter; i++) {
//
//                if (member[i].contains(username)) {
//                    registration.println(member[i] + " " + fullname + " " + ID + " " + age + " " + phNumber + " ");
//                } else {
//                   registration.println(member[i]);
//                }
//            }
//                input.close();
//              registration.close();
//            
//        } catch (Exception e) {
//        }
    }

    public void UpdateSearch(){

        Scanner input=new Scanner(System.in);    
        System.out.println("Book Name:");
        String BookName=input.nextLine();
        System.out.println("Book Catagory");
        String BookCategory=input.nextLine();
        ArrayList<String[]> array = new ArrayList<>();
        try{
            Scanner input2=new Scanner(new FileInputStream(BookCategory + ".txt"));
            while(input2.hasNext()){
                array.add(input2.nextLine().split(","));
            }
        }catch(IOException e){
            System.out.println("");
        }
        boolean bookAvailable = false, bookInCollection = false;
        
        for (int i = 0; i < array.size(); i++) {
                if(array.get(i)[0].equalsIgnoreCase(BookName)){
                    if(array.get(i)[1].equalsIgnoreCase("in")){
                        bookInCollection = true;
                        bookAvailable = true;
//                        System.out.println("Available!");
                        break;
                    }
                    else if(array.get(i)[1].equalsIgnoreCase("out")){
                        bookInCollection = true;
                    }  
                }
        }
        if(bookAvailable){
            System.out.println("Available. Would you like to borrow " + BookName + "? (y/n)");
            String reply = input.nextLine();
            if(reply.equalsIgnoreCase("y"))
                Borrow(BookName,BookCategory);
        }
            
        else if(bookInCollection && !bookAvailable)
            System.out.println("That book is already borrowed by another user.");
        else if(!bookInCollection)
            System.out.println("Book doesnt exist in collection.");
        
    }

    public void Borrow(String bookName, String bookCategory){
        ArrayList<String[]> array = new ArrayList<>();
        int bookIndex = 0;
//        System.out.println(bookName);
        try{
            Scanner input1=new Scanner(new FileInputStream(bookCategory+".txt"));
            
            while(input1.hasNext()){
                array.add(input1.nextLine().split(","));
            }
            for (int i = 0; i < array.size(); i++) {
                if(bookName.equalsIgnoreCase(array.get(i)[0]))
                    bookIndex = i;
            }
            array.get(bookIndex)[1] = "out";
//            System.out.println(array.get(bookIndex)[1]);
//            for (int i = 0; i < array.size(); i++) {
//                for (int j = 0; j < array.get(i).length; j++) {
//                    System.out.println(array.get(i)[j]);
//                }
//                System.out.println("");
//            }
            input1.close();
            PrintWriter pw = new PrintWriter(new FileOutputStream(bookCategory + ".txt"));
            for (int i = 0; i < array.size(); i++) {
                for (int j = 0; j < array.get(i).length; j++) {
                    if(j == array.get(i).length - 1)
                        pw.print(array.get(i)[j]);
                    else
                        pw.print(array.get(i)[j] + ",");
                }
                pw.println();
            }
            pw.close();
//            
            
        }catch(IOException e){
        }

    }

        public boolean isMemberActive(String username) {
            ArrayList<String[]> array = new ArrayList<>();
            try{
                Scanner read = new Scanner(new FileInputStream("member.txt"));
                while(read.hasNext()){
                    array.add(read.nextLine().split(","));
                }
                read.close();
            }
            catch(IOException e){
            }
            String[] date = new String[3];
            for (int i = 0; i < array.size(); i++) {
                if(array.get(i)[0].equalsIgnoreCase(username))
                    date = array.get(i)[4].split("-");
            }
            return (Integer.parseInt(date[2]) < 2016);
                
            
        }

    private void renew(String username) {
        
    }
}



        
          
    

            

        
       
        
    

