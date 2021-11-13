package com.cmsc122lab.lab5.interfaces;

import com.cmsc122lab.lab5.models.PhylogeneticBSTNode;
import com.cmsc122lab.lab5.models.Species;

public interface PhylogeneticBSTADT {
    PhylogeneticBSTNode insertSpecies(PhylogeneticBSTNode root, Species species);
    PhylogeneticBSTNode deleteSpecies(PhylogeneticBSTNode root, Species species);
    PhylogeneticBSTNode searchSpecies(PhylogeneticBSTNode root, Species species);
    void traversePhylogeneticBSTInorder(PhylogeneticBSTNode root);
    void traversePhylogeneticBSTPreorder(PhylogeneticBSTNode root);
    void traversePhylogeneticBSTPostorder(PhylogeneticBSTNode root);
}
