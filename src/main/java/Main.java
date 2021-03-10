import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        Word w = new Word();
        XWPFDocument document = w.createDocument();

        File readFolder = new File("readFolder");
        MyFileReader fr = new MyFileReader(document);
        fr.runWriteFromRootFolder(readFolder, w);

        w.writeToFile(fr.getDocument());
        System.out.println("success");
    }
}
