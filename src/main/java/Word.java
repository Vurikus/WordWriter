import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Word {


    public XWPFDocument createDocument(){
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
        return docxModel;
    }

    public void writeToFile(XWPFDocument document) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("Source.docx");
        document.write(outputStream);
        outputStream.close();
    }

    public void writeTitle(XWPFDocument docxModel, String title){
        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphConfig = bodyParagraph.createRun();
        paragraphConfig.setBold(true);
        paragraphConfig.setFontSize(12);
        // HEX цвет без решетки #
        paragraphConfig.setColor("31d8eb");
        paragraphConfig.setText(title);
    }

    public void writeBody(XWPFDocument docxModel, String body){
        XWPFParagraph bodyParagraph = docxModel.createParagraph();
//        bodyParagraph.setAlignment(ParagraphAlignment);
        XWPFRun paragraphConfig = bodyParagraph.createRun();
        paragraphConfig.setFontSize(8);
        // HEX цвет без решетки #
//        paragraphConfig.setColor("31d8eb");
        String[] split = body.split("\n");
        for (String s: split) {
            paragraphConfig.setText(s);
            paragraphConfig.addBreak();
        }
    }
}
