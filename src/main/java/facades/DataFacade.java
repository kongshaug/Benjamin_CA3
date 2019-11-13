/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import DTO.PersonDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author benja
 */
public class DataFacade {
    private static ExecutorService executor = Executors.newFixedThreadPool(5);
    Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static DataFacade instance;
    
   private DataFacade(){}

    public static DataFacade getDataFacade () {
        if (instance == null) {
          
            instance = new DataFacade();
        }
        return instance;
    }
    
    
private String getSwappiData(int id) throws MalformedURLException, IOException{
    URL url = new URL("https://swapi.co/api/people/"+id);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Accept", "application/json;charset=UTF-8");
    con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
    Scanner scan = new Scanner(con.getInputStream());
    String jsonStr = null;
    if (scan.hasNext()) {
      jsonStr = scan.nextLine();
    }
    scan.close();
    return jsonStr;
  }


public List<PersonDTO> getData() throws InterruptedException, ExecutionException{
    final Random rand = new Random();
    List<PersonDTO> persons = new ArrayList<>();
  Queue<Future<PersonDTO>> queue = new ArrayBlockingQueue(5);
    
    for (int i = 1; i <= 5; i++) {
        Future<PersonDTO> future = executor.submit(() -> {
          
           
                int randomId = rand.nextInt(87);
                PersonDTO person = GSON.fromJson(getSwappiData(randomId+1), PersonDTO.class);
                return person;
        });
        
        queue.add(future);
    }
     while (!queue.isEmpty()) {
            Future<PersonDTO> person = queue.poll();
            if (person.isDone()) {
                persons.add(person.get());
            } else {
                queue.add(person);
            }
        }
    
return persons;
}





}
