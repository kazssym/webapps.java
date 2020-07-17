/*
 * ServerAgent.java
 * Copyright (C) 2020 Kaz Nishimura
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

package org.vx68k.webapp.toybox.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ServerAgent implements Closeable
{
    public static final int DEFAULT_PORT = 6080;

    private ServerSocketChannel channel = null;

    public ServerAgent() throws IOException
    {
        open();
    }

    public void open() throws IOException
    {
        if (channel == null) {
            channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress(DEFAULT_PORT));
        }
    }

    @Override
    public void close() throws IOException
    {
        if (channel != null) {
            channel.close();
        }
        channel = null;
    }
}
