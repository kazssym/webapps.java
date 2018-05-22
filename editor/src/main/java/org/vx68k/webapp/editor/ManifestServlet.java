/*
 * ManifestServlet.java - application manifest generator
 * Copyright (C) 2018 Kaz Nishimura
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */

package org.vx68k.webapp.editor;

import java.io.IOException;
import javax.json.Json;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Application manifest generator.
 *
 * @author Kaz Nishimura
 * @since 1.0
 * @see <a href="https://www.w3.org/TR/appmanifest/">Web App Manifest</a>
 */
@WebServlet(name = "ManifestServlet", urlPatterns = {"/manifest/*"},
    initParams = {
        @WebInitParam(name = ManifestServlet.NAME, value = "Dummy App"),
        @WebInitParam(name = ManifestServlet.SHORT_NAME, value = "Dummy"),
    })
public final class ManifestServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * Content type of application manifests.
     */
    private static final String CONTENT_TYPE = "application/manifest+json";

    /**
     * Name of the initialization parameter for the name of the application.
     */
    public static final String NAME = "name";

    /**
     * Name of the initialization parameter for the short name of the
     * application.
     */
    public static final String SHORT_NAME = "shortName";

    /**
     * Generated manifest.
     */
    private transient Manifest manifest;

    /**
     * Returns the generated manifest.
     *
     * @return the generated manifest
     */
    public Manifest getManifest()
    {
        return manifest;
    }

    /**
     * Generates a new manifest from the {@link ServletConfig} object.
     *
     * @return a new manifest
     */
    protected Manifest newManifest()
    {
        ServletConfig servletConfig = getServletConfig();
        Manifest newManifest = new Manifest();
        newManifest.setName(servletConfig.getInitParameter(NAME));
        newManifest.setShortName(servletConfig.getInitParameter(SHORT_NAME));
        return newManifest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final ServletConfig config)
        throws ServletException
    {
        assert config != null;
        super.init(config);
        manifest = newManifest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy()
    {
        super.destroy();
        manifest = null;
    }

    /**
     * Serves the generated manifest as the response body.
     *
     * @exception IOException if an I/O exception has occurred
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
        throws IOException
    {
        if (manifest == null) {
            throw new IllegalStateException("Not initialized");
        }

        response.setContentType(CONTENT_TYPE);
        JsonWriter writer = Json.createWriter(response.getOutputStream());
        try {
            writer.writeObject(manifest.toJsonObject());
        }
        finally {
            writer.close();
        }
    }
}
