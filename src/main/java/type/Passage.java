

/* First created by JCasGen Sun Oct 04 16:19:56 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



import org.apache.uima.jcas.cas.DoubleArray;


/** Stores the information of the passage.
 * Updated by JCasGen Mon Oct 19 13:23:53 EDT 2015
 * XML source: /Users/Yash/Dropbox/CMU/courses/Design and Engineering of Intelligent Information Systems/pi7-ygaur/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class Passage extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Passage.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Passage() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Passage(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Passage(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Passage(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets The passage text extracted from the source document.
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "type.Passage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Passage_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets The passage text extracted from the source document. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "type.Passage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Passage_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets This stores a gold standard label of this answer.  'True' means that it answers the question; otherwise, it is does not.
   * @generated
   * @return value of the feature 
   */
  public boolean getLabel() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Passage");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets This stores a gold standard label of this answer.  'True' means that it answers the question; otherwise, it is does not. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(boolean v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Passage");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: sourceDocId

  /** getter for sourceDocId - gets This specifies the source document id to which the passage belongs to.
   * @generated
   * @return value of the feature 
   */
  public String getSourceDocId() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sourceDocId == null)
      jcasType.jcas.throwFeatMissing("sourceDocId", "type.Passage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sourceDocId);}
    
  /** setter for sourceDocId - sets This specifies the source document id to which the passage belongs to. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSourceDocId(String v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sourceDocId == null)
      jcasType.jcas.throwFeatMissing("sourceDocId", "type.Passage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sourceDocId, v);}    
   
    
  //*--------------*
  //* Feature: question

  /** getter for question - gets This denotes the corresponding question ID.
   * @generated
   * @return value of the feature 
   */
  public Question getQuestion() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.Passage");
    return (Question)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_question)));}
    
  /** setter for question - sets This denotes the corresponding question ID. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestion(Question v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.Passage");
    jcasType.ll_cas.ll_setRefValue(addr, ((Passage_Type)jcasType).casFeatCode_question, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: WordVector

  /** getter for WordVector - gets Stores the word vector
   * @generated
   * @return value of the feature 
   */
  public DoubleArray getWordVector() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_WordVector == null)
      jcasType.jcas.throwFeatMissing("WordVector", "type.Passage");
    return (DoubleArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_WordVector)));}
    
  /** setter for WordVector - sets Stores the word vector 
   * @generated
   * @param v value to set into the feature 
   */
  public void setWordVector(DoubleArray v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_WordVector == null)
      jcasType.jcas.throwFeatMissing("WordVector", "type.Passage");
    jcasType.ll_cas.ll_setRefValue(addr, ((Passage_Type)jcasType).casFeatCode_WordVector, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for WordVector - gets an indexed value - Stores the word vector
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public double getWordVector(int i) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_WordVector == null)
      jcasType.jcas.throwFeatMissing("WordVector", "type.Passage");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_WordVector), i);
    return jcasType.ll_cas.ll_getDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_WordVector), i);}

  /** indexed setter for WordVector - sets an indexed value - Stores the word vector
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setWordVector(int i, double v) { 
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_WordVector == null)
      jcasType.jcas.throwFeatMissing("WordVector", "type.Passage");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_WordVector), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_WordVector), i, v);}
   
    
  //*--------------*
  //* Feature: SystemLabel

  /** getter for SystemLabel - gets Stores the label that system will provide.
   * @generated
   * @return value of the feature 
   */
  public boolean getSystemLabel() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_SystemLabel == null)
      jcasType.jcas.throwFeatMissing("SystemLabel", "type.Passage");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_SystemLabel);}
    
  /** setter for SystemLabel - sets Stores the label that system will provide. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSystemLabel(boolean v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_SystemLabel == null)
      jcasType.jcas.throwFeatMissing("SystemLabel", "type.Passage");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_SystemLabel, v);}    
  }

    