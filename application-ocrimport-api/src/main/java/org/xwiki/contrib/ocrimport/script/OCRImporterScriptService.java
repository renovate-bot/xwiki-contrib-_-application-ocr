/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.ocrimport.script;

import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.contrib.ocrimport.OCRImporterException;
import org.xwiki.contrib.ocrimport.OCRImporterManager;
import org.xwiki.contrib.ocrimport.XDOMDocument;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.script.service.ScriptService;

/**
 * Exposes the OCR importer APIs to server-side scripts.
 *
 * @version $Id$
 * @since 1.0
 */
@Component
@Named(OCRImporterScriptService.ROLE_HINT)
@Singleton
public class OCRImporterScriptService implements ScriptService
{
    /**
     * Hint of the component.
     */
    static final String ROLE_HINT = "ocrimporter";

    @Inject
    private OCRImporterManager ocrImporterManager;

    /**
     * Create a {@link XDOMDocument} corresponding to the given file content.
     *
     * @param fileStream the file to import
     * @param fileName the file name
     * @return the generated document
     * @throws OCRImporterException if an error occurs during the importation
     */
    XDOMDocument importDocument(InputStream fileStream, String fileName) throws OCRImporterException
    {
        return ocrImporterManager.importDocument(fileStream, fileName);
    }

    /**
     * Saves the given {@link XDOMDocument} in the correct wiki emplacement.
     *
     * @param document the {@link XDOMDocument} to save
     * @param targetReference the reference of the target wiki page
     * @param syntaxId the syntax that should be used to save the document
     * @param title the title of the document
     * @param append if true, append the content of the {@link XDOMDocument} to the target document
     * @throws OCRImporterException if an error occurs
     */
    void saveDocument(XDOMDocument document, DocumentReference targetReference, String syntaxId, String title,
            boolean append) throws OCRImporterException
    {
        ocrImporterManager.saveDocument(document, targetReference, syntaxId, title, append);
    }
}
