/*
 * ImageResourceTest.java - class ImageResourceTest
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * Test fixture for {@link ImageResource}.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
public final class ImageResourceTest
{
    /**
     * Tests the {@code src} property.
     */
    @Test
    public void testSrc()
    {
        ImageResource image = new ImageResource();
        assertNull(image.getSrc(), "src");
        assertFalse(image.toJsonObject().containsKey("src"),
            "'src' in JSON(image)");

        image.setSrc("http://example.com/");
        assertEquals("http://example.com/", image.getSrc(), "src");
        assertEquals("http://example.com/",
            image.toJsonObject().getString("src", null), "JSON(image)['src']");

        image.setSrc(null);
        assertNull(image.getSrc(), "src");
    }

    /**
     * Tests the {@code sizes} property.
     */
    @Test
    public void testSizes()
    {
        ImageResource image = new ImageResource();
        assertNull(image.getSizes(), "sizes");
        assertFalse(image.toJsonObject().containsKey("sizes"),
            "'sizes' in JSON(image)");

        image.setSizes("48x48");
        assertEquals("48x48", image.getSizes(), "sizes");
        assertEquals("48x48", image.toJsonObject().getString("sizes", null),
            "JSON(image)['sizes']");

        image.setSizes(null);
        assertNull(image.getSizes(), "sizes");
    }

    /**
     * Tests the {@code type} property.
     */
    @Test
    public void testType()
    {
        ImageResource image = new ImageResource();
        assertNull(image.getType(), "type");
        assertFalse(image.toJsonObject().containsKey("type"),
            "'type' in JSON(image)");

        image.setType("image/png");
        assertEquals("image/png", image.getType(), "type");
        assertEquals("image/png", image.toJsonObject().getString("type", null),
            "JSON(image)['type']");

        image.setType(null);
        assertNull(image.getType(), "image.type");
    }
}
