/*
 * ChannelMessage.java
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

/**
 * Channel message.
 *
 * @author Kaz Nishimura
 */
public class ChannelMessage
{
    private int channel;

    private ByteBuffer payload = null;

    public ChannelMessage()
    {
        this(-1, null);
    }

    public ChannelMessage(final int channel, final ByteBuffer payload)
    {
        this.channel = channel;

        setPayload(payload);
    }

    public final int getChannel()
    {
        return channel;
    }

    public final void setChannel(final int channel)
    {
        this.channel = channel;
    }

    public final ByteBuffer getPayload()
    {
        return payload;
    }

    public final void setPayload(ByteBuffer payload)
    {
        if (payload != null) {
            payload = payload.duplicate();
        }
        this.payload = payload;
    }
}
