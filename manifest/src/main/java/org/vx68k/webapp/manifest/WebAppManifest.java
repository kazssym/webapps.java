/*
 * WebAppManifest.java - class WebAppManifest
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
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.annotation.JsonbProperty;

/**
 * Web app manifest.
 *
 * <p>This implementation in not complete, and has only the following
 * properties:</p>
 * <ul>
 * <li>{@code name}</li>
 * <li>{@code shortName} ({@code short_name} in JSON)</li>
 * <li>{@code icons}</li>
 * </ul>
 *
 * @author Kaz Nishimura
 * @since 1.0
 * @see <a href="https://www.w3.org/TR/appmanifest/">"Web App Manifest"</a>
 */
public class WebAppManifest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Name of the web app.
     */
    @JsonbProperty("name")
    private String name = null;

    /**
     * Short name of the web app.
     */
    @JsonbProperty("short_name")
    private String shortName = null;

    /**
     * Icons of the web app.
     */
    @JsonbProperty("icons")
    private ImageResource[] icons = null;

    /**
     * Returns a new copy of an array of image resources.
     *
     * @param resources an array of image resources
     * @return a new copy of the array of image resources
     */
    protected static ImageResource[] duplicate(final ImageResource[] resources)
    {
        return Arrays.stream(resources).map(ImageResource::duplicate)
            .toArray(ImageResource[]::new);
    }

    /**
     * Constructs a blank manifest.
     */
    public WebAppManifest()
    {
        // All the fields have the default values.
    }

    /**
     * Constructs a manifest by copying another one.
     *
     * @param other another manifest
     * @see #duplicate()
     * @since 2.0
     */
    protected WebAppManifest(final WebAppManifest other)
    {
        this.name = other.name;
        this.shortName = other.shortName;
        this.icons = duplicate(other.icons);
    }

    /**
     * Returns the name of the web app.
     *
     * @return the name, or {@code null} if not specified
     */
    public final String getName()
    {
        return name;
    }

    /**
     * Sets the name of the web app.
     *
     * @param name the new name
     */
    public final void setName(final String name)
    {
        this.name = name;
    }

    /**
     * Returns the short name of the web app.
     *
     * @return the short name, or {@code null} if not specified
     */
    public final String getShortName()
    {
        return shortName;
    }

    /**
     * Sets the short name of the web app.
     *
     * @param shortName the new short name
     */
    public final void setShortName(final String shortName)
    {
        this.shortName = shortName;
    }

    /**
     * Returns the icons of the web app.
     *
     * @return the icons, or {@code null} if not specified
     */
    public final ImageResource[] getIcons()
    {
        return icons;
    }

    /**
     * Sets the icons of the web app.
     *
     * <p>Each element must not be {@code null}.</p>
     *
     * @param icons the new icons
     */
    public final void setIcons(final ImageResource[] icons)
    {
        ImageResource[] copy = null;
        if (icons != null) {
            copy = duplicate(icons);
        }
        this.icons = copy;
    }

    /**
     * Returns a JSON object that represents this manifest.
     *
     * @return a JSON object
     */
    public final JsonObject toJsonObject()
    {
        JsonObjectBuilder object = Json.createObjectBuilder();
        if (name != null) {
            object.add("name", name);
        }
        if (shortName != null) {
            object.add("short_name", shortName);
        }
        if (icons != null) {
            JsonArrayBuilder iconsArray = Json.createArrayBuilder();
            Arrays.stream(icons)
                .forEachOrdered((icon) -> {
                    iconsArray.add(icon.toJsonObject());
                });
            object.add("icons", iconsArray);
        }
        return object.build();
    }

    /**
     * Returns a new copy of the manifest.
     *
     * @return a new copy of the manifest
     * @see #WebAppManifest(WebAppManifest)
     * @since 2.0
     */
    public WebAppManifest duplicate()
    {
        return new WebAppManifest(this);
    }
}
