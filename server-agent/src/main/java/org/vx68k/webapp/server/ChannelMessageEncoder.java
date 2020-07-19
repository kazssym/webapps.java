/*
 * ChannelMessageEncoder.java
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

import java.nio.ByteBuffer;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChannelMessageEncoder implements Encoder.Binary<ChannelMessage>
{
    private EndpointConfig endpointConfig;

    public final EndpointConfig getEndpointConfig()
    {
        return endpointConfig;
    }

    @Override
    public ByteBuffer encode(final ChannelMessage message) throws EncodeException
    {
        int channel = message.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put(0, (byte)channel);
        return buffer;
    }

    @Override
    public final void init(final EndpointConfig endpointConfig)
    {
        this.endpointConfig = endpointConfig;
    }

    @Override
    public final void destroy()
    {
        endpointConfig = null;
    }
}
