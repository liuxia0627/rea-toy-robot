package com.reagroup.toyrobot.model.args;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * This class provides API to reads the file provided as parameter or reads the input command from stdin of no file defined
 * The input is composed as a stream of strings, and each represents a command string which will be finally parsed into a command object
 */

public class InputArgs implements Function<String[], Stream<String>> {

    private final static Logger LOGGER = Logger.getLogger(InputArgs.class.getName());

    @Override
    public Stream<String> apply(String[] args) {
        switch (args.length){
            case 0:
                return stdinStream();
            case 1:
                return fileStream(args[0]);
            default:
                LOGGER.severe("Invalid arguments. Must be either blank or one filename.");
                return Stream.empty();
        }
    }

    /**
     * Reads lines of the files and return a stream of command Strings
     * @param filePath Path of file
     * @return Stream of Strings, each represents a command String which will be finally parsed to a command object
     */
    private Stream<String> fileStream(String filePath){
        try{
            return Files.lines(Paths.get(filePath));
        }catch(IOException ex){
            LOGGER.severe("There is IOException Occurred: " + ex.getMessage());
            return Stream.empty();
        }
    }

    /**
     * Reads input from stdin and return a stream of command Strings
     * @return a stream of command Strings which will finally be parsed to command objects
     */
    private Stream<String> stdinStream(){
        Scanner scanner = new Scanner(System.in);
        List<String> result = new ArrayList<>();

        System.out.println("Please enter the proper commands to start Toy Robot Simulator");
        System.out.println("Command line should be entered per line.");
        System.out.println("To complete the commands and execute the simulator, please type 'CTRL + D' in Unix or 'CTRL + Z' + 'Enter' in Windows.");
        System.out.println();

        while(scanner.hasNext()){
            result.add(scanner.nextLine());
        }
        return result.stream();
    }
}
