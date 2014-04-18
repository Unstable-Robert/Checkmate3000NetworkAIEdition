package edu.mccc.cos210.fp2014.cm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple tree structure. Each root has several leaves, which are in turn trees as well.
 * This is our internal representation of possible boards from a given point in time.
 */
public class Tree<T> {
	private T root;
	private ArrayList<Tree<T>> leaves;
	public Tree(T t){
	}
	/**
	 * Returns the root of the tree, the element from which all other possibilities orrur.
	 * @return the element which makes up our root.
	 */
	public T getRoot() {
		return null;
	}
	/**
	 * This returns a list of all of the leaves, possibilities stemming from our original root.
	 * @return a list of all leaves (and their trees as well)
	 */
	public List<Tree<T>> getLeaves() {
		return null;
	}
	/**
	 * Returns true if the root has leaves, false otherwise.
	 * @return whether or not the tree has leaves
	 */
	public boolean hasLeaves() {
		return false;
	}
	/**
	 * Adds a leaf to the tree
	 * @param t the value to be added
	 */
	public void addLeaf(T t) {
	}
	/**
	 * Trims the tree to the values passed into as a paramater
	 * This removes all branches except the root t and any subsequent children if it has any.
	 * @param t the value to trim the tree to.
	 */
	public void trimTree(T t) {
	}
}
