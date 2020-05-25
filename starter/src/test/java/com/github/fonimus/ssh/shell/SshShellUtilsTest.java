/*
 * Copyright (c) 2020 François Onimus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fonimus.ssh.shell;

import org.apache.sshd.common.channel.PtyMode;
import org.apache.sshd.common.io.IoSession;
import org.apache.sshd.server.channel.ChannelSession;
import org.apache.sshd.server.session.ServerSession;
import org.jline.terminal.Attributes;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SshShellUtilsTest {

    @Test
    void testFillAttributes() {
        Attributes attributes = new Attributes();
        Map<PtyMode, Integer> map = new HashMap<>();
        for (PtyMode value : PtyMode.values()) {
            map.put(value, 1);
        }
        SshShellUtils.fill(attributes, map);
        assertFalse(attributes.getControlChars().isEmpty());
    }

    public static ChannelSession mockChannelSession(Long id) {
        ChannelSession session = mock(ChannelSession.class);
        ServerSession serverSession = mock(ServerSession.class);
        when(session.getSession()).thenReturn(serverSession);
        IoSession ioSession = mock(IoSession.class);
        when(serverSession.getIoSession()).thenReturn(ioSession);
        when(ioSession.getId()).thenReturn(id);
        return session;
    }
}
