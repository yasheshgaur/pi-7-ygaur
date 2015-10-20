
/* First created by JCasGen Sun Oct 04 16:19:56 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Stores the information of the passage.
 * Updated by JCasGen Mon Oct 19 13:23:53 EDT 2015
 * @generated */
public class Passage_Type extends ComponentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Passage_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Passage_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Passage(addr, Passage_Type.this);
  			   Passage_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Passage(addr, Passage_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Passage.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.Passage");
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "type.Passage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "type.Passage");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_label;
  /** @generated */
  final int     casFeatCode_label;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getLabel(int addr) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "type.Passage");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_label);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLabel(int addr, boolean v) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "type.Passage");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_label, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sourceDocId;
  /** @generated */
  final int     casFeatCode_sourceDocId;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSourceDocId(int addr) {
        if (featOkTst && casFeat_sourceDocId == null)
      jcas.throwFeatMissing("sourceDocId", "type.Passage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sourceDocId);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSourceDocId(int addr, String v) {
        if (featOkTst && casFeat_sourceDocId == null)
      jcas.throwFeatMissing("sourceDocId", "type.Passage");
    ll_cas.ll_setStringValue(addr, casFeatCode_sourceDocId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_question;
  /** @generated */
  final int     casFeatCode_question;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuestion(int addr) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "type.Passage");
    return ll_cas.ll_getRefValue(addr, casFeatCode_question);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestion(int addr, int v) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "type.Passage");
    ll_cas.ll_setRefValue(addr, casFeatCode_question, v);}
    
  
 
  /** @generated */
  final Feature casFeat_WordVector;
  /** @generated */
  final int     casFeatCode_WordVector;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getWordVector(int addr) {
        if (featOkTst && casFeat_WordVector == null)
      jcas.throwFeatMissing("WordVector", "type.Passage");
    return ll_cas.ll_getRefValue(addr, casFeatCode_WordVector);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWordVector(int addr, int v) {
        if (featOkTst && casFeat_WordVector == null)
      jcas.throwFeatMissing("WordVector", "type.Passage");
    ll_cas.ll_setRefValue(addr, casFeatCode_WordVector, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public double getWordVector(int addr, int i) {
        if (featOkTst && casFeat_WordVector == null)
      jcas.throwFeatMissing("WordVector", "type.Passage");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_WordVector), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_WordVector), i);
  return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_WordVector), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setWordVector(int addr, int i, double v) {
        if (featOkTst && casFeat_WordVector == null)
      jcas.throwFeatMissing("WordVector", "type.Passage");
    if (lowLevelTypeChecks)
      ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_WordVector), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_WordVector), i);
    ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_WordVector), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_SystemLabel;
  /** @generated */
  final int     casFeatCode_SystemLabel;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getSystemLabel(int addr) {
        if (featOkTst && casFeat_SystemLabel == null)
      jcas.throwFeatMissing("SystemLabel", "type.Passage");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_SystemLabel);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSystemLabel(int addr, boolean v) {
        if (featOkTst && casFeat_SystemLabel == null)
      jcas.throwFeatMissing("SystemLabel", "type.Passage");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_SystemLabel, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Passage_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_label = jcas.getRequiredFeatureDE(casType, "label", "uima.cas.Boolean", featOkTst);
    casFeatCode_label  = (null == casFeat_label) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_label).getCode();

 
    casFeat_sourceDocId = jcas.getRequiredFeatureDE(casType, "sourceDocId", "uima.cas.String", featOkTst);
    casFeatCode_sourceDocId  = (null == casFeat_sourceDocId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sourceDocId).getCode();

 
    casFeat_question = jcas.getRequiredFeatureDE(casType, "question", "type.Question", featOkTst);
    casFeatCode_question  = (null == casFeat_question) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_question).getCode();

 
    casFeat_WordVector = jcas.getRequiredFeatureDE(casType, "WordVector", "uima.cas.DoubleArray", featOkTst);
    casFeatCode_WordVector  = (null == casFeat_WordVector) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_WordVector).getCode();

 
    casFeat_SystemLabel = jcas.getRequiredFeatureDE(casType, "SystemLabel", "uima.cas.Boolean", featOkTst);
    casFeatCode_SystemLabel  = (null == casFeat_SystemLabel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SystemLabel).getCode();

  }
}



    