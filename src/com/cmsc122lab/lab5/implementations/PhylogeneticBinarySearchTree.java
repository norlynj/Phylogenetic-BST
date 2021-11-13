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

    @Override
    public PhylogeneticBSTNode insertSpecies(PhylogeneticBSTNode root, Species species) {
        // implement tree insert here and modify return value
        if(root == null) {
            root = new PhylogeneticBSTNode(species);
            return root;
        }
        else {
            PhylogeneticBSTNode current = root;


            while(true){
                if (root.getSpecieData() == current.getSpecieData()){
                    return null; //no duplicates
                }
                else if(root.getSpecieData().getLineage() < current.getSpecieData().getLineage() && current.getLeftSpecieData() == null){
                    current.getLeftSpecieData().setLeftSpecieData(new PhylogeneticBSTNode(species)); //value inserted on the left
                    break;
                }
                else if (root.getSpecieData().getLineage() < current.getSpecieData().getLineage()){
                    current = current.getLeftSpecieData();
                }
                else if (root.getSpecieData().getLineage() > current.getSpecieData().getLineage() && current.getRightSpecieData() == null){
                    current.getRightSpecieData().setRightSpecieData(new PhylogeneticBSTNode(species));
                }
                else{
                    current = current.getRightSpecieData();
                }

            }
        }
        return root;
    }

    @Override
    public PhylogeneticBSTNode deleteSpecies(PhylogeneticBSTNode root, Species species) {
        //implement tree delete here and modify return value

        return root;
    }

    @Override
    public PhylogeneticBSTNode searchSpecies(PhylogeneticBSTNode root, Species species) {
        // implement tree search here and modify return value

        return null;
    }

    @Override
    public void traversePhylogeneticBSTInorder(PhylogeneticBSTNode root) {
        // implement inorder traversal here
        // displays node recursively
        if(root != null){
            traversePhylogeneticBSTInorder(root.getLeftSpecieData());
            displaySpeciesData(root.getSpecieData());
            traversePhylogeneticBSTInorder(root.getRightSpecieData());
        }
    }

    @Override
    public void traversePhylogeneticBSTPreorder(PhylogeneticBSTNode root) {
        // implement preorder traversal here
        // displays node recursively

    }

    @Override
    public void traversePhylogeneticBSTPostorder(PhylogeneticBSTNode root) {
        // implement postorder traversal here
        // displays node recursively
    }
}
