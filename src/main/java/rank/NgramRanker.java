package rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import type.Passage;
import type.Question;

public class NgramRanker extends AbstractRanker {

  // Ngrams with degrees in [1, MaxDegree] will be extracted
  
  private int MaxDegree = 3;
  
  /* Builder class */
  public static class Builder {
    private int MaxDegree;
    
    public Builder(int MaxDegree) {
      this.MaxDegree = MaxDegree;
    }
    
    public NgramRanker build() {
      return new NgramRanker(this);
    }
  }
  
  // private constructor which uses the builder class
  private NgramRanker(Builder builder) {
    MaxDegree = builder.MaxDegree;
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
    // TODO Complete the implementation of this method.
    
    // extract the ngrams from the Question
    
    ArrayList<String> QuestionNgrams = GetNgrams(question.getSentence(), this.MaxDegree);
    
    // extract the ngrams from the Passage
    
    ArrayList<String> PassageNgrams = GetNgrams(passage.getText(), this.MaxDegree);
    
    // calculate the overlap
    
    PassageNgrams.retainAll(QuestionNgrams);
    
    return (double)PassageNgrams.size()/QuestionNgrams.size();
  }
  
  
  /** 
   * ranks the List of passages in descending order according to the scores for
   * ngram overlap.
   * 
   * @param Question
   * @param associated passages
   * @return sorted list of passages ranked according to ngram overlap scores
   */
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
  
  
  /** 
   * Returns an ArrayList of ngram strings with the given text and MaxNgramDegree
   * The Ngrams extracted are of degrees lesser than or equal to the MaxNgramDegree
   * 
   * @param Text
   * @param MaxNgramDegree
   * @return an ArrayList<String>
   */
  
  private ArrayList<String> GetNgrams(String Text, int MaxDegree) {
    ArrayList<String> Ngrams = new ArrayList<String>();
    for (int i = 2; i <= MaxDegree; i++) {
      for (String ngram : ngrams(i, Text)) {
        Ngrams.add(ngram);
      }
    }
    return Ngrams; 
  }
  
  
  /** 
   * Returns a List of ngrams with a given degree
   * 
   *  @param n
   *  @param str
   *  @return List<String>
   */
  
  private static List<String> ngrams(int n, String str) {
    List<String> ngrams = new ArrayList<String>();
    String[] words = str.split(" ");
    for (int i = 0; i < words.length - n + 1; i++)
        ngrams.add(concat(words, i, i+n));
    return ngrams;
  }
  
  
  /**
   * Concatenates a string of words in an array, from being to end
   * 
   * @param: String[] words
   * @param: start
   * @param: end
   * @return string(ngram)
   */
  
  public static String concat(String[] words, int start, int end) {
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < end; i++)
        sb.append((i > start ? " " : "") + words[i]);
    return sb.toString();
  }

}
