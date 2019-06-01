/*
 * EditorManifestServlet.java - class EditorManifestServlet
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

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import org.vx68k.webapp.manifest.WebAppManifestServlet;

/**
 * Web application manifest servlet for this web app.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
@WebServlet(urlPatterns = {"/manifest/*"},
    initParams = {
        @WebInitParam(name = WebAppManifestServlet.NAME, value = "Dummy App"),
        @WebInitParam(name = WebAppManifestServlet.SHORT_NAME, value = "Dummy"),
        @WebInitParam(name = WebAppManifestServlet.ICONS, value = "1"),
        @WebInitParam(name = WebAppManifestServlet.ICONS + ".1",
            value = "/icons/dummy-48.png"),
        @WebInitParam(name = WebAppManifestServlet.ICONS + ".1.sizes",
            value = "48x48"),
        @WebInitParam(name = WebAppManifestServlet.ICONS + ".1.type",
            value = "image/png"),
    })
public final class EditorManifestServlet extends WebAppManifestServlet
{
    private static final long serialVersionUID = 1L;
}
