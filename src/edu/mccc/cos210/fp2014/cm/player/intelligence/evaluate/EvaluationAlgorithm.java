package edu.mccc.cos210.fp2014.cm.player.intelligence.evaluate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import edu.mccc.cos210.fp2014.cm.game.Board;
import edu.mccc.cos210.fp2014.cm.util.Tree;

/**
 * A prototype evaluation algorithm. It's used by the intelligence prototypes.
 */
public abstract class EvaluationAlgorithm implements Runnable {
	protected ExecutorService threadPool;
	protected boolean isWhite;
    protected boolean isFinished;
    protected Tree<Board> tree;
    protected int depth;
    protected EvaluationAlgorithm(Board b, int d, boolean iW){
    	this.tree = new Tree<Board>(b);
    	this.depth = d;
		this.isWhite = iW;
	}	
	public boolean isFinished() {
		return this.isFinished;
	}
	public void setRoot(Tree<Board> t){
    	this.tree = t;
    }
	public double evaluate(Tree<Board> tree, boolean maxPlayer) {
		Board b = tree.getRoot();
		if (tree.getLeaves().isEmpty()){
			this.isFinished = true;
			return getBoardValue(b);
		}else {
			double bestValue;
			if (maxPlayer){
				bestValue = Integer.MIN_VALUE;
				List<Tree<Board>> t = tree.getLeaves();
				for(int i = 0; i < t.size(); i++){
					double value = evaluate(t.get(i), false);
					bestValue = bestValue > value ? bestValue : value;
				}
			} else {
				bestValue = Integer.MAX_VALUE;
				List<Tree<Board>> t = tree.getLeaves();
				for(int i = 0; i < t.size(); i++){
					double value = evaluate(t.get(i), true);
					bestValue = bestValue < value ? bestValue : value; 
				}
			}
			tree.setScore(bestValue);
			this.isFinished = true;
			return bestValue;
		}
	}
	public Board getBest() {
		ArrayList<Board> best = new ArrayList<Board>();
		double bestScore = Integer.MIN_VALUE;
		for (Tree<Board> b : this.tree.getLeaves()){
			if (b.getScore() > bestScore){
				best.clear();
				bestScore = b.getScore();
				best.add(b.getRoot());
			} else if (b.getScore() == bestScore) {
				best.add(b.getRoot());
			}
		}
		return best.get(new Random().nextInt(best.size()));
	}	
	public abstract double getBoardValue(Board b);
	public Tree<Board> getSeveralBest(int i) {
		Tree<Board> intermediate = new Tree<Board>(this.tree.getRoot());
		for(Tree<Board> t : this.tree.getLeaves()){
			intermediate.addLeaf(t.getRoot());
			if (intermediate.getLeaves().size() > i){
				Tree<Board> lowest = null;
				int lowestInt = Integer.MAX_VALUE;
				for (Tree<Board> remove : intermediate.getLeaves()){
					if (remove.getScore() < lowestInt){
						lowest = remove;
					}
				}
				intermediate.removeLeaf(lowest);
			}
		}
		return intermediate;
	}
}
