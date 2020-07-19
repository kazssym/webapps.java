// init.js
// Copyright (C) 2020 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

// This file is a module script and shall be in strict mode by default.

const SERVER_AGENT_ENDPOINT_PATH = "/server_agent";

let url = new URL(import.meta.url);
let context = url.hash.substring(1);

let agent = new WebSocket(`wss://${url.host}${context}${SERVER_AGENT_ENDPOINT_PATH}`);
agent.addEventListener("error", (event) => {
    console.error("%o", event);
});
agent.addEventListener("message", (event) => {
    console.log("%o", event.data);
});

function test()
{
    let buffer = new ArrayBuffer(1);
    let view = new DataView(buffer);
    view.setUint8(0, 1);
    agent.send(buffer);
}

for (let i of document.getElementsByClassName("site-button-test")) {
    i.addEventListener("click", () => {
        test();
    });
}
