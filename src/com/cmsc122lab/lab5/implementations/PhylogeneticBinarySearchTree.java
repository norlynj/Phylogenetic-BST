package com.cmsc122lab.lab5.implementations;

import com.cmsc122lab.lab5.interfaces.PhylogeneticBSTADT;
import com.cmsc122lab.lab5.models.PhylogeneticBSTNode;
import com.cmsc122lab.lab5.models.Species;

public class PhylogeneticBinarySearchTree implements PhylogeneticBSTADT {

    private PhylogeneticBSTNode root;
    public PhylogeneticBinarySearchTree() {
        this.root = null;
    }

    public PhylogeneticBSTNode getRoot() {
        return root;
    }

    public void insert(Species species) {
        this.root = insertSpecies(this.root, species);
    }

    public void search(Species species) {
        PhylogeneticBSTNode retrieved = searchSpecies(this.root, species);
        if (retrieved != null){
            Species retrievedSpecies = retrieved.getSpecieData();
            displaySpeciesData(retrievedSpecies);
        } else{
            System.out.println("Species not found...");
        }
    }

    public void delete(Species species){
        PhylogeneticBSTNode retrieved = deleteSpecies(this.root, species);
        if (retrieved != null){
            Species retrievedSpecies = retrieved.getSpecieData();
            displaySpeciesData(retrievedSpecies);
        } else{
            System.out.println("Species not found...");
        }
    }

    public boolean compareSpecies(Species a, Species b){
        // Let's just perform simple compare based on lineage and name
        return a.getLineage() == b.getLineage() && a.getName().equalsIgnoreCase(b.getName());
    }

    public void displaySpeciesData(Species species){
        System.out.println(species.getName() + ": " + species.getLineage());
    }

    @Override // a recursive function to insert a new key in BST
    public PhylogeneticBSTNode insertSpecies(PhylogeneticBSTNode root, Species species) {
        //if tree is empty, return a new node
        //if tree is empty, return a new node
        if(root == null) {
            root = new PhylogeneticBSTNode(species);
            return root;
        }

        //otherwise, recur down the tree
        if(species.getLineage() < root.getSpecieData().getLineage()){
            root.setLeftSpecieData(insertSpecies(root.getLeftSpecieData(), species));
        }
        else if (species.getLineage() > root.getSpecieData().getLineage()){
            root.setRightSpecieData(insertSpecies(root.getRightSpecieData(), species));
        }

        //return the node pointer
        return root;
    }

    @Override
    public PhylogeneticBSTNode deleteSpecies(PhylogeneticBSTNode root, Species species) {
        if(root == null) //if empty
            return null;

        PhylogeneticBSTNode current = root;
        PhylogeneticBSTNode parent = root;
        PhylogeneticBSTNode rootNode = root;
        boolean isLeftChild = true;

        //find the specie first
        while(!compareSpecies(current.getSpecieData(), species)){
            parent = current;
            if (species.getLineage() < current.getSpecieData().getLineage()){ //go left
                isLeftChild = true;
                current = current.getLeftSpecieData();
            }
            else{ //go right
                isLeftChild = false;
                current = current.getRightSpecieData();
            }
            if(current == null)
                return null;
        }
        //after we find node, verify that it has no children
        if(current.getLeftSpecieData() == null && current.getRightSpecieData() == null){
            if (current == root) //if root
                root = null;    //tree is empty
            else if(isLeftChild)
                parent.setLeftSpecieData(null); //disconnect
            else                                //from parent
                parent.setRightSpecieData(null);
        }
        else if(current.getRightSpecieData() == null) {
            //if no right child, replace with left subtree
            if (current == root)
                root = current.getLeftSpecieData();
            else if (isLeftChild)
                parent.setLeftSpecieData(current.getLeftSpecieData());
            else
                parent.setRightSpecieData(current.getLeftSpecieData());
        }
        else if (current.getLeftSpecieData() == null){
            //if no left child, replace with right subtree
            if (current == root)
                root = current.getRightSpecieData();
            else if (isLeftChild)
                parent.setLeftSpecieData(current.getRightSpecieData());
            else
                parent.setRightSpecieData(current.getRightSpecieData());
        }
        else { //two children
            PhylogeneticBSTNode successor = getSuccessor(current);
            //connect parent of current to successor instead
            if (current == root) {//set root to successor
                root.getSpecieData().setName(successor.getSpecieData().getName());
                root.getSpecieData().setLineage(successor.getSpecieData().getLineage());
            }
            else if (isLeftChild)
                parent.setLeftSpecieData(successor);
            else
                parent.setRightSpecieData(successor);
            //connect successor to current's left child
            successor.setLeftSpecieData(current.getLeftSpecieData());

            ///without this, it will return and print
            // the new root node (if current=root) instead of the specie deleted
            return new PhylogeneticBSTNode(species);
        }
        return current;
    }

    /*
    * returns node with next-highest value after deleteSpecie
    * goes to right child, then right child's descendants
    * */
    private PhylogeneticBSTNode getSuccessor(PhylogeneticBSTNode deleteSpecie){
        PhylogeneticBSTNode parent = deleteSpecie;
        PhylogeneticBSTNode successor = deleteSpecie;
        PhylogeneticBSTNode current = deleteSpecie.getRightSpecieData();

        while (current != null){
            parent = successor;
            successor = current;
            current = current.getLeftSpecieData();
        }

        if(successor != deleteSpecie.getRightSpecieData()){
            parent.setLeftSpecieData(successor.getRightSpecieData());
            successor.setRightSpecieData(deleteSpecie.getRightSpecieData());
        }

        return successor;
    }

    @Override
    public PhylogeneticBSTNode searchSpecies(PhylogeneticBSTNode root, Species species) {

        if(root == null)
            return null;

        PhylogeneticBSTNode current = root; //start at root
        while(!compareSpecies(current.getSpecieData(), species)){
            if(species.getLineage() < current.getSpecieData().getLineage())
                current = current.getLeftSpecieData();
            else
                current = current.getRightSpecieData();
            if (current == null)
                return null;
        }
        return current;
    }

    @Override
    public void traversePhylogeneticBSTInorder(PhylogeneticBSTNode root) {
        if(root != null){
            traversePhylogeneticBSTInorder(root.getLeftSpecieData());
            displaySpeciesData(root.getSpecieData());
            traversePhylogeneticBSTInorder(root.getRightSpecieData());
        }
    }

    @Override
    public void traversePhylogeneticBSTPreorder(PhylogeneticBSTNode root) {
        if (root != null){
            displaySpeciesData(root.getSpecieData());
            traversePhylogeneticBSTPreorder(root.getLeftSpecieData());
            traversePhylogeneticBSTPreorder(root.getRightSpecieData());
        }

    }

    @Override
    public void traversePhylogeneticBSTPostorder(PhylogeneticBSTNode root) {
        if(root != null){
            traversePhylogeneticBSTPostorder(root.getLeftSpecieData());
            traversePhylogeneticBSTPostorder(root.getRightSpecieData());
            displaySpeciesData(root.getSpecieData());
        }

    }
}
