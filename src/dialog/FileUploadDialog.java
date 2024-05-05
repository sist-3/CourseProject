package dialog;

import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileUploadDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private String selectedFilePath;
    private String selectedFileName;
    private AddSubjectDialog parent;
   
    public FileUploadDialog(AddSubjectDialog parent, String subjectName) {
    	 
    	
    	this.parent = parent;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(parent);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt 파일", "txt");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFileName = subjectName + ".txt"; // 과목명을 파일명에 추가
            selectedFilePath = "src/resources/subplan/" + selectedFileName;
           
            File newFile = new File(selectedFilePath);

            
            // 선택한 파일을 복사
             try {
                 Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
             } catch (IOException e) {
                e.printStackTrace();
             }
        } else {
            // 사용자가 파일을 선택하지 않았을 경우, 선택한 파일 경로와 파일 이름을 null로 설정
            selectedFilePath = null;
            selectedFileName = null;
        }
    }

    public String getSelectedFilePath() {
        return selectedFilePath;
    }

    public String getSelectedFileName() {
        return selectedFileName;
    }

}
