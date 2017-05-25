package com.bsuir.tracker.Documentation;

import com.bsuir.tracker.Documentation.Enumerations.DocumentType;
import com.bsuir.tracker.Documentation.Factories.Entities.RequestInfo;
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
public class BuilderForBlanks {
    private IGenerator<RequestInfo> documentGenerator;

    private IFactory<RequestInfo> modelViewer;

    private final Map<DocumentType, IGenerator<RequestInfo>> defaultGenerators = new HashMap<DocumentType, IGenerator<RequestInfo>>() {{
        put(DocumentType.csvb, new CSVGeneratorRequest());
        put(DocumentType.pdfb, new PDFGeneratorRequest());
        put(DocumentType.xlsb, new XLSGeneratorRequest());
    }};

    private boolean isProtectedFromCopy = false;

    public BuilderForBlanks setProtectedFromCopy(boolean value) {
        this.isProtectedFromCopy = value;
        return this;

    }

    public BuilderForBlanks setDocumentType(DocumentType documentType) {
        setDocumentGenerator(defaultGenerators.get(documentType));
        return this;
    }

    public BuilderForBlanks setDocumentGenerator(IGenerator<RequestInfo> documentGenerator) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        this.documentGenerator = documentGenerator;

        return this;
    }

    public BuilderForBlanks setModelViewer(IFactory<RequestInfo> modelViewer) {
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");
        this.modelViewer = modelViewer;

        return this;

    }

    public void writeToResponse(List<RequestInfo> entityList, HttpServletResponse response) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");

        documentGenerator.setIsProtected(isProtectedFromCopy);
        documentGenerator.setModelViewer(modelViewer);
        documentGenerator.writeToResponse(entityList, response);
    }
}
