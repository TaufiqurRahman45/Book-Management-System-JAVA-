package librarymanagement;
import java.io.BufferedReader;
import java.io.FileReader;
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

 public class HeadLibrarians{
   public static void viewlibrarian(){
       try {
           BufferedReader op = new BufferedReader(new FileReader("librarian.txt"));
           System.out.println("Librarian's details");
           String line=null;
           while((line=op.readLine())!=null){
               System.out.println(line);
            }
       }catch(Exception e){
            System.out.println("Error reading file.");
        }
   }

public static void viewmembers(){
  try{
     BufferedReader op=new BufferedReader(new FileReader("member.txt"));
     System.out.println("Member's detalis");
     String line=null;
     while((line=op.readLine())!=null){
         System.out.println(line);
     }
     }catch(Exception e){
         System.out.println("Error reading file");
 }
}
}


 
       
 
     
       
     
         
     

     
             
                     
                     


           
        
                 
        
            
        
                
            
        
          
          
        
               
            
        
        
    
            
    

