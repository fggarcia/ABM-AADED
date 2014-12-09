package ar.edu.utn.aaded.swing.component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.ImagePainter;

import ar.edu.utn.aadeed.JAReflections;
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
        
		JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        
		final byte[] image = (byte[]) JAReflections.getFieldValue(item, field.getName());
		
        container.addMember(fieldLabel);
        container.addMember(new JAImageBoxMember(field, image));
	}

	public void renderForAdd(final JAFieldDescription field, final JAContainer container) {
		
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        
        container.addMember(fieldLabel);
        container.addMember(new JAImageBoxMember(field, null));
	}
	
	public static class JAImageBoxMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JButton viewSearchButton;
    	
    	private JTextField pathTextField;
    	
		public JAImageBoxMember(final JAFieldDescription field, final byte[] preLoadedimage) {
			
			this.field = field;
			
			this.viewSearchButton = new JButton("View/Search");
			
			pathTextField = new JTextField(55);
			pathTextField.setEditable(false);
			
			viewSearchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					JOptionPane.showConfirmDialog(null, createImagePanel(), "Load Image", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					
				}
			});
		}
		
		private JPanel createImagePanel() {
			
			final JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			final JXPanel imagePanel = new JXPanel();
			imagePanel.setPreferredSize(new Dimension(800, 600));
			
			final JButton loadButton = new JButton("Load");
			
			loadButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					final JFileChooser fc = new JFileChooser();
					fc.setAcceptAllFileFilterUsed(false);
					
					final FileFilter ff = NewFileFilter("Image Files", new String[] { "gif", "png", "jpg" });
					fc.addChoosableFileFilter(ff);
					fc.setFileFilter(ff);
			 
			        int returnVal = fc.showOpenDialog(mainPanel);
			        
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	
			        	final File fileImage = fc.getSelectedFile();
			        	showImage(imagePanel, fileImage);
			        } 
				}

			});
			
			final JPanel bottomPanel = new JPanel();
			bottomPanel.add(pathTextField);
			bottomPanel.add(loadButton);
			
			mainPanel.add(imagePanel);
			mainPanel.add(bottomPanel);
			
			return mainPanel;
		}
		
		private void showImage(final JXPanel imagePanel, final File fileImage) {
			
			if (!fileImage.exists()) {
				return;
			}
			
			pathTextField.setText(fileImage.getAbsolutePath());
			
			try {

				final BufferedImage readImage = ImageIO.read(fileImage);
				
				final ImagePainter painter = new ImagePainter(readImage);
				painter.setScaleToFit(true);
				
				imagePanel.setBackgroundPainter(painter);
			
			} catch (IOException ex) {
				throw new JARuntimeException("Could not load image", ex);
			}
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
			return viewSearchButton;
		}

		public Object getValue() {
			final String path = pathTextField.getText();
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