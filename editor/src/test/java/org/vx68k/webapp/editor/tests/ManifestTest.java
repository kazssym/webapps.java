/*
 * ManifestTest.java
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

package org.vx68k.webapp.editor.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.json.JsonValue;

import org.junit.Test;
import org.vx68k.webapp.editor.Manifest;
import org.vx68k.webapp.editor.ManifestImage;

/**
 * Test fixture for {@link Manifest}.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
public final class ManifestTest
{
    /**
     * Tests the {@code name} property.
     */
    @Test
    public void testName()
    {
        Manifest manifest = new Manifest();
        assertNull("manifest.name", manifest.getName());
        assertFalse("'name' in JSON(manifest)",
            manifest.toJsonObject().containsKey("name"));

        manifest.setName("a name");
        assertEquals("manifest.name", "a name", manifest.getName());
        assertEquals("JSON(manifest)['name']", "a name",
            manifest.toJsonObject().getString("name"));

        manifest.setName(null);
        assertNull("manifest.name", manifest.getName());
    }

    /**
     * Tests the {@code shortName} property.
     */
    @Test
    public void testShortName()
    {
        Manifest manifest = new Manifest();
        assertNull("manifest.shortName", manifest.getShortName());
        assertFalse("'short_name' in JSON(manifest)",
            manifest.toJsonObject().containsKey("short_name"));

        manifest.setShortName("a short name");
        assertEquals("manifest.shortName", "a short name",
            manifest.getShortName());
        assertEquals("JSON(manifest)['short_name']", "a short name",
            manifest.toJsonObject().getString("short_name"));

        manifest.setShortName(null);
        assertNull("manifest.shortName", manifest.getShortName());
    }

    /**
     * Tests the {@code icons} property.
     */
    @Test
    public void testIcons()
    {
        Manifest manifest = new Manifest();
        assertNull("manifest.icons", manifest.getIcons());
        assertFalse("'icons' in JSON(manifest)",
            manifest.toJsonObject().containsKey("icons"));

        manifest.setIcons(new ManifestImage[0]);
        assertEquals("manifest.icons.length", 0, manifest.getIcons().length);
        assertEquals("JSON(manifest)['icons'].length", 0,
            manifest.toJsonObject().getJsonArray("icons").size());

        manifest.setIcons(new ManifestImage[1]);
        assertNull("manifest.icons[0]", manifest.getIcons()[0]);
        assertTrue("JSON(manifest)['icons'][0]",
            manifest.toJsonObject().getJsonArray("icons").isNull(0));

        manifest.setIcons(new ManifestImage[] {new ManifestImage()});
        assertNotNull("manifest.icons[0]", manifest.getIcons()[0]);
        assertNotNull("JSON(manifest)['icons'][0]",
            manifest.toJsonObject().getJsonArray("icons").getJsonObject(0));

        manifest.setIcons(null);
        assertNull("manifest.icons", manifest.getIcons());
    }
}
