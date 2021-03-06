/*
 * ControlMessageDecoder.java
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
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decoders for control messages.
 *
 * @author Kaz Nishimura
 * @since 2.0
 */
public class ControlMessageDecoder implements Decoder.Binary<ControlMessage>
{
    private EndpointConfig endpointConfig;

    public final EndpointConfig getEndpointConfig()
    {
        return endpointConfig;
    }

    @Override
    public final ControlMessage decode(final ByteBuffer buffer) throws DecodeException
    {
        byte b1 = buffer.get();
        if (b1 != 0x80) {
            throw new DecodeException(buffer, "not a control message");
        }

        int flags = buffer.get();
        return new ControlMessage(flags, buffer.slice());
    }

    @Override
    public final boolean willDecode(ByteBuffer buffer)
    {
        if (buffer.remaining() >= 1) {
            buffer = buffer.duplicate();

            byte b1 = buffer.get();
            return b1 == 0x80;
        }
        return false;
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
