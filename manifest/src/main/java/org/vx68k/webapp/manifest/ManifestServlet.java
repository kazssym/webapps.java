/*
 * ManifestServlet.java - class ManifestServlet
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

package org.vx68k.webapp.manifest;

import java.io.IOException;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web app manifest servlet.
 *
 * <p>This implementation takes the following initialization parameters:</p>
 * <ul>
 * <li>{@code name}</li>
 * <li>{@code short_name}</li>
 * <li>{@code icons} and {@code icons.*}</li>
 * </ul>
 *
 * @author Kaz Nishimura
 * @since 1.0
 * @see <a href="https://www.w3.org/TR/appmanifest/">Web App Manifest</a>
 */
public class ManifestServlet extends HttpServlet
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
    public static final String SHORT_NAME = "short_name";

    /**
     * Name of the initialization parameter for the icons of the application.
     */
    public static final String ICONS = "icons";

    /**
     * Generated manifest.
     */
    private transient WebAppManifest manifest;

    /**
     * Last modified time from {@code 1970-01-01T00:00:00Z} in milliseconds.
     */
    private transient long lastModified;

    /**
     * Returns the generated manifest.
     *
     * @return the generated manifest
     */
    public final WebAppManifest getManifest()
    {
        return manifest;
    }

    /**
     * Creates a new manifest from a {@link ServletConfig} object.
     *
     * @param config a {@link ServletConfig} object
     * @return a new manifest
     */
    protected static WebAppManifest createManifest(final ServletConfig config)
    {
        WebAppManifest manifest = new WebAppManifest();
        manifest.setName(config.getInitParameter(NAME));
        manifest.setShortName(config.getInitParameter(SHORT_NAME));
        manifest.setIcons(createIcons(config));
        return manifest;
    }

    /**
     * Creates a new array of icons from a {@link ServletConfig} object.
     *
     * @param config a {@link ServletConfig} object
     * @return a new array of icons
     */
    protected static ImageResource[] createIcons(final ServletConfig config)
    {
        final ServletContext context = config.getServletContext();

        String[] keys = config.getInitParameter(ICONS).split("\\s+");
        return Arrays.stream(keys).map((key) -> {
                String baseName = ICONS + "." + key;
                ImageResource icon = new ImageResource();
                String src = config.getInitParameter(baseName);
                String sizes = config.getInitParameter(baseName + ".sizes");
                String type = config.getInitParameter(baseName + ".type");
                if (src != null) {
                    if (src.startsWith("/")) {
                        src = context.getContextPath() + src;
                    }
                    icon.setSrc(src);
                }
                if (sizes != null) {
                    icon.setSizes(sizes);
                }
                if (type != null) {
                    icon.setType(type);
                }
                return icon;
            }).toArray(ImageResource[]::new);
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
        manifest = createManifest(getServletConfig());
        lastModified = System.currentTimeMillis();
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected long getLastModified(HttpServletRequest request)
    {
        return lastModified;
    }
}
