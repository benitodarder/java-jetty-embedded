package local.tin.tests.utils.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author benito.darder
 */
public class XMLUtilTest {
    
    private static final String SAMPLE_XML_FILE_NAME = "sample.xml";    
    
    @Test
    public void getDocumentFromFile_returns_the_expected_document_from_file() throws ParserConfigurationException, Exception {
        
        Document result = XMLUtil.getInstance().getDocumentFromFile(XMLUtilTest.class.getResource(SAMPLE_XML_FILE_NAME).getPath());
        
        assertThat(result.getDocumentElement().getNodeName(), equalTo("note"));
        assertThat(result.getDocumentElement().getFirstChild().getNextSibling().getNodeName(), equalTo("to"));
        assertThat(result.getDocumentElement().getFirstChild().getNextSibling().getTextContent(), equalTo("Tove"));
    }
    
    @Test
    public void checkWellFormednessResult_returns_WELL_FORMED_when_no_exception_is_thrown() throws ParserConfigurationException, SAXException, Exception {   
        String sampleXML = getFileAsString(XMLUtilTest.class, SAMPLE_XML_FILE_NAME);

        String result = XMLUtil.getInstance().getWellFormedStatusFromXMLReader(sampleXML);

        assertThat(result, equalTo(XMLUtil.WELL_FORMED_XML));
    }  
    
    public String getFileAsString(Class klass, String fileName) throws IOException {
        InputStreamReader fileInputStream = new InputStreamReader(klass.getResourceAsStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(fileInputStream);
        StringBuilder stringBuilder = new StringBuilder();
        String string = bufferedReader.readLine();
        while (string != null) {
            stringBuilder.append(string);
            string = bufferedReader.readLine();
            if (string != null) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }    
    
    @Test
    public void checkWellFormednessResult_does_not_return_WELL_FORMED_when_no_exception_is_thrown() throws ParserConfigurationException, SAXException, Exception {   
        String sampleXML = getFileAsString(XMLUtilTest.class, SAMPLE_XML_FILE_NAME);
        sampleXML = sampleXML + sampleXML;

        String result = XMLUtil.getInstance().getWellFormedStatusFromXMLReader(sampleXML);

        assertThat(result, not(equalTo(XMLUtil.WELL_FORMED_XML)));
    }      
    
    @Test
    public void getDocumentPrettyPrinted_returns_the_expected_document_from_file() throws ParserConfigurationException, Exception {
        String sampleXML = getFileAsString(XMLUtilTest.class, SAMPLE_XML_FILE_NAME);
        
        String result = XMLUtil.getInstance().getDocumentPrettyPrinted(XMLUtil.getInstance().getDocumentFromFile(XMLUtilTest.class.getResource(SAMPLE_XML_FILE_NAME).getPath()));
        
        assertThat(result, notNullValue());
    }    
}
