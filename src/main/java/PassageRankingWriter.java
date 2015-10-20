import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import rank.CompositeRanker;
import rank.IRanker;
import rank.NgramRanker;
import rank.OtherRanker;
import type.Measurement;
import type.Passage;
import type.Question;

/**
 * This CAS Consumer generates the report file with the method metrics
 */
public class PassageRankingWriter extends CasConsumer_ImplBase {
  final String PARAM_OUTPUTDIR = "OutputDir";

  final String OUTPUT_FILENAME = "ErrorAnalysis.csv";

  File mOutputDir;

  IRanker ngramRanker, otherRanker;

  CompositeRanker compositeRanker;

  @Override
  public void initialize() throws ResourceInitializationException {
    String mOutputDirStr = (String) getConfigParameterValue(PARAM_OUTPUTDIR);
    if (mOutputDirStr != null) {
      mOutputDir = new File(mOutputDirStr);
      if (!mOutputDir.exists()) {
        mOutputDir.mkdirs();
      }
    }

    // how about initialize the rankers in the processCas.
    // because I think we need a new rankers for every question Passage set
    // Initialize rankers
    
    /*
    compositeRanker = new CompositeRanker();
    ngramRanker = new NgramRanker();
    otherRanker = new OtherRanker();
    compositeRanker.addRanker(ngramRanker);
    compositeRanker.addRanker(otherRanker);
    */
  }

