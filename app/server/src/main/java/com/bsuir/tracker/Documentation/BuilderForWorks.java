package com.bsuir.tracker.Documentation;

import com.bsuir.tracker.Documentation.Enumerations.DocumentType;
import com.bsuir.tracker.Documentation.Factories.Entities.WorkedInfo;
import com.bsuir.tracker.Documentation.Factories.Implementations.Request_Blank;
import com.bsuir.tracker.Documentation.Factories.Implementations.IFactory;
import com.bsuir.tracker.Documentation.Generators.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by angre on 12.05.2017.
 */
public class BuilderForWorks {
    private IGenerator<WorkedInfo> documentGenerator;

    private IFactory<WorkedInfo> modelViewer;

    private final Map<DocumentType, IGenerator<WorkedInfo>> defaultGenerators = new HashMap<DocumentType, IGenerator<WorkedInfo>>() {{
        put(DocumentType.csvd, new CSVGeneratorWorked());
        put(DocumentType.pdfd, new PDFGeneratorWorked());
        put(DocumentType.xlsd, new XLSGeneratorWorked());
    }};

    private boolean isProtectedFromCopy = false;

    public BuilderForWorks setProtectedFromCopy(boolean value) {
        this.isProtectedFromCopy = value;
        return this;

    }

    public BuilderForWorks setDocumentType(DocumentType documentType) {
        setDocumentGenerator(defaultGenerators.get(documentType));
        return this;
    }

    public BuilderForWorks setDocumentGenerator(IGenerator<WorkedInfo> documentGenerator) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        this.documentGenerator = documentGenerator;

        return this;
    }

    public BuilderForWorks setModelViewer(IFactory<WorkedInfo> modelViewer) {
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");
        this.modelViewer = modelViewer;

        return this;

    }

    public void writeToResponse(List<WorkedInfo> entityList, HttpServletResponse response) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");

        documentGenerator.setIsProtected(isProtectedFromCopy);
        documentGenerator.setModelViewer(modelViewer);
        documentGenerator.writeToResponse(entityList, response);
    }
}