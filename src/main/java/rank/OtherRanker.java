package rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import type.Passage;
import type.Question;


public class OtherRanker extends AbstractRanker {

  /** This ranker is based on the tf-idf approach 
   * */
  
  // private variables.
  String[] QueryWords;
  double[] IDFVec;
  TfIdf tfidf = new TfIdf();
  
  /** 
   * This function estimates the IDF values by accepting a question and a
   * list of associated passages. Populates the private variables.
   * 
   * @param question
   * @param passages
   * */
  
  public void train(Question question, List<Passage> passages) {
    
    this.QueryWords = question.getSentence().split(" ");
    
    // calculate idf for query words
    // populate allterms from the passages for idf calculation
    
    ArrayList<String[]> allTerms = new ArrayList<String[]>();
    for (Passage p: passages) {
      String[] Terms = p.getText().split(" ");
      allTerms.add(Terms);
    }
    
    this.IDFVec = new double[QueryWords.length];
    
    // populate the idf values
    for (int i = 0; i < IDFVec.length; i++) {
      this.IDFVec[i] = this.tfidf.idfCalculator(allTerms, QueryWords[i]);
    }
    
    
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
    
    // get word vectors for question and passage
    // the vector for the question is the IDF vector itself
    // the vector for passage can be obtained from GetWordVec function
    
    String[] QueryWords = question.getSentence().split(" ");
    double[] PassageWordVec = GetWordVec(passage, QueryWords, this.tfidf, this.IDFVec);
    
    // now calculate the cosine similarity between word vecs of question
    // and the passage
    
    return CalculateCosineSimilarity(this.IDFVec, PassageWordVec);
  }
  
  /**
   * Returns a sorted list of passages in descending order according to their
   * scores. This method assumes that train method has been called before and that
   * the private variables have this been properly populated.
   * 
   * @param Question
   * @param passages
   * @return sorted list of passages
   * */
  @Override
  public List<Passage> rank(final Question question, List<Passage> passages) {
    
    
    // rank the passages based on cosine similarity of the word vectors between
    // Question and the Passage. Word vector of question defaults to IDFVec.
    // Word vector of the passage can be obtained from getWordVec
    
    
    
    
    
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
   * Returns the cosine similarity between two vectors.
   * 
   * @param QuestionWordVec
   * @param PassageWordVec
   * @return score
   * */
  double CalculateCosineSimilarity(double[] QuestionWordVec, double[] PassageWordVec) {
    double score = 0.0;
    double DotProduct = 0.0;
    double MagQuestion = 0.0;
    double MagPassage = 0.0;
    
    for (int i = 0; i < QuestionWordVec.length; i++) {
      DotProduct += QuestionWordVec[i]*PassageWordVec[i];
    }
    
    for (double d : QuestionWordVec) {
      MagQuestion += d*d;
    }
    MagQuestion = Math.sqrt(MagQuestion);
    
    for (double d : PassageWordVec) {
      MagPassage += d*d;
    }
    MagPassage = Math.sqrt(MagPassage);
    
    score = DotProduct/(MagQuestion*MagPassage);
    
    return score;
  }
  
  /** 
   * Returns a word vector which is basically tfidf values for every query term
   * in the Question for that passage
   * 
   * @param Passage
   * @param QueryWords
   * @param tfidf
   * @param IDFVec
   * @return WordVec
   * */
  private double[] GetWordVec(Passage p, String[] QueryWords, TfIdf tfidf, double[] IDFVec) {
    double[] WordVec = new double[QueryWords.length];
    
    // first add the frequency
    for (int i = 0; i < QueryWords.length; i++) {
      WordVec[i] = tfidf.tfCalculator(p.getText().split(" "), QueryWords[i]);
    }
    
    // now normalize these frequency values
    // find the max
    double max = MaxFloat(WordVec);
    
    // divide each by max
    for (int i = 0; i < WordVec.length; i++) {
      if (max != 0.0) {
        WordVec[i] = WordVec[i]/max;
      }
      
    }
    
    // now multiple by the idf vector to get the tfidf scores
    for (int i = 0; i < WordVec.length; i++) {
      WordVec[i] = WordVec[i]*IDFVec[i];
    }
    
    return WordVec;
  }
  
  /** 
   * returns maximum out a double array
   * */
  private double MaxFloat(double[] array) {
    double highest = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] < highest) {
        highest = array[i];
      }
    }
    return highest;
  }

}
