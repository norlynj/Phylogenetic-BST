package com.cmsc122lab.lab5;

import com.cmsc122lab.lab5.implementations.PhylogeneticBinarySearchTree;
import com.cmsc122lab.lab5.models.Species;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreeOfLife {
    public static PhylogeneticBinarySearchTree bst = new PhylogeneticBinarySearchTree();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        do{
            String input = sc.nextLine();
            if (input.isEmpty() || input.equalsIgnoreCase("quit")) {
                break;
            } else {
                processUserInput(input);
            }
        } while(true);
    }

    public static void processUserInput(String userInput) throws FileNotFoundException {
        try {
            if(hasTestFile(userInput)){
                System.out.println("Opening file: " + userInput);
                preprocessInputs(new File(userInput));
            } else{
                String[] tokens = userInput.split(" ");
                String command = tokens[0] != null ? tokens[0] : "" ;

                if(tokens.length == 1){
                    System.out.println("Processing user command: " + command);
                    switch (command.toLowerCase()){
                        case "inorder":
                            bst.traversePhylogeneticBSTInorder(bst.getRoot());
                            break;
                        case "preorder":
                            bst.traversePhylogeneticBSTPreorder(bst.getRoot());
                            break;
                        case "postorder":
                            bst.traversePhylogeneticBSTPostorder(bst.getRoot());
                            break;
                        default:
                            System.out.println("Checking for file existence...");
                            preprocessInputs(new File(command.replace("\"","")));
                    }
                } else {
                    String inputArgumentA = tokens[1];
                    String inputArgumentB = tokens[2];
                    System.out.println("Performing " + command + " to BST...");
                    System.out.println("Species Name: " + inputArgumentA + "\nSpecies Lineage: "+inputArgumentB);
                    Species species = new Species(inputArgumentA, Integer.parseInt(inputArgumentB));

                    switch (command.toLowerCase()){
                        case "insert":
                            bst.insert(species);
                            break;
                        case "search":
                            bst.search(species);
                            break;
                        case "delete":
                            bst.delete(species);
                            break;
                    }
                }
                System.out.println();
            }
        } catch (Exception e){
            System.out.println("Invalid input...");
            e.printStackTrace();
        }
    }

    public static boolean hasTestFile(String path){
        return new File(path).exists();
    }

    public static void preprocessInputs(File testFile) throws FileNotFoundException {
        Scanner sc = new Scanner(testFile);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            processUserInput(input);
        }
        sc.close();
    }
}
