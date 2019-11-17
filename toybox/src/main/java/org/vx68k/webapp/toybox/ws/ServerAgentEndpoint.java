/*
 * ServerAgentEndpoint.java
 * Copyright (C) 2019 Kaz Nishimura
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

import java.nio.ByteBuffer;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * WebSocket endpoint for server agents.
 *
 * @author Kaz Nishimura
 */
@ServerEndpoint(
    value="/agent",
    decoders={ServerAgentEndpoint.ChannelMessageDecoder.class},
    encoders={ServerAgentEndpoint.ChannelMessageEncoder.class})
public class ServerAgentEndpoint
{
    @OnMessage
    public void handleMessage(final ChannelMessage message, final Session session)
    {
    }

    /**
     * Decoder for {@link ChannelMessage}s.
     */
    protected static class ChannelMessageDecoder implements
        Decoder.Binary<ChannelMessage>
    {
        @Override
        public ChannelMessage decode(final ByteBuffer buffer) throws
            DecodeException
        {
            return new ChannelMessage();
        }

        /**
         * Returns {@code true}.
         *
         * @param buffer
         * @return {@code true}
         */
        @Override
        public boolean willDecode(final ByteBuffer buffer)
        {
            return true;
        }

        @Override
        public void init(final EndpointConfig endpointConfig)
        {
        }

        @Override
        public void destroy()
        {
        }

    }

    /**
     * Encoder for {@link ChannelMessage}s.
     */
    protected static class ChannelMessageEncoder implements
        Encoder.Binary<ChannelMessage>
    {
        @Override
        public ByteBuffer encode(final ChannelMessage message) throws
            EncodeException
        {
            ByteBuffer buffer = ByteBuffer.allocate(1);
            return buffer;
        }

        @Override
        public void init(final EndpointConfig endpointConfig)
        {
        }

        @Override
        public void destroy()
        {
        }
    }
}
