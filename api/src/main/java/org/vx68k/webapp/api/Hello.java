/*
 * Hello.java
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

package org.vx68k.webapp.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Simple REST API resource.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
@Path("hello")
public class Hello
{
    /**
     * Returns a sample message.
     *
     * @return sample message
     */
    @GET
    public String echo()
    {
        return "hello";
    }
}
