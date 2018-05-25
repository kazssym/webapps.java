//

"use strict";

((scriptId) => {
    let once = false;
    const init = function () {
        if (!once) {
            once = true;
            console.log("init once");
        }
    };

    let script = document.getElementById(scriptId);
    script.addEventListener("load", () => {
            init();
        });
    if ("gapi" in window) {
        init();
    }
})("gapi-script");
