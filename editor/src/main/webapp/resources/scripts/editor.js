// editor.js - editor control script
// Copyright (C) 2018 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

"use strict";

document.addEventListener("DOMContentLoaded", () => {
        let textArea = document.getElementById("editor-text");
        if (textArea != null) {
            window.editor = CodeMirror.fromTextArea(textArea, {
                });
        }
    });
