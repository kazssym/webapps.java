/*
 * AccessControlFilter
 * Copyright (C) 2017-2018 Kaz Nishimura
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

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Controls access to resources.
 *
 * @author Kaz Nishimura
 * @since 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public final class AccessControlFilter implements Filter, Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Configuration given to {@link #init init}.
     */
    private transient FilterConfig config;

    /**
     * Returns the stored configuration previously given to
     * {@link #init init}.
     *
     * @return the stored configuration, or {@code null}
     */
    public FilterConfig getFilterConfig()
    {
        return config;
    }

    /**
     * Initializes this filter.
     * The given configuration is to be stored until {@link #destroy destroy}
     * is invoked later.
     *
     * {@inheritDoc}
     *
     * @param filterConfig the configuration for this filter
     * @exception ServletException if an error occurred
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException
    {
        assert filterConfig != null;
        config = filterConfig;
    }

    /**
     * Makes this filter inactive and releases the stored configuration.
     * After an invocation of this method, this filter does not work until
     * {@link #init init} is invoked again.
     *
     * {@inheritDoc}
     */
    @Override
    public void destroy()
    {
        config = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(final ServletRequest request,
        final ServletResponse response, final FilterChain chain)
        throws ServletException, IOException
    {
        chain.doFilter(request, response);
    }
}
