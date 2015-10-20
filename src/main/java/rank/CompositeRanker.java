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

  @Override
  public Double aggregateScores(List<Double> scores) {
    // TODO Complete the implementation of this method.

    // In PI7, compute the aggregated score by taking a weighted average of scores. Note that you
    // can figure out which score comes from which ranker because the index of List object 'scores'
    // corresponds to the index of List object 'rankers'.

    double sum = 0.0;
    //System.out.println("in aggreagte score");
    for (double d : scores) {
      //System.out.println(d);
      sum += d;
    }
    //System.out.println(sum/scores.size());
    //return sum/scores.size();
    //System.out.println(scores.get(0) + 0.001*scores.get(1));
    System.out.println(scores.size());
    return (0.0000000000001*scores.get(0) + scores.get(1));
  }

  @Override
  public List<Passage> rank(final Question question, List<Passage> passages) {
    
    System.out.println("\nprint before sort\n");
    for (int i = 0; i < passages.size(); i++) {
      System.out.println(passages.get(i).getText());
    }
    Collections.sort(passages, new Comparator<Passage>() {
      public int compare(final Passage p1, final Passage p2) {
        if (score(question, p1) < score(question, p2)) 
          return -1;
        else 
          return 1;
      }
    });
    System.out.println("\nprint after sort\n");
    for (int i = 0; i < passages.size(); i++) {
      System.out.println(passages.get(i).getText());
    }
    return passages;
  }
}
