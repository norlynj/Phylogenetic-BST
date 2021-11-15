package com.cmsc122lab.lab5.models;
public class PhylogeneticBSTNode {
    private final Species specieData;
    private PhylogeneticBSTNode rightSpecieData;
    private PhylogeneticBSTNode leftSpecieData;

    public PhylogeneticBSTNode(Species specieData) {
        this.specieData = specieData;
        this.rightSpecieData = null;
        this.leftSpecieData = null;
    }

    public Species getSpecieData() {
        return specieData;
    }

    public PhylogeneticBSTNode getRightSpecieData() {
        return rightSpecieData;
    }

    public void setRightSpecieData(PhylogeneticBSTNode rightSpecieData) {
        this.rightSpecieData = rightSpecieData;
    }

    public PhylogeneticBSTNode getLeftSpecieData() {
        return leftSpecieData;
    }

    public void setLeftSpecieData(PhylogeneticBSTNode leftSpecieData) {
        this.leftSpecieData = leftSpecieData;
    }
}