  @Override
  public void processCas(CAS arg0) throws ResourceProcessException {
    System.out.println(">> Passage Ranking Writer Processing");
    // Import the CAS as a aJCas
    JCas aJCas = null;
    File outputFile = null;
    PrintWriter writer = null;
    try {
      aJCas = arg0.getJCas();
      try {
        outputFile = new File(Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        outputFile.getParentFile().mkdirs();
        writer = new PrintWriter(outputFile);
      } catch (FileNotFoundException e) {
        System.out.printf("Output file could not be written: %s\n",
                Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        return;
      }

      writer.println("question_id,tp,fn,fp,precision,recall,f1");
      // Retrieve all the questions for printout
      List<Question> allQuestions = UimaUtils.getAnnotations(aJCas, Question.class);
      List<Question> subsetOfQuestions = RandomUtils.getRandomSubset(allQuestions, 10);

      // TODO: Here one needs to sort the questions in ascending order of their question ID

      // initialize variable that hold values for micro and macro averages
      double F1AverageMicroSum = 0.0;
      double F1AverageMicro = 0.0;
      int TPSum = 0;
      int FPSum = 0;
      int FNSum = 0;
      double PrecisionAverageMacro = 0.0;
      double RecallAverageMacro = 0.0;
      double F1AverageMacro = 0.0;
      
      for (Question question : subsetOfQuestions) {
        List<Passage> passages = UimaUtils.convertFSListToList(question.getPassages(), Passage.class);

        // TODO: Use the following three lists of ranked passages for your error analysis
        
        // initialize the rankers
        
        
        ngramRanker = new NgramRanker();
        
        List<Passage> ngramRankedPassages = ngramRanker.rank(question, passages);
        
        otherRanker = new OtherRanker();  
        
        // train the otherRanker on the question and the Given Passages
        ((OtherRanker) otherRanker).train(question, passages);
        
        //List<Passage> otherRankedPassages = otherRanker.rank(question, passages);
        
        
        compositeRanker = new CompositeRanker();
        compositeRanker.addRanker(ngramRanker);
        compositeRanker.addRanker(otherRanker);
        
        
        //List<Passage> compositeRankedPassages = compositeRanker.rank(question, passages);

        Measurement m = question.getMeasurement();
        int threshold = 5;
        m = PopulateMeasurement(m, ngramRankedPassages, threshold);
        
        // TODO: Calculate actual precision, recall and F1
        double precision = 0.0;
        double recall = 0.0;
        double f1 = 0.0;
        
        // we put 0 when division by 0 occurs
        if (m.getTp() + m.getFp() != 0) {
          precision = (double)m.getTp()/(m.getTp() + m.getFp());
        } else {
          precision = 0.0;
        }
        
        // we put 0 when division by 0 occurs
        if (m.getTp() + m.getFn() != 0) {
          recall = (double)m.getTp()/(m.getTp() + m.getFn());
        } else {
          recall = 0.0;
        }

        // we put 0 when division by 0 occurs
        if (precision + recall != 0) {
          f1= 2*precision*recall/(precision + recall);
        } else {
          f1 = 0.0;
        }

        
        
        writer.printf("%s,%d,%d,%d,%.3f,%.3f,%.3f\n", question.getId(), m.getTp(), m.getFn(),
                m.getFp(), precision, recall, f1);
        
        // update the sum values for calculating the micro and macro averages
        
        F1AverageMicroSum += f1;
        TPSum += m.getTp();
        FPSum += m.getFp();
        FNSum += m.getFn();
      }
      
      // calculate micro average
      
      F1AverageMicro = F1AverageMicroSum/subsetOfQuestions.size();
      
      // calculate macro average
      
      PrecisionAverageMacro = (double)TPSum/(TPSum + FPSum);
      RecallAverageMacro = (double)TPSum/(TPSum + FNSum);
      F1AverageMacro = 2*PrecisionAverageMacro*RecallAverageMacro/
              (RecallAverageMacro+PrecisionAverageMacro);
      
      // Print the results out
      
      System.out.println("Micro F1 Average: " + F1AverageMicro);
      System.out.println("Macro F1 Average: " + F1AverageMacro);
      
    } catch (CASException e) {
      try {
        throw new CollectionException(e);
      } catch (CollectionException e1) {
        e1.printStackTrace();
      }
    } finally {
      if (writer != null)
        writer.close();
    }
  }
  
  /** 
   * 
   * Assumes that the RankedPassages are sorted
   * */
  private Measurement PopulateMeasurement(Measurement m, List<Passage> RankedPassages, int threshold) {
    
    /* Annotate SystemLabel */
    
    RankedPassages = AnnotateSystemLabel(RankedPassages, threshold);
    
    /* Calculate TP */
    
    m.setTp(CalculateTP(RankedPassages));
    
    /* Calculate FP */
    
    m.setFp(CalculateFP(RankedPassages));
    
    /* Calculate FN */
    
    m.setFn(CalculateFN(RankedPassages));
    
    /* Calculate TN */
    
    m.setTn(CalculateTN(RankedPassages));
    
    return m;
  }
  
  public List<Passage> AnnotateSystemLabel(List<Passage> PassagesLocal, int threshold) {
    for (int i = 0; i < PassagesLocal.size(); i++) {
      if (i < threshold) {
        Passage p = PassagesLocal.get(i);
        p.setSystemLabel(true);
      } else {
        Passage p = PassagesLocal.get(i);
        p.setSystemLabel(false);
      }
    }
    return PassagesLocal; 
  }
  
  /* The below function calculate total # of true positives */

  public int CalculateTP(List<Passage> PassagesLocal) {
    int TPs = 0;
    for (int i = 0; i < PassagesLocal.size(); i++) {
      if ((PassagesLocal.get(i).getSystemLabel() == true) && 
              (PassagesLocal.get(i).getLabel() == true)) TPs++;
    }
    return TPs;
  }
  
  /* The below function calculates total # of false negatives */
  
  public int CalculateFN(List<Passage> PassagesLocal) {
    int FNs = 0;
    for (int i = 0; i < PassagesLocal.size(); i++) {
      if ((PassagesLocal.get(i).getSystemLabel() == false) && 
              (PassagesLocal.get(i).getLabel() == true)) FNs++;
    }
    return FNs;
  }
  
  /* The below function calculates total # of false positives */
  
  public int CalculateFP(List<Passage> PassagesLocal) {
    int FPs = 0;
    for (int i = 0; i < PassagesLocal.size(); i++) {
      if ((PassagesLocal.get(i).getSystemLabel() == true) && 
              (PassagesLocal.get(i).getLabel() == false)) FPs++;
    }
    return FPs;
  }
  
  /* The below function calculates total # of true negatives */
  
  public int CalculateTN(List<Passage> PassagesLocal) {
    int TNs = 0;
    for (int i = 0; i < PassagesLocal.size(); i++) {
      if ((PassagesLocal.get(i).getSystemLabel() == false) && 
              (PassagesLocal.get(i).getLabel() == false)) TNs++;
    }
    return TNs;
  }
}
