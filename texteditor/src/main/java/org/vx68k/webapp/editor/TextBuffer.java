/*
 * TextBuffer.java - class TextBuffer
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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Text buffer.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
@SessionScoped
@Named("buffer")
public class TextBuffer implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Content text.
     */
    private String text = "text text text";

    /**
     * Returns the content text.
     *
     * @return the content text
     */
    public final String getText()
    {
        return text;
    }

    /**
     * Sets the content text.
     *
     * @param value the new content text.
     */
    public final void setText(final String value)
    {
        text = value;
    }
}
