package docurepo.controller;

import docurepo.model.Document;
import docurepo.model.DocumentVersion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 021240146 MARIO JUAN DANTELO
 * @author 021240035 EMANUEL DELON NARO
 */
public class DocumentVersioningController {
    
    private List<DocumentVersion> versionHistory = new ArrayList<>();
    
    public DocumentVersion createVersion(Document doc, int version, String note, String author) {
        DocumentVersion dv = new DocumentVersion(doc, version, note, author, new Date());
        versionHistory.add(dv);
        return dv;
    }
    
    public List<DocumentVersion> getVersions(String docId) {
        List<DocumentVersion> result = new ArrayList<>();
        for (DocumentVersion dv : versionHistory) {
            if (dv.getDocument().getId().equals(docId)) {
                result.add(dv);
            }
        }
        return result;
    }
    
    public boolean rollbackToVersion(String docId, int version) {
        return getVersion(docId, version) != null;
    }
    
    public DocumentVersion getVersion(String docId, int version) {
        for (DocumentVersion dv : versionHistory) {
            if (dv.getDocument().getId().equals(docId) && dv.getVersionNumber() == version) {
                return dv;
            }
        }
        return null;
    }
}
