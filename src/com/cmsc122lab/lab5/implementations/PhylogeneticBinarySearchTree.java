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

    @Override //a recursive function to delete an existing species in BST
    public PhylogeneticBSTNode deleteSpecies(PhylogeneticBSTNode root, Species species) {
        //base case: if the tree is empty
        if(root == null)
            return root;

        //otherwise, recur down the tree
        if (species.getLineage() < root.getSpecieData().getLineage())
            root.setLeftSpecieData(deleteSpecies(root.getLeftSpecieData(), species));
        else if (species.getLineage() > root.getSpecieData().getLineage())
            root.setRightSpecieData(deleteSpecies(root.getRightSpecieData(), species));

        //if species is same as root's key then this is the node to be deleted
        else{
            //node with only one child or no child
            if (root.getLeftSpecieData() == null)
                return root.getRightSpecieData();
            else if (root.getRightSpecieData() == null)
                return root.getLeftSpecieData();

            //node with two children: get the inorder successor (smalles in the right subtree)
            root.getSpecieData().setLineage(minValue(root.getRightSpecieData()));
            root.getRightSpecieData().setRightSpecieData(deleteSpecies(root.getRightSpecieData(), root.getSpecieData()));
        }

        return root;
    }

    private int minValue(PhylogeneticBSTNode root){
        int min = root.getSpecieData().getLineage();
        while (root.getLeftSpecieData() != null){
            min = root.getLeftSpecieData().getSpecieData().getLineage();
            root = root.getLeftSpecieData();
        }
        return min;
    }

    @Override
    public PhylogeneticBSTNode searchSpecies(PhylogeneticBSTNode root, Species species) {
        // implement tree search here and modify return value
        //Base cases: root is null or species is present at root
        if (root == null || root.getSpecieData() == species)
            return root;

        //if species is greater than root's species
        if (root.getSpecieData().getLineage() < species.getLineage())
            return searchSpecies(root.getRightSpecieData(), species);
        //key is smaller than root's key
        return searchSpecies(root.getLeftSpecieData(), species);
    }

    @Override
    public void traversePhylogeneticBSTInorder(PhylogeneticBSTNode root) {
        /* Algorithm
        * 1. Traverse the left subtree, i.e., call Inorder(left-subtree)
        * 2. Visit the root.
        * 3. Traverse the right subtree, i.e., call Inorder(right-subtree)
        */
        if(root != null){
            traversePhylogeneticBSTInorder(root.getLeftSpecieData());
            displaySpeciesData(root.getSpecieData());
            traversePhylogeneticBSTInorder(root.getRightSpecieData());
        }
    }

    @Override
    public void traversePhylogeneticBSTPreorder(PhylogeneticBSTNode root) {
        /* Algorithm
         * 1. Visit the root.
         * 2.Traverse the left subtree, i.e., call Preorder(left-subtree)
         * 3. Traverse the right subtree, i.e., call Preorder(right-subtree)
         */
        if (root != null){
            displaySpeciesData(root.getSpecieData());
            traversePhylogeneticBSTPreorder(root.getLeftSpecieData());
            traversePhylogeneticBSTPreorder(root.getRightSpecieData());
        }

    }

    @Override
    public void traversePhylogeneticBSTPostorder(PhylogeneticBSTNode root) {
        if(root != null){
            /* Algorithm
             * 1. Traverse the left subtree, i.e., call Postorder(left-subtree)
             * 2. Traverse the right subtree, i.e., call Postorder(right-subtree)
             * 3. Visit the root.
             */

            traversePhylogeneticBSTPostorder(root.getLeftSpecieData());
            traversePhylogeneticBSTPostorder(root.getRightSpecieData());
            displaySpeciesData(root.getSpecieData());
        }

    }
}
