package edu.mccc.cos210.fp2014.cm.util;

import java.util.ArrayList;
import java.util.List;

import edu.mccc.cos210.fp2014.cm.game.Board;

/**
 * A simple tree structure. Each root has several leaves, which are in turn trees as well.
 * This is our internal representation of possible boards from a given point in time.
 */
public class Tree<T> {
	private TreeNode<T> root;
	private ArrayList<Tree<T>> leaves;
	private int score;
	public Tree(T t){ 
		this.root = new TreeNode<T>(t);
		this.leaves = new ArrayList<Tree<T>>();
	}
	public Tree(T t, TreeNode<T> p) {
		this.root = new TreeNode<T>(t, p);
		this.leaves = new ArrayList<Tree<T>>();
	}
	/**
	 * Returns the root of the tree, the element from which all other possibilities orrur.
	 * @return the element which makes up our root.
	 */
	public T getRoot() {
		return this.root.getValue();
	}
	public void setScore(int s){
		this.score = s;
	}
	public int getScore(){
		return this.score;
	}
	/**
	 * This returns a list of all of the leaves, possibilities stemming from our original root.
	 * @return a list of all leaves (and their trees as well)
	 */
	public List<Tree<T>> getLeaves() {
		return this.leaves;
	}
	/**
	 * Returns true if the root has leaves, false otherwise.
	 * @return whether or not the tree has leaves
	 */
	public boolean hasLeaves() {
		return this.leaves.size() > 0 ? true : false;
	}
	/**
	 * Adds a leaf to the tree
	 * @param t the value to be added
	 */
	public void addLeaf(T t) {
		this.leaves.add(new Tree<T>(t));
	}
	public void removeLeaf(Tree<T> t) {
		this.leaves.remove(t);
	}
	/**
	 * Trims the tree to the values passed into as a paramater
	 * This removes all branches except the root t and any subsequent children if it has any.
	 * @param t the value to trim the tree to.
	 */
	public void trimTree(T t) {
	}
	protected class TreeNode<T> {
		private T value;
		private TreeNode<T> parent;
		public TreeNode(T t){
			this.value = t;
		}
		public TreeNode(T t, TreeNode<T> p) {
			this.value = t;
			this.parent = p;
		}
		public T getValue() {
			return this.value;
		}
		public TreeNode<T> getParent() {
			return parent;
		}
	}
	public Tree<T> getLeaf(Board newBoard) {
		for (Tree<T> leaf : this.getLeaves()){
			if (newBoard.equals(leaf.getRoot())){
				return leaf;
			}
		}
		return null;
	}
}
