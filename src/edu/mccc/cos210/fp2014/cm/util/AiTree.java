package edu.mccc.cos210.fp2014.cm.util;

/**
 * This tree includes evaluation objects useful for ranking trees and their leaves.
 * @param <T> the type of tree
 */
public class AiTree<T> extends Tree<T> {
	public AiTree(T t) {
		super(t);
	}
	private boolean evaluateFurther;
	private double whiteTreeScore;
	private double blackTreeScore;
}
