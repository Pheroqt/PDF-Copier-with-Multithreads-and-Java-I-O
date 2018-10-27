
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static ArrayList<Integer> inputs = new ArrayList<Integer>();
    
    public static void pdfRead() {
        try {
            FileInputStream input = new FileInputStream("File.pdf");
            int read;
            while ((read = input.read()) != -1) {
                inputs.add(read);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void pdfWrite(String pdfName) {
        try {
            FileOutputStream output = new FileOutputStream(pdfName);
            
            for (int temp : inputs) {
                output.write(temp);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public static void main(String[] args) {
        pdfRead();
        Thread thread1 = new Thread (new Runnable() {
            @Override
            public void run() {
                pdfWrite("File1.pdf");
            }
        });
        Thread thread2 = new Thread (new Runnable() {
            @Override
            public void run() {
                pdfWrite("File2.pdf");
            }
        });
        Thread thread3 = new Thread (new Runnable() {
            @Override
            public void run() {
                pdfWrite("File3.pdf");
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
