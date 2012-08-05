#!/bin/sh

echo "setting up package structure in package-tmp/"

umask 0022
mkdir -p package-tmp/DEBIAN
mkdir -p package-tmp/usr/bin
mkdir -p package-tmp/usr/share/communication-book
mkdir -p package-tmp/usr/share/applications
mkdir -p package-tmp/usr/share/doc/communication-book

cp debian/control/control package-tmp/DEBIAN
cp communication-book package-tmp/usr/bin
cp dist/communication-book.jar package-tmp/usr/share/communication-book
gzip -9 -c debian/changelog > package-tmp/usr/share/doc/communication-book/changelog.gz
cp communication-book.desktop package-tmp/usr/share/applications/
cp copyright package-tmp/usr/share/doc/communication-book

fakeroot dpkg-deb -b package-tmp dist/communication-book.deb
