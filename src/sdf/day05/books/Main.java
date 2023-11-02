package sdf.day05.books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow.Publisher;
import java.util.stream.Collectors;

public class Main{

   // public static final int COL_NAME = [1];
   // public static final int COL_PUBLISHER = [11];
    public static void main(String[]args){
        if (args.length<=0) {
            System.err.println("Missing book CSV");
            System.exit(1);
        }
    System.out.printf("Processing %s/n", args[0]);

    try ( FileReader fr = new FileReader(args[0])){
        BufferedReader br = new BufferedReader(fr);
        Map<String,List<Book>> classfied = br.lines() // br.readLine()
        .skip(1) // skipping the first line
        .map(row -> row.trim().split(","))
        .map(fields -> new Book(fields[1],fields[11]) ) //how to map a field to a book --> map to a string
        .collect(Collectors.groupingBy(book->book.getPublisher())); // grouping book to publishers, hence it is a list
        // only one terminate
        for( String publisher: classfied.keySet()){
            List<Book> books = classfied.get(publisher);// assigning a list to a key
            System.out.printf("%s\n", publisher);
            for (Book book:books){
                System.out.printf("\t%s\n", book.getTitle());
            }
        }

        
    } catch(IOException e){
        System.out.println("error");
    }
        
    } 

    }

