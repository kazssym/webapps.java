/*
 * Config.java
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

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Configuration of this web application.
 */
@ApplicationScoped
@Named("config")
public final class Config
{
    /**
     * Name of the system property for client identifier for Google Sign-In.
     */
    public static final String SIGN_IN_CLIENT_ID_PROPERTY =
        "org.vx68k.webapp.api.SIGN_IN_CLIENT_ID";

    /**
     * Client identifier for Google Sign-In.
     */
    private String signInClientId;

    /**
     * Returns the client identifier for Google Sign-In.
     *
     * @return the client identifier
     */
    public String getSignInClientId()
    {
        return signInClientId;
    }

    /**
     * Sets the client identifier for Google Sign-In.
     * If the value is {@code "!"}, the client identifier is set to the value
     * of system property {@value #SIGN_IN_CLIENT_ID_PROPERTY}.
     *
     * @param value the new client identifier
     */
    @Resource(name = "signInClientId")
    public void setSignInClientId(String value)
    {
        if ("!".equals(value)) {
            value = System.getProperty(SIGN_IN_CLIENT_ID_PROPERTY);
        }
        signInClientId = value;
    }
}
