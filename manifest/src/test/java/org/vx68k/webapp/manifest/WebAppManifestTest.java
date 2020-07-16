/*
 * WebAppManifestTest.java - class WebAppManifestTest
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * Test fixture for {@link WebAppManifest}.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
public final class WebAppManifestTest
{
    /**
     * Tests the {@code name} property.
     */
    @Test
    public void testName()
    {
        WebAppManifest manifest = new WebAppManifest();
        assertNull(manifest.getName(), "name");

        manifest.setName("a name");
        assertEquals("a name", manifest.getName(), "name");

        manifest.setName(null);
        assertNull(manifest.getName(), "name");
    }

    /**
     * Tests the {@code shortName} property.
     */
    @Test
    public void testShortName()
    {
        WebAppManifest manifest = new WebAppManifest();
        assertNull(manifest.getShortName(), "shortName");

        manifest.setShortName("a short name");
        assertEquals("a short name", manifest.getShortName(), "shortName");

        manifest.setShortName(null);
        assertNull(manifest.getShortName(), "shortName");
    }

    /**
     * Tests the {@code icons} property.
     */
    @Test
    public void testIcons()
    {
        WebAppManifest manifest = new WebAppManifest();
        assertNull(manifest.getIcons(), "icons");

        manifest.setIcons(new ImageResource[0]);
        assertEquals(0, manifest.getIcons().length, "icons.length");

        manifest.setIcons(new ImageResource[] {new ImageResource()});
        assertNotNull(manifest.getIcons()[0], "icons[0]");

        manifest.setIcons(null);
        assertNull(manifest.getIcons(), "icons");
    }
}
