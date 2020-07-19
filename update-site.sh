#!/bin/sh

cd "`dirname \"$0\"`" || exit 1
destdir=../../Web/vx68k.bitbucket.io/webapps.java/2

rm -rf target/site */target/site || exit 1
rm -rf target/staging || exit 1

LANG=en_US.UTF-8 mvn -q -B site site:stage || exit 1

rsync -a --delete-after target/staging/. $destdir/ || exit 1

exit 0
