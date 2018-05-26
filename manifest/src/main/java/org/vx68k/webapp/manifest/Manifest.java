/*
 * Manifest.java - web app manifest
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

import java.io.Serializable;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Web app manifest.
 *
 * @author Kaz Nishimura
 * @since 1.0
 * @see <a href="https://www.w3.org/TR/appmanifest/">Web App Manifest</a>
 */
public class Manifest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Name of the web app.
     */
    private String name;

    /**
     * Short name of the web app.
     */
    private String shortName;

    /**
     * Icons of the web app.
     */
    private ImageResource[] icons;

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
     * @param value the new name
     */
    public final void setName(final String value)
    {
        name = value;
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
     * @param value the new short name
     */
    public final void setShortName(final String value)
    {
        shortName = value;
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
     * @param value the new icons
     */
    public final void setIcons(final ImageResource[] value)
    {
        icons = value;
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
