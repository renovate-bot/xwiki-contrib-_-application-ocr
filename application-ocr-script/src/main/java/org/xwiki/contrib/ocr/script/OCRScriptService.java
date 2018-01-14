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
package org.xwiki.contrib.ocr.script;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.script.service.ScriptService;
import org.xwiki.script.service.ScriptServiceManager;
import org.xwiki.stability.Unstable;

/**
 * Generic Script Service for the OCR application.
 *
 * @version $Id$
 * @since 1.0
 */
@Component
@Named(OCRScriptService.ROLE_HINT)
@Singleton
@Unstable
public class OCRScriptService implements ScriptService
{
    /**
     * The name of the script service.
     */
    public static final String ROLE_HINT = "ocr";

    @Inject
    private ScriptServiceManager scriptServiceManager;

    /**
     * Get a sub script service related to wiki. (Note that we're voluntarily using an API name of "get" to make it
     * extra easy to access Script Services from Velocity (since in Velocity writing <code>$services.wiki.name</code> is
     * equivalent to writing <code>$services.wiki.get("name")</code>). It also makes it a short and easy API name for
     * other scripting languages.
     *
     * @param serviceName id of the script service
     * @return the service asked or null if none could be found
     */
    public ScriptService get(String serviceName)
    {
        return scriptServiceManager.get(ROLE_HINT + '.' + serviceName);
    }
}
