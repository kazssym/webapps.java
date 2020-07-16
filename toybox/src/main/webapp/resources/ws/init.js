// init.js
// Copyright (C) 2020 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

// This file is a module script and shall be in strict mode by default.

let url = new URL(import.meta.url);
let context = url.hash.substring(1);

let agent = new WebSocket(`wss://${url.host}${context}/agent`);
agent.addEventListener("error", (event) => {
    console.error("%o", event);
});
agent.addEventListener("message", (event) => {
    console.log("%o", event.data);
});

function test()
{
    agent.send("aa");
}

for (let i of document.getElementsByClassName("site-button-test")) {
    i.addEventListener("click", () => {
        test();
    });
}
