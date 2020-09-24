package local.tin.tests.utils.ftp.model;

import java.io.InputStream;

/**
 *
 * @author benitodarder
 */
public class FTPDetails extends FTPConnection {
    
    private String destinationFolder;
    private String destinationFileName;
    private InputStream sourceStream;
    private String sourceFilePathAndName;

    public InputStream getSourceStream() {
        return sourceStream;
    }

    public void setSourceStream(InputStream uploadStream) {
        this.sourceStream = uploadStream;
    }

    public String getDestinationFileName() {
        return destinationFileName;
    }

    public void setDestinationFileName(String uploadStreamName) {
        this.destinationFileName = uploadStreamName;
    }

    public String getSourceFilePathAndName() {
        return sourceFilePathAndName;
    }

    public void setSourceFilePathAndName(String fileToUpload) {
        this.sourceFilePathAndName = fileToUpload;
    }

    public String getDestinationFolder() {
        return destinationFolder;
    }

    public void setDestinationFolder(String destinationFolder) {
        this.destinationFolder = destinationFolder;
    }
    
    
    
}
