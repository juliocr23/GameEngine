package World;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class World {

    private static Object[][] object;
    private static ArrayList<String>  mapCode;    //The code parsed from the text file

    private static int tileWidth;                 //The width of the tile
    private static int tileHeight;                //The height of the tile

    private String  pathToTextFile;

    private double xOffset;                      //The x offset of the map
    private double yOffset;                      //The y offset of the map

    public World(String pathToTextFile){

        this.pathToTextFile    = pathToTextFile;


        //Get code parsed from the text file
        mapCode                = getContext(pathToTextFile);

        //Create the 2D array of objects
        int width              = getLongestLine(mapCode);
        int height             = mapCode.size();
        object                 = new Object[height][width];
    }

    public abstract void processInput();
    public abstract void update();
    public abstract void draw(Graphics g);


    public void addObject(char symbol, Object item){

        String lineOfCode;
        char   obj;

        for(int row = 0; row<object.length; row++){

            lineOfCode = mapCode.get(row);
            for(int col = 0; col<lineOfCode.length(); col++){

                obj = lineOfCode.charAt(col);
                if(obj == symbol)
                    object[row][col] = item;
            }
        }
    }


    /**
     * The getContext method read a file line by line ignoring #
     * and put it in an ArrayList
     * @param fileName A .txt file.
     * @return An ArrayList  containing the context read from the txt file
     */

    private ArrayList<String> getContext(String fileName){

        ArrayList<String> context = new ArrayList<>();
        File file = new File(fileName);

        try {
            Scanner read = new Scanner(file);
            String line;
            while (read.hasNext()){

                line = read.nextLine();
                if(!line.startsWith("#")){
                    context.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return context;
    }

    /**
     * The getLongestLine method find the longest line
     * in a ArrayList type string
     * @param context An ArrayList type string
     * @return The length of the longest line in the ArrayList
     */

    public int getLongestLine(ArrayList<String> context){
        int longest = 0;
        int temp;
        for(int i = 0; i<context.size(); i++){
            temp = context.get(i).length();

            if(longest < temp) longest = temp;
        }
        return longest;
    }
}
