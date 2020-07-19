/*
 * ServerEndpoint.java
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

import java.io.IOException;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;
import org.vx68k.webapp.server.ChannelMessage;
import org.vx68k.webapp.server.ChannelMessageDecoder;
import org.vx68k.webapp.server.ChannelMessageEncoder;
import org.vx68k.webapp.server.ServerAgent;

/**
 * WebSocket endpoint for server agents.
 *
 * @author Kaz Nishimura
 */
@ServerEndpoint(
    value=ServerAgent.SERVER_ENDPOINT_PATH,
    decoders={ChannelMessageDecoder.class},
    encoders={ChannelMessageEncoder.class})
public class ServerEndpont
{
    private ConnectionManager connectionManager = null;

    public final ConnectionManager getConnectionManager()
    {
        return connectionManager;
    }

    @Inject
    public final void setConnectionManager(final ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @OnMessage
    public void handleMessage(final ChannelMessage message, final Session session)
        throws EncodeException, IOException
    {
        // TODO: Replace this with a real data.
        Basic remote = session.getBasicRemote();
        remote.sendObject(message);
    }
}
