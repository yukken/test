
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.atilika.kuromoji.Tokenizer.Mode;

public class SentenceAnalysis {

	private static final String verbregex = "^動詞";
	private static final String nounregex = "^名詞";
	private static final String supportregex = "^助詞";
	public static void main(String[] args) throws Exception {
//		XmlParser.testDom();
		String sentence = "これはペンです。";
		System.out.println(sentence);
		analyze(sentence);
	}


	private static void analyze(String sentence){
		Tokenizer tokenizer = Tokenizer.builder().build();
		List<Token> result = tokenizer.tokenize(sentence);

		Pattern verebP = Pattern.compile(verbregex);
		Pattern nounP = Pattern.compile(nounregex);
		Pattern supportP = Pattern.compile(supportregex);
		List<String> verbList = new ArrayList<String>();
		List<String> nounList = new ArrayList<String>();

		int i = 0;
		for (Token token : result) {
			Matcher verbM = verebP.matcher(token.getPartOfSpeech());
			if(verbM.find()){
				verbList.add(token.getSurfaceForm());
				String[] features =token.getAllFeatures().split(",");
				//System.out.println(features[0]);//品詞
				//System.out.println(features[1]);//品詞の補助？
				//System.out.println(features[2]);//?
				//System.out.println(features[3]);//?
				//System.out.println(features[4]);//活用の種類
				//System.out.println(features[5]);//活用形
				//System.out.println(features[6]);//基本形の単語
				//System.out.println(features[7]);//ヨミ？
				//System.out.println(features[8]);//ヨミ？
			}
			Matcher nounM = nounP.matcher(token.getPartOfSpeech());
			if(nounM.find()){
				nounList.add(token.getSurfaceForm());

			}

			Matcher supportM = supportP.matcher(token.getPartOfSpeech());
			if(supportM.find()){
				if(i>0){
					Token preToken = result.get(i-1);
					System.out.println("主語は「"+preToken.getSurfaceForm()+"」です。");

				}
			}
			i++;
		}

//		System.out.println(Arrays.toString(verbList.toArray()));
//		System.out.println(Arrays.toString(nounList.toArray()));
	}


	private static void test(String sentence){
		Tokenizer tokenizer = Tokenizer.builder().build();
		List<Token> result = tokenizer.tokenize(sentence);

		for (Token token : result) {
			System.out.println(token.getBaseForm());
			System.out.println(token.getPartOfSpeech());

			System.out.println(token.getPosition());
			System.out.println(token.getReading());
			System.out.println(token.getSurfaceForm());
			System.out.println(token.getSurfaceForm() + "\t"
					+ token.getAllFeatures());
		}
	}
}
