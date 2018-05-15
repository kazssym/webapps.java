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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Application manifest generator.
 *
 * @author Kaz Nishimura
 * @see <a href="https://www.w3.org/TR/appmanifest/">Web App Manifest</a>
 */
@WebServlet(name = "ManifestServlet", value = {"/manifest/*"})
public final class ManifestServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * Content type of application manifests.
     */
    private static final String CONTENT_TYPE = "application/manifest+json";

    /**
     * Generated manifest.
     */
    protected transient Manifest manifest;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final ServletConfig config)
        throws ServletException
    {
        super.init(config);
        manifest = new Manifest();
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
     * Generates an application manifest.
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType(CONTENT_TYPE);
        // TODO: Construct a real manifest.
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("{}");
        response.getWriter().close();
    }
}
