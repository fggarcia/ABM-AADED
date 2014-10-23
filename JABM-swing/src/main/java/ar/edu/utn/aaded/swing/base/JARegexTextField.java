package ar.edu.utn.aaded.swing.base;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JARegexTextField extends JTextField {
 
	private static final long serialVersionUID = 1L;
	
	protected int maxLength = -1;
    protected String regexCheck = ".*";
 
    public JARegexTextField() {
        super();
    }
 
    public JARegexTextField(int cols) {
        super(cols);
    }
     
    public JARegexTextField(int cols, int maxLength) {
        super(cols);
        setMaximumLength(maxLength);
    }
     
    @Override
    protected Document createDefaultModel() {
        return new RegexDocument();
    }
     
    public int getMaximumLength() {
        return maxLength;
    }
 
    public void setMaximumLength(int max) {
        maxLength = max;
    }
     
    public String getRegexFilter() {
        return String.valueOf(regexCheck);
    }
 
    public void setRegexFilter(String regex) {
        regexCheck = regex;
    }
 
    private final class RegexDocument extends PlainDocument {

    	private static final long serialVersionUID = 1L;

		@Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			
            if(str == null) {
                return;
            }
 
            if (maxLength < 0) {
            	
                if(str.matches(regexCheck)) {
                	super.insertString(offs, str, a);
                }
                
            } else {
                
            	if(offs < maxLength && offs >= 0 && str.matches(regexCheck) && getLength() < maxLength) {
                	super.insertString(offs, str, a);
                }
            	
            }
        }
    }
}
