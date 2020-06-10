/*
 * ImageResource.java - class ImageResource
 * Copyright (C) 2018-2020 Kaz Nishimura
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

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Image resource in a web app manifest.
 *
 * <p>This implementation is not complete, and has only the following
 * properties:</p>
 * <ul>
 * <li>{@code src}</li>
 * <li>{@code sizes}</li>
 * <li>{@code type}</li>
 * </ul>
 *
 * @author Kaz Nishimura
 * @since 1.0
 * @see <a href="https://www.w3.org/TR/appmanifest/">"Web App Manifest"</a>
 */
public class ImageResource implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * URL ({@code src}) of the image resource.
     */
    @JsonbProperty("src")
    private String src = null;

    /**
     * Sizes of the image resource.
     */
    @JsonbProperty("sizes")
    private String sizes = null;

    /**
     * Media type of the image resource.
     */
    @JsonbProperty("type")
    private String type = null;

    /**
     * Constructs an blank image resource.
     */
    public ImageResource()
    {
        // All the fields have the default values.
    }

    /**
     * Constructs an image resource by copying another one.
     *
     * @param other another image resource
     * @see #duplicate()
     * @since 2.0
     */
    protected ImageResource(final ImageResource other)
    {
        this.src = other.src;
        this.sizes = other.sizes;
        this.type = other.type;
    }

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
     * @param value the new URL
     */
    public final void setSrc(final String value)
    {
        src = value;
    }

    /**
     * Returns the sizes of the image resource.
     *
     * @return the sizes, or {@code null} if not specified
     */
    public final String getSizes()
    {
        return sizes;
    }

    /**
     * Sets the sizes of the image resource.
     *
     * @param value the new sizes
     */
    public final void setSizes(final String value)
    {
        sizes = value;
    }

    /**
     * Returns the media type of the image resource.
     *
     * @return the media type, or {@code null} if not specified
     */
    public final String getType()
    {
        return type;
    }

    /**
     * Sets the media type of the image resource.
     *
     * @param value the new type
     */
    public final void setType(final String value)
    {
        type = value;
    }

    /**
     * Returns a JSON object that represents this image resource.
     *
     * @return a JSON object
     */
    public final JsonObject toJsonObject()
    {
        JsonObjectBuilder object = Json.createObjectBuilder();
        if (src != null) {
            object.add("src", src);
        }
        if (sizes != null) {
            object.add("sizes", sizes);
        }
        if (type != null) {
            object.add("type", type);
        }
        return object.build();
    }

    /**
     * Returns a new copy of the image resource.
     *
     * @return a new copy of the image resource
     * @see #ImageResource(ImageResource)
     * @since 2.0
     */
    public ImageResource duplicate()
    {
        return new ImageResource(this);
    }
}
