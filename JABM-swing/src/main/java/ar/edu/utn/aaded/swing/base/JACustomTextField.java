package ar.edu.utn.aaded.swing.base;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aadeed.model.JAFieldDescription;

public class JACustomTextField extends JTextField {
 
	private static final long serialVersionUID = 1L;
	
	protected int maxLength = -1;
    protected JAFieldDescription field;
 
    public JACustomTextField(final JAFieldDescription field) {
        super();
        setField(field);
    }
     
    public JACustomTextField(final JAFieldDescription field, final int cols) {
        super(cols);
        setField(field);
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
     
    public JAFieldDescription getField() {
		return field;
	}
    
    public void setField(final JAFieldDescription field) {
		this.field = field;
		setMaximumLength(field.getMaxLength());
	}
    
    private final class RegexDocument extends PlainDocument {

    	private static final long serialVersionUID = 1L;

		@Override
		public void remove(int offs, int len) throws BadLocationException {
			
			final String proposedRemove = getText(0, offs) + getText(offs + len, getLength() - offs - len);
			
			if(validateInput(proposedRemove)) {
				super.remove(offs, len);
            }
		}

		@Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			
            if(str == null) {
                return;
            }
            
            final String proposedInsert = getText(0, offs) + str + getText(offs, getLength() - offs);
 
            if (maxLength < 0) {
            	
                if(validateInput(proposedInsert)) {
                	super.insertString(offs, str, a);
                }
                
            } else {
                
            	if(offs < maxLength && offs >= 0 && getLength() + str.length() <= maxLength && validateInput(proposedInsert)) {
                	super.insertString(offs, str, a);
                }
            	
            }
        }
		
		private boolean validateInput(final String input) {
			
			if (StringUtils.isBlank(input)) {
				return true;
			}
			
			try {
				ConvertUtils.convert(input, field.getClazz());
				return true;
			} catch (Exception e) {
				return false;
			}
		}
    }
}
