package searchEngine;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;
 
  
public class IKAnalyzerDemo  {  
	public IKAnalyzerDemo(){
		
	}
    public ArrayList<String> Depart_simple(String Concept) throws IOException{//�򵥵ķִʣ���ͳ��Ƶ��
    	ArrayList<String> result=new ArrayList<String>();
    	Analyzer anal=new IKAnalyzer(true);       
      StringReader reader=new StringReader(Concept);  
      //�ִ�  
      TokenStream ts=anal.tokenStream("", reader);  
      CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
      //�����ִ�����  
      while(ts.incrementToken()){  
          result.add(term.toString());  
      } 
      reader.close();
      anal.close();
    	return result;
    }
	public Map<String,Integer> getTextDef(String text) throws IOException {//�ִʲ���ȡ�ؼ��ֵ�Ƶ��
	    Map<String, Integer> wordsFren=new HashMap<String, Integer>();
	    IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text), true);
	    Lexeme lexeme;
	    while ((lexeme = ikSegmenter.next()) != null) {
	        if(lexeme.getLexemeText().length()>1){
	            if(wordsFren.containsKey(lexeme.getLexemeText())){
	                wordsFren.put(lexeme.getLexemeText(),wordsFren.get(lexeme.getLexemeText())+1);
	            }else {
	                wordsFren.put(lexeme.getLexemeText(),1);
	            }
	        }
	    }
	   return wordsFren;
	}
	public static void Output(Map<String,Integer> map){//Ԥ������˿ڣ����ڼ������
		 for (String key : map.keySet()) {
		        System.out.println(key+map.get(key));
		    }

	}   
} 
