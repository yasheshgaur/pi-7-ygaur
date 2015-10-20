package rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import type.Passage;
import type.Question;

public class CompositeRanker extends AbstractRanker implements IAggregator {

  /** Individual rankers */
  private List<IRanker> rankers;

  public CompositeRanker() {
    rankers = new ArrayList<IRanker>();
  }

  public void addRanker(IRanker ranker) {
    rankers.add(ranker);
  }

  /**
   * Returns a score of the given passage associated with the given question.
   * 
   * @param question
   * @param passage
   * @return a score of the passage
   */
  @Override
  public Double score(Question question, Passage passage) {
    List<Double> scores = new ArrayList<Double>();
    for (IRanker r : rankers) {
      scores.add(r.score(question, passage));
    }
    return aggregateScores(scores);
  }

  /** 
   * Returns aggregated scores
   * 
   * @param List<Double> scores
   * @return aggregated score
   * */
  @Override
  public Double aggregateScores(List<Double> scores) {
    // TODO Complete the implementation of this method.

    // In PI7, compute the aggregated score by taking a weighted average of scores. Note that you
    // can figure out which score comes from which ranker because the index of List object 'scores'
    // corresponds to the index of List object 'rankers'.

    double sum = 0.0;
    
    for (double d : scores) {
      
      sum += d;
    }
    
    return (0.5*scores.get(0) + 0.5*scores.get(1));
  }

  
  
  /** 
   * returns a ranked list of passages
   * 
   * @param Question question
   * @param List<Passage> passages
   * @return ranked list of passages
   * */
  @Override
  public List<Passage> rank(final Question question, List<Passage> passages) {
    
    
    Collections.sort(passages, new Comparator<Passage>() {
      public int compare(final Passage p1, final Passage p2) {
        if (score(question, p1) < score(question, p2)) 
          return -1;
        else 
          return 1;
      }
    });
    
    return passages;
  }
}
