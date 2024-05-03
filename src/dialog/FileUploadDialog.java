package dialog;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;
import java.nio.file.DirectoryStream.Filter;
import javax.swing.JDialog;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileUploadDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private String selectedFilePath;
    private String selectedFileName;
    

    public FileUploadDialog(AddSubjectDialog parent) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(parent);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt 파일", "txt");
      
     
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // 선택한 파일의 경로를 가져옴
            selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            // 선택한 파일의 이름을 가져옴
            selectedFileName = fileChooser.getSelectedFile().getName();
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
