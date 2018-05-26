/*
 * ManifestImageTest.java
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

package org.vx68k.webapp.manifest.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.vx68k.webapp.manifest.ManifestImage;

/**
 * Test fixture for {@link ManifestImage}.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
public final class ManifestImageTest
{
    /**
     * Tests the {@code src} property.
     */
    @Test
    public void testSrc()
    {
        ManifestImage image = new ManifestImage();
        assertNull("image.src", image.getSrc());
        assertFalse("'src' in JSON(image)",
            image.toJsonObject().containsKey("src"));

        image.setSrc("http://example.com/");
        assertEquals("image.src", "http://example.com/", image.getSrc());
        assertEquals("JSON(image)['src']", "http://example.com/",
            image.toJsonObject().getString("src", null));

        image.setSrc(null);
        assertNull("image.src", image.getSrc());
    }

    /**
     * Tests the {@code sizes} property.
     */
    @Test
    public void testSizes()
    {
        ManifestImage image = new ManifestImage();
        assertNull("image.sizes", image.getSizes());
        assertFalse("'sizes' in JSON(image)",
            image.toJsonObject().containsKey("sizes"));

        image.setSizes("48x48");
        assertEquals("image.sizes", "48x48", image.getSizes());
        assertEquals("JSON(image)['sizes']", "48x48",
            image.toJsonObject().getString("sizes", null));

        image.setSizes(null);
        assertNull("image.sizes", image.getSizes());
    }

    /**
     * Tests the {@code type} property.
     */
    @Test
    public void testType()
    {
        ManifestImage image = new ManifestImage();
        assertNull("image.type", image.getType());
        assertFalse("'type' in JSON(image)",
            image.toJsonObject().containsKey("type"));

        image.setType("image/png");
        assertEquals("image.type", "image/png", image.getType());
        assertEquals("JSON(image)['type']", "image/png",
            image.toJsonObject().getString("type", null));

        image.setType(null);
        assertNull("image.type", image.getType());
    }
}
