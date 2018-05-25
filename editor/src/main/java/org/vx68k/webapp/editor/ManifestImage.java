/*
 * ManifestImage.java
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

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Image resource in manifests.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
public class ManifestImage implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * URL ({@code src}) of the image resource.
     */
    private String src;

    /**
     * Returns the URL ({@code src}) of the image resource.
     *
     * @return the URL, or {@code null} if not specified
     */
    public final String getSrc()
    {
        return src;
    }

    /**
     * Sets the URL ({@code src}) of the image resource.
     *
     * @param newSrc the new URL
     */
    public final void setSrc(final String newSrc)
    {
        src = newSrc;
    }

    /**
     * Returns a JSON object that represents this image resource.
     *
     * @return a JSON object
     */
    public final JsonObject toJsonObject()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        if (src != null) {
            builder.add("src", src);
        }
        return builder.build();
    }
}