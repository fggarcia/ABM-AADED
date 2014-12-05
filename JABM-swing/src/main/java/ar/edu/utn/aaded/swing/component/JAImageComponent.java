package ar.edu.utn.aaded.swing.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;

public class JAImageComponent implements JAViewComponent {

	public JAViewType getViewType() {
		return JAViewType.IMAGE;
	}

	public void renderForSearch(final JAFieldDescription field, final JAContainer container) {
		// Cannot search by image...
	}

	public void renderForUpdate(final Object item, final JAFieldDescription field, final JAContainer container) {
		
	}

	public void renderForAdd(final JAFieldDescription field, final JAContainer container) {
		
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        
		final JTextField pathTextField = new JTextField(55);
		pathTextField.setEditable(false);
        
        container.addMember(fieldLabel);
        container.addMember(new JAImageBoxMember(field, pathTextField));
	}
	
	public static class JAImageBoxMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JTextField textField;
    	
    	private JButton button;
    	
		public JAImageBoxMember(final JAFieldDescription field, final JTextField textField) {
			
			this.field = field;
			this.textField = textField;
			
			this.button = new JButton("Search");
	        
	        button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					int result = JOptionPane.showConfirmDialog(null, createShowAndSetPanel(textField), "Load Image", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
					
					if (JOptionPane.YES_OPTION != result) {
						textField.setText("");
					}
				}
			});
		}
		
		private JPanel createShowAndSetPanel(final JTextField pathTextField) {
			
			final JPanel panel = new JPanel();
			
			final JButton button = new JButton("Search...");
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					final JFileChooser fc = new JFileChooser();
					fc.setAcceptAllFileFilterUsed(false);
					
					final FileFilter ff = NewFileFilter("Image Files", new String[] { "gif", "png", "jpg" });
					fc.addChoosableFileFilter(ff);
					fc.setFileFilter(ff);
			 
			        int returnVal = fc.showOpenDialog(panel);
			        
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	
			        	final File file = fc.getSelectedFile();
			        	pathTextField.setText(file.getAbsolutePath());
			        } 
				}
			});
			
			panel.add(pathTextField);
			panel.add(button);
			
			return panel;
		}
		
		 private javax.swing.filechooser.FileFilter NewFileFilter(final String desc, final String[] allowed_extensions) {
	         return new javax.swing.filechooser.FileFilter() {
	             @Override
	             public boolean accept(java.io.File f) {
	                 if (f.isDirectory()) {
	                     return true;
	                 }
	                 int pos = f.getName().lastIndexOf('.');
	                 if (pos == -1) {
	                     return false;
	                 } else {
	                     String extension = f.getName().substring(pos + 1);
	                     for (String allowed_extension : allowed_extensions) {
	                         if (extension.equalsIgnoreCase(allowed_extension)) {
	                             return true;
	                         }
	                     }
	                     return false;
	                 }
	             }

	             @Override
	             public String getDescription() {
	                 return desc;
	             }
	         };
	     }

		public JAFieldDescription getField() {
			return field;
		}

		public Object getComponent() {
			return button;
		}

		public Object getValue() {
			
			final String path = textField.getText();
			
			return (StringUtils.isBlank(path)) ? new byte[0] : readImage(path);
		}
		
		private byte[] readImage(final String fullPath) {
			
			try{

				final File fileImage = new File(fullPath);
				
				if (!fileImage.exists()) {
					return new byte[0];
				}
					
				final BufferedImage originalImage = ImageIO.read(fileImage);
			 
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				ImageIO.write(originalImage, FilenameUtils.getExtension(fullPath), baos );
				baos.flush();
				
				byte[] imageInByte = baos.toByteArray();
				
				baos.close();
				
				return imageInByte;
			 
			} catch(Exception e) {
				
				throw new JARuntimeException(e.getMessage(), e);
				
			}		
		}
    }
}