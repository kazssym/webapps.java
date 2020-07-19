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

package org.vx68k.webapp.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.channels.ServerSocketChannel;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint(
    decoders={ChannelMessageDecoder.class},
    encoders={ChannelMessageEncoder.class}
)
public class ServerAgent
{
    public static final String SERVER_ENDPOINT_PATH = "/server";

    public static final String SERVER_AGENT_ENDPOINT_PATH = "/server_agent";

    public static final int DEFAULT_PORT = 6080;

    private Session serverSession = null;

    private ServerSocketChannel socketChannel = null;

    public static void main(final String[] args)
    {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(ServerAgent.class,
                URI.create(args[0] + SERVER_ENDPOINT_PATH));
        }
        catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @OnOpen
    public final void handleOpen(final Session session) throws IOException
    {
        serverSession = session;

        socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(DEFAULT_PORT));
    }
}
