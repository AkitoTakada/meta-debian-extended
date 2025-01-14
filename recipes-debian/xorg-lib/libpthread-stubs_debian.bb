# base recipe: meta/recipes-graphics/xorg-lib/libpthread-stubs_0.4.bb
# base branch: warrior
# base commit: a63aa5c0e46b7dc7efb1be7272267fab93e2693e

SUMMARY = "Library that provides weak aliases for pthread functions"
DESCRIPTION = "This library provides weak aliases for pthread functions \
not provided in libc or otherwise available by default."
HOMEPAGE = "http://xcb.freedesktop.org"
BUGTRACKER = "http://bugs.freedesktop.org/buglist.cgi?product=XCB"
SECTION = "x11/libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=6edc1fea03d959f0c2d743fe5ca746ad"

inherit debian-package
require recipes-debian/sources/libpthread-stubs.inc
DEBIAN_PATCH_TYPE = "nopatch"

inherit autotools

RDEPENDS_${PN}-dev = ""
RRECOMMENDS_${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"

BBCLASSEXTEND = "native nativesdk"
