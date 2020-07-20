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
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint(
    decoders={
        ControlMessageDecoder.class,
        ChannelMessageDecoder.class,
    },
    encoders={
        ControlMessageEncoder.class,
        ChannelMessageEncoder.class
    }
)
public class ServerAgent
{
    public static final String SERVER_ENDPOINT_PATH = "/server";

    public static final String SERVER_AGENT_ENDPOINT_PATH = "/server_agent";

    public static final int DEFAULT_PORT = 6080;

    private final int port;

    private Session serverSession = null;

    private AsynchronousServerSocketChannel serverSocket = null;

    /**
     * Returns the logger for this class.
     *
     * @return the logger for this class
     */
    protected static Logger getLogger()
    {
        return Logger.getLogger(ServerAgent.class.getPackage().getName());
    }

    public static void main(final String[] args)
    {
        try {
            ServerAgent agent = new ServerAgent();

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(agent, URI.create(args[0] + SERVER_ENDPOINT_PATH));
        }
        catch (final Exception e1) {
            e1.printStackTrace();
            System.exit(1);
        }
    }

    public ServerAgent()
    {
        this(DEFAULT_PORT);
    }

    public ServerAgent(final int port)
    {
        this.port = port;
    }

    protected void openServerSocket() throws IOException
    {
        serverSocket = AsynchronousServerSocketChannel.open();
        try {
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.accept(null,
                new CompletionHandler<AsynchronousSocketChannel, Object>()
                {
                    @Override
                    public void completed(final AsynchronousSocketChannel result,
                        final Object attachment)
                    {
                        serverSocket.accept(attachment, this);

                        // TODO: Store the socket.
                    }

                    @Override
                    public void failed(final Throwable exception, final Object attachment)
                    {
                        exception.printStackTrace();
                    }
                }
            );
        }
        catch (final IOException e1) {
            serverSocket.close();
            serverSocket = null;
            throw e1;
        }
    }

    protected void send(final ChannelMessage message) throws EncodeException, IOException
    {
        RemoteEndpoint.Basic remote = serverSession.getBasicRemote();
        remote.sendObject(message);
    }

    @OnOpen
    public final void handleOpen(final Session session) throws IOException
    {
        serverSession = session;
        openServerSocket();
    }

    @OnClose
    public void handleClose() throws IOException
    {
        serverSocket.close();
        serverSocket = null;
    }
}
