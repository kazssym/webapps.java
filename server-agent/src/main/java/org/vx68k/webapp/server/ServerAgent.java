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
import java.util.concurrent.atomic.AtomicBoolean;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint(
    decoders={ChannelMessageDecoder.class},
    encoders={ChannelMessageEncoder.class}
)
public class ServerAgent implements Runnable
{
    public static final String SERVER_ENDPOINT_PATH = "/server";

    public static final String SERVER_AGENT_ENDPOINT_PATH = "/server_agent";

    public static final int DEFAULT_PORT = 6080;

    private static Session serverSession = null;

    private ServerSocketChannel listeningChannel = null;

    private final AtomicBoolean running = new AtomicBoolean(false);

    public static void main(final String[] args)
    {
        try {
            ServerAgent agent = new ServerAgent();

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            serverSession = container.connectToServer(agent,
                URI.create(args[0] + SERVER_ENDPOINT_PATH));

            agent.run();
        }
        catch (final Exception e1) {
            e1.printStackTrace();
            System.exit(1);
        }
    }

    @OnOpen
    public final void handleOpen(final Session session) throws IOException
    {
        listeningChannel = ServerSocketChannel.open();
        listeningChannel.bind(new InetSocketAddress(DEFAULT_PORT));
    }

    @Override
    public void run()
    {
        running.set(true);
        while (running.get()) {
            try {
                listeningChannel.accept();
            }
            catch (final IOException e1) {
                e1.printStackTrace();
                running.set(false); // TODO: Try to recover.
            }
        }
    }
}
