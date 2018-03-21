/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author AVRO
 */
public class loginModule {
    public String user ="";
  
    public void login() throws FileNotFoundException{
        System.out.println("Please select a option for login: \n1.Member\n2.Librarian\n3.Head Librarian");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();
        switch(selection){
            case 1:
                memberLogin();
            break;
            case 2: if(librarianLogin()==true){
                bookManagementModule librarian = new bookManagementModule();//?????????
            }
            break;
            case 3: if(headLibrarianLogin()==true){
                System.out.println("logged in as head ");
            }
            break;
            default:login();
            break;
        }
    }
        public void memberLogin() throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the username:");
        String username = input.nextLine();
        System.out.println("Please enter the password:");
        String password = input.nextLine();
        ArrayList<String[]> list = new ArrayList<>();
        try {
            Scanner fileData = new Scanner(new FileInputStream("member.txt"));
            while (fileData.hasNextLine()) {
               String[] line = fileData.nextLine().split(",");
               list.add(line);
            }
            
        } catch (FileNotFoundException e) {
        }
            for (int i = 0; i < list.size(); i++) {
//                for (int j = 0; j < list.get(i).length; j++) {
//                    System.out.println(list.get(i)[j]);
//                }
//                System.out.println("");
                 if(username.equals(list.get(i)[0])&&password.equals(list.get(i)[1])){
                    MemberManagmentModule member = new MemberManagmentModule(username);
                    break;
                 }
                
            }
        
    }
        
        public boolean librarianLogin() throws FileNotFoundException{
        boolean userFound = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the username:");
        String username = input.nextLine();
        user = username;
        System.out.println("Please enter the password:");
        String password = input.nextLine();
        bookManagementModule test = new bookManagementModule();
        
        try {
            Scanner fileData = new Scanner(new FileInputStream("librarian.txt"));
            while (fileData.hasNextLine()) {
                
                
                if(username.equals(fileData.next())&&password.equals(fileData.next())){
                    userFound =true;
                    test.clockin(username);
                    break;
                 }else{
                    userFound=false;
                }
               
            }
            
        } catch (Exception e) {
        }
        
        
       return userFound;
    }
        
        public boolean headLibrarianLogin(){
        boolean userFound = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the username:");
        String username = input.nextLine();
        System.out.println("Please enter the password:");
        String password = input.nextLine();
        
        try {
            Scanner fileData = new Scanner(new FileInputStream("headlibrarian.txt"));
            while (fileData.hasNextLine()) {
                
                
                if(username.equals(fileData.next())&&password.equals(fileData.next())){
                    userFound =true;
                    break;
                 }else{
                    userFound=false;
                }
               
            }
            
        } catch (Exception e) {
        }
        
        
       return userFound;
    }
    
}
