package com.bsuir.tracker.Documentation;

import com.bsuir.tracker.Documentation.Enumerations.DocumentType;
import com.bsuir.tracker.Documentation.Factories.Implementations.IFactory;
import com.bsuir.tracker.Documentation.Generators.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by angre on 29.04.2017.
 */
public class Builder<T> {
    private IGenerator<T> documentGenerator;

    private IFactory<T> modelViewer;

    private final Map<DocumentType, IGenerator<T>> defaultGenerators = new HashMap<DocumentType, IGenerator<T>>() {{
        put(DocumentType.csv, new CSVGenerator<T>());
        put(DocumentType.pdf, new PDFGenerator<T>());
        put(DocumentType.xls, new XLSGenerator<T>());
    }};

    private boolean isProtectedFromCopy = false;

    public Builder<T> setProtectedFromCopy(boolean value) {
        this.isProtectedFromCopy = value;
        return this;

    }

    public Builder<T> setDocumentType(DocumentType documentType) {
        setDocumentGenerator(defaultGenerators.get(documentType));
        return this;
    }

    public Builder<T> setDocumentGenerator(IGenerator<T> documentGenerator) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        this.documentGenerator = documentGenerator;

        return this;
    }

    public Builder setModelViewer(IFactory<T> modelViewer) {
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");
        this.modelViewer = modelViewer;

        return this;

    }

    public void writeToResponse(List<T> entityList, HttpServletResponse response) {
        if (documentGenerator == null) throw new IllegalAccessError("generator is null");
        if (modelViewer == null) throw new IllegalAccessError("viewer is null");

        documentGenerator.setIsProtected(isProtectedFromCopy);
        documentGenerator.setModelViewer(modelViewer);
        documentGenerator.writeToResponse(entityList, response);

    }
}
