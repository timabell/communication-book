#!/bin/sh -v
ant -f build.xml clean jar && ./package.sh && lintian dist/communication-book.deb 
