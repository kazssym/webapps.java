/*
 * WebAppManifestServlet.java - class WebAppManifestServlet
 * Copyright (C) 2018-2019 Kaz Nishimura
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
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
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
public class WebAppManifestServlet extends HttpServlet
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
     * Manifest that is served by the servlet.
     *
     * It shall be created by {@link #init(ServletConfig)}.
     */
    private WebAppManifest manifest = null;

    /**
     * Last modified time in milliseconds since 1970-01-01T00:00:00Z.
     */
    private long lastModified = 0;

    /**
     * Returns the manifest that is served by the servlet.
     *
     * @return the manifest that is served by the servlet
     */
    public final WebAppManifest getManifest()
    {
        return manifest;
    }

    /**
     * Sets the manifest.
     *
     * @param manifest a new manifest
     */
    public final void setManifest(final WebAppManifest manifest)
    {
        this.manifest = manifest.duplicate();
        lastModified = System.currentTimeMillis();
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
     *
     * <p>This implementation creates a new manifest.</p>
     */
    @Override
    public void init(final ServletConfig config)
        throws ServletException
    {
        super.init(config);
        setManifest(createManifest(getServletConfig()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy()
    {
        manifest = null;
        super.destroy();
    }

    /**
     * Serves the manifest as the response body.
     *
     * {@inheritDoc}
     *
     * @exception IOException if an I/O exception has occurred
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
        throws IOException
    {
        try (Jsonb jsonb = JsonbBuilder.create()) {
            jsonb.toJson(manifest, response.getOutputStream());
        }
        catch (Exception e) {
            throw new IOException(e);
        }
        response.setContentType(CONTENT_TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected long getLastModified(final HttpServletRequest request)
    {
        return lastModified;
    }
}
