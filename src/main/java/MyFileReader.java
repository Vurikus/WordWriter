import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class MyFileReader {

    private XWPFDocument document;
    private static final String [] JAVA_FORMAT = {"java", "xml", "config"};
    private static final String [] C_FORMAT = {"cs"};
    private static final String [] TS_FORMAT = {"ts", "xml", "json", "config", "css", "html", "js"};

    public MyFileReader(XWPFDocument document) {
        this.document = document;
    }

    private String readFile(File file) throws IOException {
        StringBuilder text = new StringBuilder();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null) {
            if(line.contains("//") ||
                    line.contains("package") ||
                    line.contains("import java") ||
                    line.contains("import lombok")) {
                line = reader.readLine();
                continue;
            }
//            line.replace("\n", "");
            text.append(line);
            text.append(System.getProperty("line.separator"));
            line = reader.readLine();
        }
        return text.toString();
    }

    private String readTitle(File file) throws IOException {
        return file.getName();
    }

    public void setDocument(XWPFDocument document) {
        this.document = document;
    }

    public XWPFDocument getDocument() {
        return document;
    }

    public XWPFDocument runWriteFromRootFolder(File folder, Word word) throws IOException {

        File[] folderEntries = folder.listFiles();
        for (File f: folderEntries)
        {
            if (f.isDirectory()){
                if(!f.getName().contains("target")){
                    runWriteFromRootFolder(f, word);
                }
                continue;
            }else{
                String name = "TemporaryGeneratedFile";
                int i = f.getName().lastIndexOf(".");
                String extension = f.getName().substring(i + 1);
                if(matchFormat(C_FORMAT, extension) && !f.getName().contains(name)) {
                    String title = readTitle(f);
                    String body = readFile(f);
                    word.writeTitle(document, title);
                    word.writeBody(document, body);
                }
                continue;
            }
        }
        return this.document;
    }

    private boolean matchFormat(String [] format, String extension){
        for(String s: format){
            if(extension.equals(s)){
                return true;
            };
        }
        return false;
    }
}

