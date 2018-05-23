/*
 * Manifest.java - application manifest
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
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Application manifest.
 *
 * @author Kaz Nishimura
 * @since 1.0
 * @see <a href="https://www.w3.org/TR/appmanifest/">Web App Manifest</a>
 */
public class Manifest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Name of the application.
     */
    private String name;

    /**
     * Short name of the application.
     */
    private String shortName;

    /**
     * Icon resources of the application.
     */
    private ManifestImage[] icons;

    /**
     * Returns the name of the application.
     *
     * @return the name, or {@code null} if not specified
     */
    public final String getName()
    {
        return name;
    }

    /**
     * Sets the name of the application.
     *
     * @param newName the new name
     */
    public final void setName(final String newName)
    {
        name = newName;
    }

    /**
     * Returns the short name of the application.
     *
     * @return the short name, or {@code null} if not specified
     */
    public final String getShortName()
    {
        return shortName;
    }

    /**
     * Sets the short name of the application.
     *
     * @param newShortName the new short name
     */
    public final void setShortName(final String newShortName)
    {
        shortName = newShortName;
    }

    /**
     * Returns the icon resources of the application.
     *
     * @return the icon resouces, or {@code null} if not specified
     */
    public final ManifestImage[] getIcons()
    {
        return icons;
    }

    /**
     * Sets the icon resources of the application.
     *
     * @param newIcons the new icon resources
     */
    public final void setIcons(final ManifestImage[] newIcons)
    {
        icons = newIcons;
    }

    /**
     * Returns a JSON object that represents this manifest.
     *
     * @return a JSON object
     */
    public final JsonObject toJsonObject()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        if (name != null) {
            builder.add("name", name);
        }
        if (shortName != null) {
            builder.add("short_name", shortName);
        }
        if (icons != null) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            Arrays.stream(icons).forEachOrdered((icon) -> {
                    if (icon != null) {
                        arrayBuilder.add(icon.toJsonObject());
                    }
                    else {
                        arrayBuilder.addNull();
                    }
                });
            builder.add("icons", arrayBuilder);
        }
        return builder.build();
    }
}
