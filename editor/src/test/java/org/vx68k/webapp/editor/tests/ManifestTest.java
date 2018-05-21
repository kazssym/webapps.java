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

import org.junit.Test;
import org.vx68k.webapp.editor.Manifest;

import static org.junit.Assert.*;

/**
 * Test fixture for {@link Manifest}.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
public class ManifestTest
{
    /**
     * Tests the {@code name} property.
     */
    @Test
    public void testName()
    {
        Manifest manifest = new Manifest();
        assertNull(manifest.getName());
        manifest.setName("a name");
        assertEquals("a name", manifest.getName());
        manifest.setName(null);
        assertNull(manifest.getName());
    }

    /**
     * Tests the {@code shortName} property.
     */
    @Test
    public void testShortName()
    {
        Manifest manifest = new Manifest();
        assertNull(manifest.getShortName());
        manifest.setShortName("a short name");
        assertEquals("a short name", manifest.getShortName());
        manifest.setShortName(null);
        assertNull(manifest.getShortName());
    }
}
